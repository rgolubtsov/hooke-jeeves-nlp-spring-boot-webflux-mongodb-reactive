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
    private final int nvars;

    private final double startpt0;

    private final double startpt1;

    private final double startpt2;

    private final double startpt3;

    private final double rho;

    public int getNvars() {
        return nvars;
    }

    public double getStartpt0() {
        return startpt0;
    }

    public double getStartpt1() {
        return startpt1;
    }

    public double getStartpt2() {
        return startpt2;
    }

    public double getStartpt3() {
        return startpt3;
    }

    public double getRho() {
        return rho;
    }

    /**
     * The effective constructor.
     *
     * @param _nvars    TBD.
     * @param _startpt0 TBD.
     * @param _startpt1 TBD.
     * @param _startpt2 TBD.
     * @param _startpt3 TBD.
     * @param _rho      TBD.
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
