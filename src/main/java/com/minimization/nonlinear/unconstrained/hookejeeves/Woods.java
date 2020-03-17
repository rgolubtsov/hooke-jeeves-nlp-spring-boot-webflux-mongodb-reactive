/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * Woods.java
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
 * The <code>Woods</code> class is responsible for solving a nonlinear
 * optimization problem using the algorithm of Hooke and Jeeves.
 * <br />
 * <br />The objective function in this case is the so-called
 * &quot;Woods&quot; function.
 *
 * @version 0.1
 * @since   hooke-jeeves 0.1
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
    private static final double RHO_WOODS = 0.6;

    /** Helper constants. */
    private static final int    INDEX_TWO   =   2;
    private static final int    INDEX_THREE =   3;
    private static final int    ONE_HUNDRED = 100;
    private static final int    NINETY      =  90;
    private static final int    TEN         =  10;
    private static final double TEN_POINT   =  10.;
    private static final int    FOUR        =   4;
    private static final int    MINUS_THREE =  -3;
    private static final int    MINUS_ONE   =  -1;

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

        HookeJeeves.set_funevals(HookeJeeves.get_funevals() + 1);

        s1 = x[HookeJeeves.INDEX_ONE] - x[HookeJeeves.INDEX_ZERO]
                                      * x[HookeJeeves.INDEX_ZERO];
        s2 = 1                        - x[HookeJeeves.INDEX_ZERO];
        s3 = x[HookeJeeves.INDEX_ONE] - 1;

        t1 = x[INDEX_THREE] - x[INDEX_TWO]
                            * x[INDEX_TWO];
        t2 = 1              - x[INDEX_TWO];
        t3 = x[INDEX_THREE] - 1;

        t4 = s3 + t3;
        t5 = s3 - t3;

        return (ONE_HUNDRED * (s1 * s1) + s2 * s2
                   + NINETY * (t1 * t1) + t2 * t2
                   +    TEN * (t4 * t4) + t5 * t5 / TEN_POINT);
    }
}

// vim:set nu et ts=4 sw=4:
