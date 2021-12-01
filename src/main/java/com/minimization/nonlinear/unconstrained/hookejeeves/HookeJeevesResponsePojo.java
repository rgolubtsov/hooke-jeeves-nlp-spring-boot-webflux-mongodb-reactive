/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesResponsePojo.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.6.0
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

/**
 * The POJO representation, returning in the response.
 *
 * @version 0.6.0
 * @since   0.6.0
 */
public class HookeJeevesResponsePojo {
    /** The number of variables. */
    private final int nvars;

    /** The 1st starting point coordinate. */
    private final double startpt0;

    /** The 2nd starting point coordinate. */
    private final double startpt1;

    /** The 3rd starting point coordinate. */
    private final double startpt2;

    /** The 4th starting point coordinate. */
    private final double startpt3;

    /** The rho value. */
    private final double rho;

    /**
     * The accessor method for the number of variables.
     *
     * @return The number of variables.
     */
    public int getNvars() {
        return nvars;
    }

    /**
     * The accessor method for the 1st starting point coordinate.
     *
     * @return The 1st starting point coordinate.
     */
    public double getStartpt0() {
        return startpt0;
    }

    /**
     * The accessor method for the 2nd starting point coordinate.
     *
     * @return The 2nd starting point coordinate.
     */
    public double getStartpt1() {
        return startpt1;
    }

    /**
     * The accessor method for the 3rd starting point coordinate.
     *
     * @return The 3rd starting point coordinate.
     */
    public double getStartpt2() {
        return startpt2;
    }

    /**
     * The accessor method for the 4th starting point coordinate.
     *
     * @return The 4th starting point coordinate.
     */
    public double getStartpt3() {
        return startpt3;
    }

    /**
     * The accessor method for the rho value.
     *
     * @return The rho value.
     */
    public double getRho() {
        return rho;
    }

    /**
     * The effective constructor.
     *
     * @param _nvars    The number of variables.
     * @param _startpt0 The 1st starting point coordinate.
     * @param _startpt1 The 2nd starting point coordinate.
     * @param _startpt2 The 3rd starting point coordinate.
     * @param _startpt3 The 4th starting point coordinate.
     * @param _rho      The rho value.
     */
    public HookeJeevesResponsePojo(final int    _nvars,
                                   final double _startpt0,
                                   final double _startpt1,
                                   final double _startpt2,
                                   final double _startpt3,
                                   final double _rho) {

        nvars    = _nvars;
        startpt0 = _startpt0;
        startpt1 = _startpt1;
        startpt2 = _startpt2;
        startpt3 = _startpt3;
        rho      = _rho;
    }
}

// vim:set nu et ts=4 sw=4:
