/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * Woods.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.0.1
 * ============================================================================
 * Written by Radislav (Radicchio) Golubtsov, 2020
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

/**
 * The <code>Woods</code> class is responsible for solving a nonlinear
 * optimization problem using the algorithm of Hooke and Jeeves.
 * <br />
 * <br />The objective function in this case is the so-called
 * &quot;Woods&quot; function.
 *
 * @version 0.0.1
 * @since   hooke-jeeves 0.0.1
 */
public final class Woods {
    /**
     * Constant. The stepsize geometric shrink.
     * <br />
     * <br />The Hooke &amp; Jeeves algorithm works reasonably well
     * on Rosenbrock's function, but can fare worse on some standard
     * test functions, depending on rho. Here is an example that works well
     * when rho = 0.5, but fares poorly with rho = 0.6, and better again
     * with rho = 0.8.
     */
    public static final double RHO_WOODS = 0.6;

    /**
     * The user-supplied objective function f(x,n).
     * <br />
     * <br />Woods &ndash; a la More, Garbow &amp; Hillstrom
     * (TOMS algorithm 566).
     *
     * @param x The point at which f(x) should be evaluated.
     * @param n The number of coordinates of <code>x</code>.
     *
     * @return The objective function value.
     */
    public static double f(final double[] x, final int n) {
        double s1;
        double s2;
        double s3;

        double t1;
        double t2;
        double t3;
        double t4;
        double t5;

        s1 = x[1] - x[0] * x[0];
        s2 = 1    - x[0];
        s3 = x[1] - 1;

        t1 = x[3] - x[2] * x[2];
        t2 = 1    - x[2];
        t3 = x[3] - 1;

        t4 = s3 + t3;
        t5 = s3 - t3;

        return (100 * (s1 * s1) + s2 * s2
               + 90 * (t1 * t1) + t2 * t2
               + 10 * (t4 * t4) + t5 * t5 / 10.);
    }
}

// vim:set nu et ts=4 sw=4:
