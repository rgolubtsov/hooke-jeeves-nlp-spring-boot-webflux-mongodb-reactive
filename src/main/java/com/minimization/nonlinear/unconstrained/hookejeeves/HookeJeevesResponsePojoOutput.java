/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesResponsePojoOutput.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.6.9
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
 * The &quot;<code>output</code>&quot; POJO representation,
 * returning as part of the response POJO.
 *
 * @version 0.6.9
 * @since   0.6.0
 */
public class HookeJeevesResponsePojoOutput {
    /** The number of iterations. */
    private final int iters;

    /** The ending point coordinates. */
    private final double[] endpt;

    /** The objective function value. */
    private final double f;

    /**
     * The accessor method for the number of iterations.
     *
     * @return The number of iterations.
     */
    public int getIters() {
        return iters;
    }

    /**
     * The accessor method for the ending point coordinates.
     *
     * @return The array of ending point coordinates.
     */
    public double[] getEndpt() {
        return endpt;
    }

    /**
     * The accessor method for the objective function value.
     *
     * @return The objective function value.
     */
    public double getF() {
        return f;
    }

    /**
     * Constructs the &quot;<code>output</code>&quot; part
     * of the response object.
     *
     * @param _nvars The number of variables.
     * @param _iters The number of iterations.
     * @param _endpt The ending point coordinates.
     * @param _f     The objective function value.
     */
    public HookeJeevesResponsePojoOutput(final int      _nvars,
                                         final int      _iters,
                                         final double[] _endpt,
                                         final double   _f) {

        iters = _iters;
        endpt = new double[_nvars];
        f     = _f;

        for (int i = 0; i < _nvars; i++) {
            endpt[i] = _endpt[i];
        }
    }
}

// vim:set nu et ts=4 sw=4:
