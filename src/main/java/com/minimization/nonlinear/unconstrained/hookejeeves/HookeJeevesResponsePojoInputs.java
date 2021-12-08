/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesResponsePojoInputs.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.7.3
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
 * The &quot;<code>inputs</code>&quot; POJO representation,
 * returning as part of the response POJO.
 *
 * @version 0.7.3
 * @since   0.6.0
 */
public class HookeJeevesResponsePojoInputs {
    /** The number of variables. */
    private final int nvars;

    /** The starting point coordinates. */
    private final double[] startpt;

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
     * The accessor method for the starting point coordinates.
     *
     * @return The array of starting point coordinates.
     */
    public double[] getStartpt() {
        return startpt;
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
     * Constructs the &quot;<code>inputs</code>&quot; part
     * of the response object.
     *
     * @param _nvars   The number of variables.
     * @param _startpt The starting point coordinates.
     * @param _rho     The rho value.
     */
    public HookeJeevesResponsePojoInputs(final int      _nvars,
                                         final double[] _startpt,
                                         final double   _rho) {

        nvars   = _nvars;
        startpt = new double[nvars];
        rho     = _rho;

        for (int i = 0; i < nvars; i++) {
            startpt[i] = _startpt[i];
        }
    }
}

// vim:set nu et ts=4 sw=4:
