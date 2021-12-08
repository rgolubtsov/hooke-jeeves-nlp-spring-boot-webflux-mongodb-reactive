/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesResponsePojoError.java
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
 * The POJO representation, returning in the response
 * when erroneous behavior is occurred.
 *
 * @version 0.7.3
 * @since   0.6.6
 */
public class HookeJeevesResponsePojoError {
    /** The error message. */
    private final String error;

    /**
     * The accessor method for the error message.
     *
     * @return The error message.
     */
    public String getError() {
        return error;
    }

    /**
     * The effective constructor.
     *
     * @param _error The error message.
     */
    public HookeJeevesResponsePojoError(final String _error) {
        error = _error;
    }
}

// vim:set nu et ts=4 sw=4:
