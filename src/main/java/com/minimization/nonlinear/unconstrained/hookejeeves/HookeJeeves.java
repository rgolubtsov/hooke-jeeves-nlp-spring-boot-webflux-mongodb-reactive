/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeeves.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.1
 * ============================================================================
 * Written by Radislav (Radicchio) Golubtsov, 2020
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

/**
 * The <code>HookeJeeves</code> class contains methods for solving a nonlinear
 * optimization problem using the algorithm of Hooke and Jeeves.
 * @version 0.1
 * @since   hooke-jeeves 0.1
 */
public class HookeJeeves {
    /** Constant. The maximum number of variables. */
    public static final int VARS = 250;

    /** Constant. The ending value of stepsize. */
    public static final double EPSMIN = 1E-6;

    /** Constant. The maximum number of iterations. */
    public static final int IMAX = 5000;

    /** Helper constants. */
    public  static final int    INDEX_ZERO      = 0;
    public  static final int    INDEX_ONE       = 1;
    private static final double ZERO_POINT_FIVE = 0.5;

    /** The number of function evaluations. */
    private static int funEvals = 0;

    /**
     * Getter for <code>funEvals</code>.
     *
     * @return The number of function evaluations.
     */
    public static int getFunEvals() {
        return funEvals;
    }

    /**
     * Setter for <code>funEvals</code>.
     *
     * @param __funEvals The number of function evaluations.
     */
    public static void setFunEvals(final int __funEvals) {
        funEvals = __funEvals;
    }
}

// vim:set nu et ts=4 sw=4:
