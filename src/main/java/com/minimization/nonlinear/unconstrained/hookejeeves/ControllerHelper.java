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
    public static final String EQUALS   =    "=";
    public static final String BRACES   =   "{}";
    public static final String SPACE    =    " ";
    public static final String V_BAR    =    "|";
    public static final String DBG_PREF = "==> ";
    public static final String NEW_LINE = System.lineSeparator();

    // Logging messages used in Reactive Streams Subscriber classes.
    private static final String PUT_ON_COMPLETE = "Data successfully sent.";
    private static final String PUT_ON_ERROR    = "Error occurred in sending data:";
    private static final String GET_ON_COMPLETE = "Data successfully received.";
    private static final String GET_ON_ERROR    = "Error occurred in receiving data:";

    /** The SLF4J logger. */
    private static final Logger l = LoggerFactory.getLogger(
        MethodHandles.lookup().lookupClass()
    );

    /**
     * The <code>Subscriber</code> class, intended to be operational
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
         * @param s <code>Subscription</code> that allows requesting data
         *          via <code>Subscription.request(long)</code>.
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
                  + SPACE    + BRACES, document_.toString());
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
     * The <code>Subscriber</code> class, intended to be operational
     * regarding <b>GET</b> and <b>POST</b> REST endpoints.
     */
    public static class GetSubscriber<Document_>
                extends PutSubscriber<Document_> {

        private static final String GET_SUBSCRIBER_S
                                  = GetSubscriber.class.getSimpleName() + "'s";

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

        public void await() {
            l.debug(DBG_PREF + GET_SUBSCRIBER_S
                  + SPACE    + "await() called.");
        }

        /**
         * Getter for <code>document</code>.
         *
         * @return The BSON document.
         */
        public Document getDocument() {
            l.debug(DBG_PREF + GET_SUBSCRIBER_S
                  + SPACE    + "getDocument() called:"
                  + SPACE    + BRACES, document);

            return document;
        }
    }
}

// vim:set nu et ts=4 sw=4:
