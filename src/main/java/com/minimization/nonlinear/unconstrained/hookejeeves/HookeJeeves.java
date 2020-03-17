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
    private static int funevals = 0;

    /**
     * Getter for <code>funevals</code>.
     *
     * @return The number of function evaluations.
     */
    public static int get_funevals() {
        return funevals;
    }

    /**
     * Setter for <code>funevals</code>.
     *
     * @param __funevals The number of function evaluations.
     */
    public static void set_funevals(final int __funevals) {
        funevals = __funevals;
    }

    /**
     * Helper method.
     * <br />
     * <br />Given a point, look for a better one nearby, one coord at a time.
     *
     * @param delta      The delta between <code>prevbest</code>
     *                   and <code>point</code>.
     * @param point      The coordinate from where to begin.
     * @param prevbest   The previous best-valued coordinate.
     * @param nvars      The number of variables.
     * @param objfun_cls The class in which the objective function is defined.
     *
     * @return The objective function value at a nearby.
     */
    private double best_nearby(final double[] delta,
                               final double[] point,
                               final double   prevbest,
                               final int      nvars,
                               final Class    objfun_cls) {

        double   minf;
        double[] z = new double[VARS];
        double   ftmp;

        int i;

        minf = prevbest;

        for (i = 0; i < nvars; i++) {
            z[i] = point[i];
        }

        for (i = 0; i < nvars; i++) {
            z[i] = point[i] + delta[i];

                   if (objfun_cls.equals(Rosenbrock.class)) {
                ftmp = Rosenbrock.f(z, nvars);
            } else if (objfun_cls.equals(     Woods.class)) {
                ftmp =      Woods.f(z, nvars);
            } else {
                ftmp = 0;
            }

            if (ftmp < minf) {
                minf = ftmp;
            } else {
                delta[i] =      0.0 - delta[i];
                    z[i] = point[i] + delta[i];

                       if (objfun_cls.equals(Rosenbrock.class)) {
                    ftmp = Rosenbrock.f(z, nvars);
                } else if (objfun_cls.equals(     Woods.class)) {
                    ftmp =      Woods.f(z, nvars);
                } else {
                    ftmp = 0;
                }

                if (ftmp < minf) {
                    minf = ftmp;
                } else {
                    z[i] = point[i];
                }
            }
        }

        for (i = 0; i < nvars; i++) {
            point[i] = z[i];
        }

        return minf;
    }

    /**
     * Main optimization method.
     * <br />
     * <br />The hooke subroutine itself.
     *
     * @param nvars      The number of variables.
     * @param startpt    The starting point coordinates.
     * @param endpt      The ending point coordinates.
     * @param rho        The rho value.
     * @param epsilon    The epsilon value.
     * @param itermax    The maximum number of iterations.
     * @param objfun_cls The class in which the objective function is defined.
     *
     * @return The number of iterations used to find the local minimum.
     */
    public int hooke(final int      nvars,
                     final double[] startpt,
                     final double[] endpt,
                     final double   rho,
                     final double   epsilon,
                     final int      itermax,
                     final Class    objfun_cls) {

        int i;
        int iadj;
        int iters;
        int j;
        int keep;

        double[] newx    = new double[VARS];
        double[] xbefore = new double[VARS];
        double[] delta   = new double[VARS];

        double steplength;
        double fbefore;
        double newf;
        double tmp;

        for (i = 0; i < nvars; i++) {
            xbefore[i] =          startpt[i];
               newx[i] =          xbefore[i];
              delta[i] = Math.abs(startpt[i] * rho);

            if (delta[i] == 0.0) {
                delta[i] = rho;
            }
        }

        iadj       =   0;
        steplength = rho;
        iters      =   0;

               if (objfun_cls.equals(Rosenbrock.class)) {
            fbefore = Rosenbrock.f(newx, nvars);
        } else if (objfun_cls.equals(     Woods.class)) {
            fbefore =      Woods.f(newx, nvars);
        } else {
            fbefore = 0;
        }

        newf = fbefore;

        while ((iters < itermax) && (steplength > epsilon)) {
            iters++;
             iadj++;

            System.out.printf("\nAfter %5d funevals, f(x) =  %.4e at\n",
                funevals, fbefore);

            for (j = 0; j < nvars; j++) {
                System.out.printf("   x[%2d] = %.4e\n", j, xbefore[j]);
            }

            // Find best new point, one coord at a time.
            for (i = 0; i < nvars; i++) {
                newx[i] = xbefore[i];
            }

            newf = best_nearby(delta, newx, fbefore, nvars, objfun_cls);

            // If we made some improvements, pursue that direction.
            keep = 1;

            while ((newf < fbefore) && (keep == 1)) {
                iadj = 0;

                for (i = 0; i < nvars; i++) {
                    // Firstly, arrange the sign of delta[].
                    if (newx[i] <= xbefore[i]) {
                        delta[i] = 0.0 - Math.abs(delta[i]);
                    } else {
                        delta[i] =       Math.abs(delta[i]);
                    }

                    // Now, move further in this direction.
                    tmp        = xbefore[i];
                    xbefore[i] =    newx[i];
                       newx[i] =    newx[i] + newx[i] - tmp;
                }

                fbefore = newf;

                newf = best_nearby(delta, newx, fbefore, nvars, objfun_cls);

                // If the further (optimistic) move was bad....
                if (newf >= fbefore) {
                    break;
                }

                /*
                 * Make sure that the differences between the new and the old
                 * points are due to actual displacements; beware of roundoff
                 * errors that might cause newf < fbefore.
                 */
                keep = 0;

                for (i = 0; i < nvars; i++) {
                    keep = 1;

                    if (Math.abs(newx[i] - xbefore[i])
                        > (ZERO_POINT_FIVE * Math.abs(delta[i]))) {

                        break;
                    } else {
                        keep = 0;
                    }
                }
            }

            if ((steplength >= epsilon) && (newf >= fbefore)) {
                steplength *= rho;

                for (i = 0; i < nvars; i++) {
                    delta[i] *= rho;
                }
            }
        }

        for (i = 0; i < nvars; i++) {
            endpt[i] = xbefore[i];
        }

        return iters;
    }
}

// vim:set nu et ts=4 sw=4:
