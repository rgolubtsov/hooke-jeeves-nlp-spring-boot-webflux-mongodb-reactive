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

/** The controller class of the microservice. */
@RestController
public class HookeJeevesController {
    // Helper constants.
    private static final String REST_STORE_ROSENBROCK = "store-rosenbrock";
    private static final String REST_STORE_WOODS      = "store-woods";
    private static final String REST_SOLVE            = "solve";

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

    private static final String EQUALS   = "=";
    private static final String NEW_LINE = System.lineSeparator();

    @PutMapping(REST_STORE_ROSENBROCK)
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

    @PutMapping(REST_STORE_WOODS)
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

    @GetMapping(REST_SOLVE)
    public void solve_the_problem(
        @RequestParam(name=FX, defaultValue=ROSENBROCK)       String fx,
                                                        final Object _) {

        int nvars;
        int itermax;
        int jj;
        int i;

        double[] startpt = new double[HookeJeeves.VARS];
        double   rho;
        double   epsilon;
        double[] endpt   = new double[HookeJeeves.VARS];

               if (fx.compareTo(ROSENBROCK) == 0) {
            // TODO: Implement solving a nonlinear optimization problem
            //       when the objective function is the Rosenbrock's parabolic
            //       valley function.
        } else if (fx.compareTo(WOODS     ) == 0) {
            // TODO: Implement solving a nonlinear optimization problem
            //       when the objective function is so-called "Woods" function.
        } else {
            fx = null;
        }

        System.out.println(FX + EQUALS + fx);
    }

    @PostMapping(REST_SOLVE)
    public void solve_the_problem(
        @RequestParam(name=FX, defaultValue=ROSENBROCK) final String fx) {

        solve_the_problem(fx, null);
    }
}

// vim:set nu et ts=4 sw=4:
