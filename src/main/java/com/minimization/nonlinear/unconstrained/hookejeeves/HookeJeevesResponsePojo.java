/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesResponsePojo.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.8.11
 * ============================================================================
 * A Spring Boot-based application, designed and intended to be run
 * as a microservice, implementing the nonlinear unconstrained
 * minimization algorithm of Hooke and Jeeves.
 * ============================================================================
 * Copyright (C) 2020-2025 Radislav (Radicchio) Golubtsov
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

/**
 * The POJO representation, returning in the response.
 *
 * @version 0.8.11
 * @since   0.6.0
 */
public class HookeJeevesResponsePojo {
    /**
     * The &quot;<code>inputs</code>&quot; POJO,
     * returning as part of the response POJO.
     */
    private final HookeJeevesResponsePojoInputs inputs;

    /**
     * The &quot;<code>output</code>&quot; POJO,
     * returning as part of the response POJO.
     */
    private final HookeJeevesResponsePojoOutput output;

    /**
     * The accessor method for the &quot;<code>inputs</code>&quot; POJO,
     * returning as part of the response POJO.
     *
     * @return The &quot;<code>inputs</code>&quot; POJO,
     *         returning as part of the response POJO.
     */
    public HookeJeevesResponsePojoInputs getInputs() {
        return inputs;
    }

    /**
     * The accessor method for the &quot;<code>output</code>&quot; POJO,
     * returning as part of the response POJO.
     *
     * @return The &quot;<code>output</code>&quot; POJO,
     *         returning as part of the response POJO.
     */
    public HookeJeevesResponsePojoOutput getOutput() {
        return output;
    }

    /**
     * Constructs the response object.
     *
     * @param _inputs The &quot;<code>inputs</code>&quot; POJO,
     *                returning as part of the response POJO.
     * @param _output The &quot;<code>output</code>&quot; POJO,
     *                returning as part of the response POJO.
     */
    public HookeJeevesResponsePojo(final HookeJeevesResponsePojoInputs _inputs,
                                   final HookeJeevesResponsePojoOutput _output)
    {
        inputs = _inputs;
        output = _output;
    }
}

// vim:set nu et ts=4 sw=4:
