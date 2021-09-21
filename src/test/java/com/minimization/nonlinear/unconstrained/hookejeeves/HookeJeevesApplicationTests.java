/*
 * src/test/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesApplicationTests.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.0.1
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

import static com.minimization.nonlinear.unconstrained.hookejeeves.controller.ControllerHelper.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * The test class for the microservice.
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@SpringBootTest
class HookeJeevesApplicationTests {
    /** The SLF4J logger. */
    private static final Logger l = LoggerFactory.getLogger(
        MethodHandles.lookup().lookupClass()
    );

    /** Dummy for a while... Does nothing. */
    @Test
    void contextLoads() { l.debug(BRACES, BRACES); }
}

// vim:set nu et ts=4 sw=4:
