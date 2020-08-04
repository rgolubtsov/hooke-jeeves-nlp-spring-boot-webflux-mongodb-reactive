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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import org.bson.Document;

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

    /** The SLF4J logger. */
    private static final Logger l = LoggerFactory.getLogger(
        MethodHandles.lookup().lookupClass()
    );

    /**
     * The <code>Subscriber</code> class, intended to be operational
     * regarding <b>PUT</b> REST endpoints.
     */
    public static class PutSubscriber<Document>
             implements    Subscriber<Document> {

        /**
         * Invoked after calling <code>Publisher.subscribe(Subscriber)</code>.
         * <br />
         * <br />No data will start flowing
         * until <code>Subscription.request(long)</code> is invoked.
         *
         * @param s <code>Subscription</code> that allows requesting data
         *          via <code>Subscription.request(long)</code>.
         */
        @Override
        public void onSubscribe(final Subscription s) {
            s.request(1L); // <== Such a value is also valid
                           //     to be effectively "hot".))
//          s.request(Long.MAX_VALUE);
        }

        /**
         * Data notification sent by the <code>Publisher</code>
         * in response to requests to <code>Subscription.request(long)</code>.
         *
         * @param document_ The element signaled.
         */
        @Override
        public void onNext(final Document document_) {
            // Dummy for a while...
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

    /** The BSON document. */
    public static Document document;

    /**
     * The <code>Subscriber</code> class, intended to be operational
     * regarding <b>GET</b> and <b>POST</b> REST endpoints.
     */
    public static class GetSubscriber<Document>
                extends PutSubscriber<Document> {

        /**
         * Data notification sent by the <code>Publisher</code>
         * in response to requests to <code>Subscription.request(long)</code>.
         *
         * @param document_ The element signaled (canonically).
         *                  (Currently this is the BSON document instance.)
         */
        @Override
        public void onNext(final Document  document_) {
            document = (org.bson.Document) document_;
        }

        /**
         * Successful terminal state.
         * <br />
         * <br />No further events will be sent
         * even if <code>Subscription.request(long)</code> is invoked again.
         */
        @Override
        public void onComplete() {
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
            l.error(GET_ON_ERROR, t);
        }
    }
}

// vim:set nu et ts=4 sw=4:
