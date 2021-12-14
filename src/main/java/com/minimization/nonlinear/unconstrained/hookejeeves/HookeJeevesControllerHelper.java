/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesControllerHelper.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.7.6
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.InputStream;

import java.util.Properties;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;

import org.bson.Document;

/**
 * The helper class for the controller and the related ones.
 *
 * @version 0.7.6
 * @since   0.0.1
 */
public class HookeJeevesControllerHelper {
    // Helper constants.
    public static final String EMPTY_STRING =     "";
    public static final String EQUALS       =    "=";
    public static final String BRACES       =   "{}";
    public static final String SPACE        =    " ";
    public static final String V_BAR        =    "|";
    public static final String DBG_PREF     = "==> ";
    public static final String NEW_LINE     = System.lineSeparator();

    // Extra helper constants.
    private static final String YES = "yes";
    private static final int TWELVE = 12;

    // Common error messages.
    public static final String ERR_DBG_LOG_UNABLE_TO_ACTIVATE
        = "Unable to activate debug log.";
    public static final String ERR_DBL_VALUE_SCALE_UNABLE_TO_GET
        = "Unable to get scaling factor for double-precision value.";
    public static final String ERR_REQ_PARAMS_ROSENBROCK_NEEDS_TWO_VARS
        = "The Rosenbrock function requires exactly two starting point "
        + "coordinates. Please check your inputs.";
    public static final String ERR_REQ_PARAMS_WOODS_NEEDS_FOUR_VARS
        = "The Woods function requires exactly four starting point "
        + "coordinates. Please check your inputs.";
    public static final String ERR_REQ_PARAMS_OBJFUN_UNKNOWN
        = "Objective function name provided is unknown: "
        + "it must be set as 'rosenbrock' for the Rosenbrock function, "
        + "or 'woods' for the Woods function. Please check your inputs.";
    public static final String ERR_FROM_DB_VARS_NUMBERS_EXPECTED
        = "The number of variables (coordinates) must take positive integer "
        + "values, starting point coordinates must take floating-point "
        + "values, the rho factor must take floating-point values. "
        + "Please check incoming data from DB.";

    /** The application properties filename. */
    private static final String APP_PROPS = "application.properties";

    // Application properties keys for the logger.
    private static final String DBG_LOG_IO_ENABLED
        = "logger.debug.io.enabled";
    private static final String DBG_LOG_INTERIM_ENABLED
        = "logger.debug.intermediate.enabled";

    // Application properties keys for double-precision
    // values-related manipulations.
    private static final String DBL_VALUE_SCALE
        = "value.double-precision.scaling-factor";

    // Logging messages used in Reactive Streams Subscriber classes.
    private static final String PUT_ON_COMPLETE
        = "Data successfully sent.";
    private static final String PUT_ON_ERROR
        = "Error occurred in sending data:";
    private static final String GET_ON_COMPLETE
        = "Data successfully received.";
    private static final String GET_ON_ERROR
        = "Error occurred in receiving data:";

    /** The SLF4J logger. */
    private static final Logger l = LoggerFactory.getLogger(
        MethodHandles.lookup().lookupClass()
    );

    /**
     * The <code>Subscriber</code>-based class, intended to be operational
     * regarding <b>PUT</b> REST endpoints.
     */
    public static class PutSubscriber<Document_>
             implements    Subscriber<Document_> {

        private static final String PUT_SUBSCRIBER_S
                                  = PutSubscriber.class.getSimpleName() + "'s";

        /**
         * Invoked after calling <code>Publisher.subscribe(Subscriber)</code>.
         * <br />
         * <br />No data will start flowing
         * until <code>Subscription.request(long)</code> is invoked.
         *
         * @param s Subscription that allows requesting data
         *          via Subscription.request(long).
         */
        @Override
        public void onSubscribe(final Subscription s) {
            s.request(1L); // <== Such a value is also valid
                           //     to be effectively "hot".))
//          s.request(Long.MAX_VALUE);

            l.debug(DBG_PREF + PUT_SUBSCRIBER_S
                  + SPACE    + "onSubscribe() called:"
                  + SPACE    + BRACES, s);
        }

        /**
         * Data notification sent by the <code>Publisher</code>
         * in response to requests to <code>Subscription.request(long)</code>.
         *
         * @param document_ The element signaled.
         */
        @Override
        public void onNext(final Document_ document_) {
            // Dummy for a while...

            l.debug(DBG_PREF + PUT_SUBSCRIBER_S
                  + SPACE    + "onNext() called:"
                  + SPACE    + BRACES, document_);
        }

        /**
         * Successful terminal state.
         * <br />
         * <br />No further events will be sent
         * even if <code>Subscription.request(long)</code> is invoked again.
         */
        @Override
        public void onComplete() {
            l.info(PUT_ON_COMPLETE);
        }

        /**
         * Failed terminal state.
         * <br />
         * <br />No further events will be sent
         * even if <code>Subscription.request(long)</code> is invoked again.
         *
         * @param t The throwable signaled.
         */
        @Override
        public void onError(final Throwable t) {
            l.error(PUT_ON_ERROR, t);
        }
    }

    /**
     * The <code>Subscriber</code>-based class, intended to be operational
     * regarding <b>GET</b> and <b>POST</b> REST endpoints.
     */
    public static class GetSubscriber<Document_>
                extends PutSubscriber<Document_> {

        private static final String GET_SUBSCRIBER_S
                                  = GetSubscriber.class.getSimpleName() + "'s";

        /** The threads' countdown latch. */
        private final CountDownLatch latch;

        /** The BSON document. */
        private Document document;

        /**
         * Data notification sent by the <code>Publisher</code>
         * in response to requests to <code>Subscription.request(long)</code>.
         *
         * @param document_ The element signaled (canonically).
         *                  (Currently this is the BSON document instance.)
         */
        @Override
        public void onNext(final Document_ document_) {
            document = (org.bson.Document) document_;

            l.debug(DBG_PREF + GET_SUBSCRIBER_S
                  + SPACE    + "onNext() called:"
                  + SPACE    + BRACES, document);
        }

        /**
         * Successful terminal state.
         * <br />
         * <br />No further events will be sent
         * even if <code>Subscription.request(long)</code> is invoked again.
         */
        @Override
        public void onComplete() {
            latch.countDown();

            l.info(GET_ON_COMPLETE);
        }

        /**
         * Failed terminal state.
         * <br />
         * <br />No further events will be sent
         * even if <code>Subscription.request(long)</code> is invoked again.
         *
         * @param t The throwable signaled.
         */
        @Override
        public void onError(final Throwable t) {
            onComplete();

            l.error(GET_ON_ERROR, t);
        }

        /**
         * Causes the current thread to wait until the latch
         * has counted down to zero, unless the thread is interrupted.
         */
        public void await() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            l.debug(DBG_PREF + GET_SUBSCRIBER_S
                  + SPACE    + "await() called.");
        }

        /**
         * Accessor method for the <code>document</code>.
         *
         * @return The BSON document.
         */
        public Document getDocument() {
            l.debug(DBG_PREF + GET_SUBSCRIBER_S
                  + SPACE    + "getDocument() called:"
                  + SPACE    + BRACES, document);

            return document;
        }

        /** Constructor for the <code>GetSubscriber</code> class. */
        public GetSubscriber() {
            latch = new CountDownLatch(1);

            l.debug(DBG_PREF + GET_SUBSCRIBER_S
                  + SPACE    + "constructor called:"
                  + SPACE    + BRACES, latch.getCount());
        }
    }

    /**
     * Identifies whether data I/O debug logging is enabled by retrieving
     * the corresponding setting from application properties.
     *
     * @return <code>true</code> if data I/O debug logging is enabled,
     *         <code>false</code> otherwise.
     */
    public static boolean is_dbg_log_io_enabled() {
        Properties props = _get_props(ERR_DBG_LOG_UNABLE_TO_ACTIVATE);

        String dbg_log_io_enabled = props.getProperty(DBG_LOG_IO_ENABLED);

        if ((dbg_log_io_enabled != null)
            && (dbg_log_io_enabled.compareTo(YES) == 0)) { return true; }

        return false;
    }

    /**
     * Identifies whether intermediate (computational) debug logging is enabled
     * by retrieving the corresponding setting from application properties.
     *
     * @return <code>true</code> if intermediate (computational) debug logging
     *         is enabled, <code>false</code> otherwise.
     */
    public static boolean is_dbg_log_interim_enabled() {
        Properties props = _get_props(ERR_DBG_LOG_UNABLE_TO_ACTIVATE);

        String dbg_log_interim_enabled
            = props.getProperty(DBG_LOG_INTERIM_ENABLED);

        if ((dbg_log_interim_enabled != null)
            && (dbg_log_interim_enabled.compareTo(YES) == 0)) { return true; }

        return false;
    }

    /**
     * Scales down the given double-precision value
     * using the specified scaling factor.
     *
     * @param value The double-precision value to scale it down.
     * @param scale The scaling factor for the given double-precision value.
     *
     * @return The double-precision value, a scaled down one.
     */
    public static double scale_down_double_value(final double value,
                                                 final int    scale) {

        return BigDecimal.valueOf(value).setScale((scale > TWELVE) ? TWELVE :
                                  scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Retrieves the scaling factor for a double-precision value
     * from application properties.
     *
     * @return The scaling factor for a double-precision value.
     */
    public static int get_scaling_factor() {
        Properties props = _get_props(ERR_DBL_VALUE_SCALE_UNABLE_TO_GET);

        String dbl_value_scale = props.getProperty(DBL_VALUE_SCALE);

        if (dbl_value_scale != null) {
            return Integer.parseInt(dbl_value_scale); // <== May throw NFE !
        }

        return TWELVE;
    }

    // Helper method. Used to get the application properties object.
    private static final Properties _get_props(final String error_msg) {
        Properties props = new Properties();

        ClassLoader loader
            = HookeJeevesControllerHelper.class.getClassLoader();

        InputStream data = loader.getResourceAsStream(APP_PROPS);

        try {
            props.load(data);
            data.close();
        } catch (java.io.IOException e) {
            l.error(error_msg);
        }

        return props;
    }
}

// vim:set nu et ts=4 sw=4:
