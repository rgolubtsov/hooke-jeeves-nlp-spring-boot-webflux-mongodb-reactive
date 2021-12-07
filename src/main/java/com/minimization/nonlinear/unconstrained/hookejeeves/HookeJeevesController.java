/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesController.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.7.0
 * ============================================================================
 * A Spring Boot-based application, designed and intended to be run
 * as a microservice, implementing the nonlinear unconstrained
 * minimization algorithm of Hooke and Jeeves.
 * ============================================================================
 * Copyright (C) 2020-2021 Radislav (Radicchio) Golubtsov
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

import static com.minimization.nonlinear.unconstrained.hookejeeves.HookeJeevesControllerHelper.*;
import        com.minimization.nonlinear.unconstrained.hookejeeves.HookeJeevesApp;
import        com.minimization.nonlinear.unconstrained.hookejeeves.algorithm.HookeJeeves;
import        com.minimization.nonlinear.unconstrained.hookejeeves.algorithm.Rosenbrock;
import        com.minimization.nonlinear.unconstrained.hookejeeves.algorithm.Woods;

/**
 * The controller class of the microservice.
 *
 * @version 0.7.0
 * @since   0.0.1
 */
@RestController
@SuppressWarnings("unchecked")
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

    // Extra helper constants.
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

    private static final String SLASH =   "/";
    private static final String _ID   = "_id";

    /** The SLF4J logger. */
    private static final Logger l = LoggerFactory.getLogger(
        MethodHandles.lookup().lookupClass()
    );

    /** The BSON document. */
    private Document document;

    /**
     * The &quot;<code>/store/rosenbrock</code>&quot; <b>PUT</b> endpoint.
     * <br />
     * <br />Puts initial guess data to the database
     * for their future retrieval.
     *
     * @param nvars    The number of variables.
     * @param startpt0 The 1st starting point coordinate.
     * @param startpt1 The 2nd starting point coordinate.
     * @param rho      The rho value.
     *
     * @return The ResponseEntity object with a specific HTTP status code
     *         provided (and the response body in JSON representation
     *         in case of the request payload is not valid).
     */
    @PutMapping(REST_STORE + SLASH + ROSENBROCK)
    public ResponseEntity store_initial_guess_data(
        @RequestParam(name=NVARS,    defaultValue=TWO                ) final String nvars,
        @RequestParam(name=STARTPT0, defaultValue=MINUS_ONE_POINT_TWO) final String startpt0,
        @RequestParam(name=STARTPT1, defaultValue=ONE_POINT_ZERO     ) final String startpt1,
        @RequestParam(name=RHO,      defaultValue=RHO_BEGIN_STR      ) final String rho) {

        String req_method = RequestMethod.PUT.toString();

        boolean is_request_malformed = false;

        l.debug(req_method + SPACE  + SPACE  + SPACE
              + NVARS      + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + STARTPT0   + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + STARTPT1   + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + RHO        + EQUALS + BRACES,
                nvars,
                startpt0,
                startpt1,
                rho);

        if (nvars.compareTo(TWO) != 0) {
            is_request_malformed = true;
        }

        if (is_request_malformed) {
            HookeJeevesResponsePojoError resp_body_err
                = new HookeJeevesResponsePojoError(
                    ERR_REQ_PARAMS_ROSENBROCK_NEEDS_TWO_VARS);

            return new ResponseEntity(resp_body_err, HttpStatus.BAD_REQUEST);
        }

        // Putting initial guess data to the database.
        _put_to_db(nvars, startpt0, startpt1, null, null, rho);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * The &quot;<code>/store/woods</code>&quot; <b>PUT</b> endpoint.
     * <br />
     * <br />Puts initial guess data to the database
     * for their future retrieval.
     *
     * @param nvars    The number of variables.
     * @param startpt0 The 1st starting point coordinate.
     * @param startpt1 The 2nd starting point coordinate.
     * @param startpt2 The 3rd starting point coordinate.
     * @param startpt3 The 4th starting point coordinate.
     * @param rho      The rho value.
     *
     * @return The ResponseEntity object with a specific HTTP status code
     *         provided (and the response body in JSON representation
     *         in case of the request payload is not valid).
     */
    @PutMapping(REST_STORE + SLASH + WOODS)
    public ResponseEntity store_initial_guess_data(
        @RequestParam(name=NVARS,    defaultValue=FOUR         ) final String nvars,
        @RequestParam(name=STARTPT0, defaultValue=MINUS_THREE  ) final String startpt0,
        @RequestParam(name=STARTPT1, defaultValue=MINUS_ONE    ) final String startpt1,
        @RequestParam(name=STARTPT2, defaultValue=MINUS_THREE  ) final String startpt2,
        @RequestParam(name=STARTPT3, defaultValue=MINUS_ONE    ) final String startpt3,
        @RequestParam(name=RHO,      defaultValue=RHO_WOODS_STR) final String rho) {

        String req_method = RequestMethod.PUT.toString();

        boolean is_request_malformed = false;

        l.debug(req_method + SPACE  + SPACE  + SPACE
              + NVARS      + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + STARTPT0   + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + STARTPT1   + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + STARTPT2   + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + STARTPT3   + EQUALS + BRACES + SPACE + V_BAR + SPACE
              + RHO        + EQUALS + BRACES,
                nvars,
                startpt0,
                startpt1,
                startpt2,
                startpt3,
                rho);

        if (nvars.compareTo(FOUR) != 0) {
            is_request_malformed = true;
        }

        if (is_request_malformed) {
            HookeJeevesResponsePojoError resp_body_err
                = new HookeJeevesResponsePojoError(
                    ERR_REQ_PARAMS_WOODS_NEEDS_FOUR_VARS);

            return new ResponseEntity(resp_body_err, HttpStatus.BAD_REQUEST);
        }

        // Putting initial guess data to the database.
        _put_to_db(nvars, startpt0, startpt1, startpt2, startpt3, rho);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    // Helper method: Puts initial guess data to the database.
    private final void _put_to_db(final String nvars,
                                  final String startpt0,
                                  final String startpt1,
                                  final String startpt2,
                                  final String startpt3,
                                  final String rho) {

        // Creating a new document containing initial guess data.
        document = new Document();

        document.append(NVARS,    nvars   )
                .append(STARTPT0, startpt0)
                .append(STARTPT1, startpt1);

        if ((startpt2 != null) && (startpt3 != null)) {
            document.append(STARTPT2, startpt2)
                    .append(STARTPT3, startpt3);
        }

        document.append(RHO, rho);

        // Putting initial guess data to the database.
        HookeJeevesApp.collection
                      .insertOne(document)
                      .subscribe(new PutSubscriber<Document>());
    }

    /**
     * The &quot;<code>/solve</code>&quot; <b>GET</b> endpoint.
     * <br />
     * <br />Retrieves initial guess data form the database
     * and calls the main optimization method against them.
     * <br />In short, it solves the nonlinear optimization problem.
     *
     * @param fx The name of the user-supplied objective function f(x,n).
     * @param __ The flag, indicating whether the POST request method is used.
     *           (For that to be effective, this flag should be
     *           <code>null</code>.)
     *
     * @return The ResponseEntity object, containing the response body
     *         in JSON representation, along with a specific HTTP status code
     *         provided.
     */
    @GetMapping(REST_SOLVE)
    public ResponseEntity solve_the_problem(
        @RequestParam(name=FX, defaultValue=ROSENBROCK)       String fx,
                                                        final Object __) {

        int nvars=0;
        int itermax;
        int jj=0;
        int i;

        double[] startpt = new double[HookeJeeves.VARS];
        double   rho=.0;
        double   epsilon;
        double[] endpt   = new double[HookeJeeves.VARS];

        Class objfun_cls = null;

        double f_x = .0;

        String req_method = RequestMethod.GET + SPACE;

        boolean is_request_malformed = false;

        HookeJeevesResponsePojoError resp_body_err;

        // When calling solve_the_problem()'s POST counterpart,
        // changing the method name accordingly.
        if (__ == null) {
            req_method = RequestMethod.POST.toString();
        }

        l.debug(req_method  + SPACE + SPACE
              + FX + EQUALS + BRACES, fx);

        if ((fx.compareTo(ROSENBROCK) != 0) && (fx.compareTo(WOODS) != 0)) {
            is_request_malformed = true;
        }

        if (is_request_malformed) {
            resp_body_err = new HookeJeevesResponsePojoError(
                ERR_REQ_PARAMS_OBJFUN_UNKNOWN);

            return new ResponseEntity(resp_body_err, HttpStatus.BAD_REQUEST);
        }

        GetSubscriber subscriber = new GetSubscriber<Document>();

               if (fx.compareTo(ROSENBROCK) == 0) {
            /*
             * The following request command in the MongoDB shell
             * is implemented below to retrieve the last document
             * stored with the *nvars* param equals to 2.
             *
             * $ mongo
             * > db.hooke_initial_guess_data.find({nvars: "2"}).sort({_id: -1}).limit(1)
             */
            HookeJeevesApp.collection
                .find(eq(NVARS, TWO))
                .sort(new Document(_ID, -1))
                .first().subscribe(subscriber);

            subscriber.await();

            document = subscriber.getDocument();

            try {
                nvars      = Integer.parseInt   (document.getString(NVARS   ));
                startpt[0] = Double .parseDouble(document.getString(STARTPT0));
                startpt[1] = Double .parseDouble(document.getString(STARTPT1));
                rho        = Double .parseDouble(document.getString(RHO     ));
            } catch (NumberFormatException e) {
                is_request_malformed = true;
            }

            l.debug(req_method + SPACE  + SPACE
                  + NVARS      + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + STARTPT0   + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + STARTPT1   + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + RHO        + EQUALS + BRACES,
                    nvars,
                    startpt[0],
                    startpt[1],
                    rho);

            if (is_request_malformed) {
                resp_body_err = new HookeJeevesResponsePojoError(
                    ERR_FROM_DB_VARS_NUMBERS_EXPECTED);

                return new ResponseEntity(resp_body_err,
                    HttpStatus.BAD_REQUEST);
            }

            objfun_cls = Rosenbrock.class;
        } else if (fx.compareTo(WOODS     ) == 0) {
            /*
             * The following request command in the MongoDB shell
             * is implemented below to retrieve the last document
             * stored with the *nvars* param equals to 4.
             *
             * $ mongo
             * > db.hooke_initial_guess_data.find({nvars: "4"}).sort({_id: -1}).limit(1)
             */
            HookeJeevesApp.collection
                .find(eq(NVARS, FOUR))
                .sort(new Document(_ID, -1))
                .first().subscribe(subscriber);

            subscriber.await();

            document = subscriber.getDocument();

            try {
                nvars      = Integer.parseInt   (document.getString(NVARS   ));
                startpt[0] = Double .parseDouble(document.getString(STARTPT0));
                startpt[1] = Double .parseDouble(document.getString(STARTPT1));
                startpt[2] = Double .parseDouble(document.getString(STARTPT2));
                startpt[3] = Double .parseDouble(document.getString(STARTPT3));
                rho        = Double .parseDouble(document.getString(RHO     ));
            } catch (NumberFormatException e) {
                is_request_malformed = true;
            }

            l.debug(req_method + SPACE  + SPACE
                  + NVARS      + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + STARTPT0   + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + STARTPT1   + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + STARTPT2   + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + STARTPT3   + EQUALS + BRACES + SPACE + V_BAR + SPACE
                  + RHO        + EQUALS + BRACES,
                    nvars,
                    startpt[0],
                    startpt[1],
                    startpt[2],
                    startpt[3],
                    rho);

            if (is_request_malformed) {
                resp_body_err = new HookeJeevesResponsePojoError(
                    ERR_FROM_DB_VARS_NUMBERS_EXPECTED);

                return new ResponseEntity(resp_body_err,
                    HttpStatus.BAD_REQUEST);
            }

            objfun_cls = Woods.class;
        }

        itermax = HookeJeeves.IMAX;
        epsilon = HookeJeeves.EPSMIN;

        jj = new HookeJeeves()
            .hooke(nvars, startpt, endpt, rho, epsilon, itermax, objfun_cls);

        System.out.println("\n\n\nHOOKE USED " + jj
                         + " ITERATIONS, AND RETURNED");

        for (i = 0; i < nvars; i++) {
            System.out.printf("x[%3d] = %15.7e \n", i, endpt[i]);
        }

        if (fx.compareTo(WOODS) == 0) {
            System.out.println("True answer: f(1, 1, 1, 1) = 0.");
        }

        /*
         * Calculating the objective function value
         * at the ending point of the local minimum.
         */
               if (objfun_cls.equals(Rosenbrock.class)) {
            f_x = Rosenbrock.f(endpt, nvars);
        } else if (objfun_cls.equals(     Woods.class)) {
            f_x =      Woods.f(endpt, nvars);
        }

        // Scaling down the ending point coordinate values.
        for (i = 0; i < nvars; i++) {
            endpt[i]=_scale_down_double_value(endpt[i], _get_scaling_factor());
        }

        // Scaling down the objective function value.
        f_x = _scale_down_double_value(f_x, _get_scaling_factor());

        HookeJeevesResponsePojo resp_body = new HookeJeevesResponsePojo(
            new HookeJeevesResponsePojoInputs(nvars, startpt, rho),
            new HookeJeevesResponsePojoOutput(nvars,jj,endpt, f_x));

        return new ResponseEntity(resp_body, HttpStatus.OK);
    }

    /**
     * The &quot;<code>/solve</code>&quot; <b>POST</b> endpoint.
     * <br />
     * <br />Retrieves initial guess data form the database
     * and calls the main optimization method against them.
     * <br />In short, it solves the nonlinear optimization problem.
     *
     * @param fx The name of the user-supplied objective function f(x,n).
     *
     * @return The ResponseEntity object, containing the response body
     *         in JSON representation, along with a specific HTTP status code
     *         provided.
     */
    @PostMapping(REST_SOLVE)
    public ResponseEntity solve_the_problem(
        @RequestParam(name=FX, defaultValue=ROSENBROCK) final String fx) {

        // Simply calling its GET counterpart.
        return solve_the_problem(fx, null);
    }
}

// vim:set nu et ts=4 sw=4:
