/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesApplication.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.0.1
 * ============================================================================
 * Copyright (C) 2020 Radislav (Radicchio) Golubtsov
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

/** The startup class of the microservice. */
@SpringBootApplication
public class HookeJeevesApplication {
    /** Constant: The database name to connect to. */
    private static final String TEST_DATABASE = "test";

    /** Constant: The collection name to get the collection. */
    private static final String INITIAL_DATA_COLL = "hooke_initial_guess_data";

    /** The collection object to store data to. */
    static MongoCollection collection;

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

        SpringApplication.run(HookeJeevesApplication.class, args);
    }
}

// vim:set nu et ts=4 sw=4:
