/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * Rosenbrock.java
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
 * The <code>Rosenbrock</code> class is responsible for solving a nonlinear
 * optimization problem using the algorithm of Hooke and Jeeves.
 * <br />
 * <br />The objective function in this case is the Rosenbrock's parabolic
 * valley function.
 *
 * @version 0.1
 * @since   hooke-jeeves 0.1
 */
public final class Rosenbrock {
    /** Constant. The stepsize geometric shrink. */
    private static final double RHO_BEGIN = 0.5;

    /** Helper constants. */
    private static final double ONE_HUNDRED_POINT_ZERO = 100.0;
    private static final double ONE_POINT_ZERO         =   1.0;
    private static final int    TWO                    =   2;
    private static final double MINUS_ONE_POINT_TWO    =  -1.2;

    /**
     * The user-supplied objective function f(x,n).
     * <br />
     * <br />Represents here the Rosenbrock's classic parabolic valley
     * (&quot;banana&quot;) function.
     *
     * @param x The point at which f(x) should be evaluated.
     * @param n The number of coordinates of <code>x</code>.
     *
     * @return The objective function value.
     */
    public static double f(final double[] x, final int n) {
        double a;
        double b;
        double c;

        HookeJeeves.set_funevals(HookeJeeves.get_funevals() + 1);

        a = x[HookeJeeves.INDEX_ZERO];
        b = x[HookeJeeves.INDEX_ONE ];

        c = ONE_HUNDRED_POINT_ZERO * (b - (a * a)) * (b - (a * a));

        return (c + ((ONE_POINT_ZERO - a) * (ONE_POINT_ZERO - a)));
    }
}

// vim:set nu et ts=4 sw=4: