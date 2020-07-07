/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesController.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.0.1
 * ============================================================================
 * Copyright (C) 2020 Radislav (Radicchio) Golubtsov
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

/** The controller class of the microservice. */
@RestController
public class HookeJeevesController {
    // Helper constants.
    private static final String REST_STORE = "store";
    private static final String REST_SOLVE = "solve";

    private static final String NVARS    = "nvars";
    private static final String STARTPT0 = "startpt0";
    private static final String STARTPT1 = "startpt1";
    private static final String STARTPT2 = "startpt2";
    private static final String STARTPT3 = "startpt3";
    private static final String RHO      = "rho";

    private static final String TWO                 =  "2";
    private static final String MINUS_ONE_POINT_TWO = "-1.2";
    private static final String ONE_POINT_ZERO      =  "1.0";

    private static final String FOUR                =  "4";
    private static final String MINUS_THREE         = "-3";
    private static final String MINUS_ONE           = "-1";

    private static final String RHO_BEGIN_STR       =  "0.5";
    private static final String RHO_WOODS_STR       =  "0.6";

    private static final String FX         = "fx";
    private static final String ROSENBROCK = "rosenbrock";
    private static final String WOODS      = "woods";

    private static final String SLASH    = "/";
    private static final String EQUALS   = "=";
    private static final String NEW_LINE = System.lineSeparator();

    /**
     * The "/store/rosenbrock" PUT endpoint.
     * Puts initial guess data to the database for their future retrieval.
     *
     * @param nvars    The number of variables.
     * @param startpt0 The 1st starting point coordinate.
     * @param startpt1 The 2nd starting point coordinate.
     * @param rho      The rho value.
     */
    @PutMapping(REST_STORE + SLASH + ROSENBROCK)
    public void store_initial_guess_data(
        @RequestParam(name=NVARS,    defaultValue=TWO                ) final String nvars,
        @RequestParam(name=STARTPT0, defaultValue=MINUS_ONE_POINT_TWO) final String startpt0,
        @RequestParam(name=STARTPT1, defaultValue=ONE_POINT_ZERO     ) final String startpt1,
        @RequestParam(name=RHO,      defaultValue=RHO_BEGIN_STR      ) final String rho) {

        System.out.println(NVARS    + EQUALS + nvars    + NEW_LINE
                         + STARTPT0 + EQUALS + startpt0 + NEW_LINE
                         + STARTPT1 + EQUALS + startpt1 + NEW_LINE
                         + RHO      + EQUALS + rho);

        // Putting initial guess data to the database.
        _put_to_db(nvars, startpt0, startpt1, null, null, rho);
    }

    /**
     * The "/store/woods" PUT endpoint.
     * Puts initial guess data to the database for their future retrieval.
     *
     * @param nvars    The number of variables.
     * @param startpt0 The 1st starting point coordinate.
     * @param startpt1 The 2nd starting point coordinate.
     * @param startpt2 The 3rd starting point coordinate.
     * @param startpt3 The 4th starting point coordinate.
     * @param rho      The rho value.
     */
    @PutMapping(REST_STORE + SLASH + WOODS)
    public void store_initial_guess_data(
        @RequestParam(name=NVARS,    defaultValue=FOUR         ) final String nvars,
        @RequestParam(name=STARTPT0, defaultValue=MINUS_THREE  ) final String startpt0,
        @RequestParam(name=STARTPT1, defaultValue=MINUS_ONE    ) final String startpt1,
        @RequestParam(name=STARTPT2, defaultValue=MINUS_THREE  ) final String startpt2,
        @RequestParam(name=STARTPT3, defaultValue=MINUS_ONE    ) final String startpt3,
        @RequestParam(name=RHO,      defaultValue=RHO_WOODS_STR) final String rho) {

        System.out.println(NVARS    + EQUALS + nvars    + NEW_LINE
                         + STARTPT0 + EQUALS + startpt0 + NEW_LINE
                         + STARTPT1 + EQUALS + startpt1 + NEW_LINE
                         + STARTPT2 + EQUALS + startpt2 + NEW_LINE
                         + STARTPT3 + EQUALS + startpt3 + NEW_LINE
                         + RHO      + EQUALS + rho);

        // Putting initial guess data to the database.
        _put_to_db(nvars, startpt0, startpt1, startpt2, startpt3, rho);
    }

    // Helper method. Puts initial guess data to the database.
    private void _put_to_db(final String nvars,
                            final String startpt0,
                            final String startpt1,
                            final String startpt2,
                            final String startpt3,
                            final String rho) {

        // Creating a new document containing initial guess data.
        Document document = new Document();

        document.append(NVARS,    nvars   )
                .append(STARTPT0, startpt0)
                .append(STARTPT1, startpt1);

        if ((startpt2 != null) && (startpt3 != null)) {
            document.append(STARTPT2, startpt2)
                    .append(STARTPT3, startpt3);
        }

        document.append(RHO, rho);

        // Putting initial guess data to the database.
        HookeJeevesApplication.collection.insertOne(document);
    }

    /**
     * The "/solve" GET endpoint.
     * Retrieves initial guess data form the database
     * and calls the main optimization method against them.
     * In short, solves the nonlinear optimization problem.
     *
     * @param fx The name of the user-supplied objective function f(x,n).
     */
    @GetMapping(REST_SOLVE)
    public void solve_the_problem(
        @RequestParam(name=FX, defaultValue=ROSENBROCK)       String fx,
                                                        final Object _) {

        Document document;

        int nvars=0;
        int itermax;
        int jj;
        int i;

        double[] startpt = new double[HookeJeeves.VARS];
        double   rho=.0;
        double   epsilon;
        double[] endpt   = new double[HookeJeeves.VARS];

        Class objfun_cls;

        System.out.println(FX + EQUALS + fx);

               if (fx.compareTo(ROSENBROCK) == 0) {
            // $ mongo
            // > db.hooke_initial_guess_data.find({nvars: "2"}).sort({_id: -1}).limit(1)
            document = (Document) HookeJeevesApplication.collection
                .find(eq(NVARS, TWO))
                .sort(new Document("_id", -1))
                .first();

            nvars      = new Integer(document.getString(NVARS   )).intValue   ();
            startpt[0] = new Double (document.getString(STARTPT0)).doubleValue();
            startpt[1] = new Double (document.getString(STARTPT1)).doubleValue();
            rho        = new Double (document.getString(RHO     )).doubleValue();

            System.out.println(NVARS    + EQUALS + nvars      + NEW_LINE
                             + STARTPT0 + EQUALS + startpt[0] + NEW_LINE
                             + STARTPT1 + EQUALS + startpt[1] + NEW_LINE
                             + RHO      + EQUALS + rho);

            objfun_cls = Rosenbrock.class;
        } else if (fx.compareTo(WOODS     ) == 0) {
            // $ mongo
            // > db.hooke_initial_guess_data.find({nvars: "4"}).sort({_id: -1}).limit(1)
            document = (Document) HookeJeevesApplication.collection
                .find(eq(NVARS, FOUR))
                .sort(new Document("_id", -1))
                .first();

            nvars      = new Integer(document.getString(NVARS   )).intValue   ();
            startpt[0] = new Double (document.getString(STARTPT0)).doubleValue();
            startpt[1] = new Double (document.getString(STARTPT1)).doubleValue();
            startpt[2] = new Double (document.getString(STARTPT2)).doubleValue();
            startpt[3] = new Double (document.getString(STARTPT3)).doubleValue();
            rho        = new Double (document.getString(RHO     )).doubleValue();

            System.out.println(NVARS    + EQUALS + nvars      + NEW_LINE
                             + STARTPT0 + EQUALS + startpt[0] + NEW_LINE
                             + STARTPT1 + EQUALS + startpt[1] + NEW_LINE
                             + STARTPT2 + EQUALS + startpt[2] + NEW_LINE
                             + STARTPT3 + EQUALS + startpt[3] + NEW_LINE
                             + RHO      + EQUALS + rho);

            objfun_cls = Woods.class;
        } else {
            objfun_cls = null;
        }

        itermax = HookeJeeves.IMAX;
        epsilon = HookeJeeves.EPSMIN;

        if (objfun_cls != null) {
            jj = new HookeJeeves()
                .hooke(nvars, startpt, endpt, rho, epsilon, itermax, objfun_cls);

            System.out.println("\n\n\nHOOKE USED " + jj
                             + " ITERATIONS, AND RETURNED");

            for (i = 0; i < nvars; i++) {
                System.out.printf("x[%3d] = %15.7e \n", i, endpt[i]);
            }
        } else {
            // TODO: Implement returning a specific response
            //       when the objective function is not defined.
        }

        if (fx.compareTo(WOODS) == 0) {
            System.out.println("True answer: f(1, 1, 1, 1) = 0.");
        }
    }

    /**
     * The "/solve" POST endpoint.
     * Retrieves initial guess data form the database
     * and calls the main optimization method against them.
     * In short, solves the nonlinear optimization problem.
     *
     * @param fx The name of the user-supplied objective function f(x,n).
     */
    @PostMapping(REST_SOLVE)
    public void solve_the_problem(
        @RequestParam(name=FX, defaultValue=ROSENBROCK) final String fx) {

        solve_the_problem(fx, null);
    }
}

// vim:set nu et ts=4 sw=4:
