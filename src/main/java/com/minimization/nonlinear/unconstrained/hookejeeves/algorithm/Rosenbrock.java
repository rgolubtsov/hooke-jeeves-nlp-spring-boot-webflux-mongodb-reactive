/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * algorithm/Rosenbrock.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.8.10
 * ============================================================================
 * A Spring Boot-based application, designed and intended to be run
 * as a microservice, implementing the nonlinear unconstrained
 * minimization algorithm of Hooke and Jeeves.
 * ============================================================================
 * Written by Radislav (Radicchio) Golubtsov, 2020-2022
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves.algorithm;

/**
 * The <code>Rosenbrock</code> class is responsible for solving a nonlinear
 * optimization problem using the algorithm of Hooke and Jeeves.
 * <br />
 * <br />The objective function in this case is the Rosenbrock's parabolic
 * valley function.
 *
 * @version 0.8.10
 * @since   0.0.1
 */
public final class Rosenbrock {
    /** The stepsize geometric shrink. */
    public static final double RHO_BEGIN = 0.5;

    /**
     * The user-supplied objective function <code>f(x,n)</code>.
     * <br />
     * <br />Represents here the <b>Rosenbrock</b>'s classic parabolic valley
     * (&quot;banana&quot;) function.
     *
     * @param x The point at which f(x) should be evaluated.
     * @param n The number of coordinates of x.
     *
     * @return The objective function value.
     */
    public static final double f(final double[] x, final int n) {
        double a;
        double b;
        double c;

        a = x[0];
        b = x[1];

        c = 100.0 * (b - (a * a)) * (b - (a * a));

        return (c + ((1.0 - a) * (1.0 - a)));
    }
}

// vim:set nu et ts=4 sw=4:
