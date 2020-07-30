/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * ControllerHelper.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.0.1
 * ============================================================================
 * Copyright (C) 2020 Radislav (Radicchio) Golubtsov
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

/** The helper for the controller class and related ones. */
public class ControllerHelper {
    // Helper constants.
    public static final String EQUALS   =  "=";
    public static final String BRACES   = "{}";
    public static final String SPACE    =  " ";
    public static final String V_BAR    =  "|";
    public static final String NEW_LINE = System.lineSeparator();

    // Logging messages used in Reactive Streams Subscriber classes.
    public static final String PUT_ON_COMPLETE = "Data successfully sent.";
    public static final String PUT_ON_ERROR    = "Error occurred in sending data:";
    public static final String GET_ON_COMPLETE = "Data successfully received.";
    public static final String GET_ON_ERROR    = "Error occurred in receiving data:";
}

// vim:set nu et ts=4 sw=4:
