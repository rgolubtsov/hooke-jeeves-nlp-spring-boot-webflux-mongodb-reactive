/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesApp.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.8.9
 * ============================================================================
 * A Spring Boot-based application, designed and intended to be run
 * as a microservice, implementing the nonlinear unconstrained
 * minimization algorithm of Hooke and Jeeves.
 * ============================================================================
 * Copyright (C) 2020-2022 Radislav (Radicchio) Golubtsov
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.reactivestreams.client.MongoCollection;

import java.util.Properties;

import static com.minimization.nonlinear.unconstrained.hookejeeves.HookeJeevesControllerHelper.*;

/**
 * The startup class of the microservice.
 *
 * @version 0.8.9
 * @since   0.0.1
 */
@SpringBootApplication
public class HookeJeevesApp {
    /** The database name to connect to. */
    private static final String TEST_DATABASE = "test";

    /** The collection name to get the collection. */
    private static final String INITIAL_DATA_COLL = "hooke_initial_guess_data";

    /** The collection object to store data to. */
    public static MongoCollection collection;

    /** The application properties object. */
    public static Properties props;

    /** The data I/O debug logging enabler. */
    public static boolean dbg_log_io_enabled;

    /** The intermediate (computational) debug logging enabler. */
    public static boolean dbg_log_interim_enabled;

    /** The scaling factor for a double-precision value. */
    public static int scaling_factor;

    /**
     * The microservice entry point.
     *
     * @param args The array of command-line arguments.
     */
    public static void main(final String[] args) {
        // Making the connection to the database and getting the collection.
        MongoClient   client   = MongoClients.create();
        MongoDatabase database = client.getDatabase(TEST_DATABASE);

        collection = database.getCollection(INITIAL_DATA_COLL);

        // Getting the application properties object.
        props = _get_props();

        // Identifying whether debug logging is enabled.
        dbg_log_io_enabled      = is_dbg_log_io_enabled();
        dbg_log_interim_enabled = is_dbg_log_interim_enabled();

        // Getting the scaling factor for a double-precision value
        // from application properties.
        scaling_factor = get_scaling_factor();

        // Starting up the app.
        SpringApplication.run(HookeJeevesApp.class, args);
    }
}

// vim:set nu et ts=4 sw=4:
