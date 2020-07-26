```
...
[INFO] --- spring-boot-maven-plugin:2.2.5.RELEASE:run (default-cli) @ hooke-jeeves ---
[INFO] Attaching agents: []
[2020-07-26][23:00:32][INFO ]  Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
[2020-07-26][23:00:33][INFO ]  Opened connection [connectionId{localValue:1, serverValue:433}] to localhost:27017
[2020-07-26][23:00:33][INFO ]  Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[2, 6, 10]}, minWireVersion=0, maxWireVersion=2, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=1761448}
[2020-07-26][23:00:33][INFO ]  HV000001: Hibernate Validator 6.0.18.Final

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.5.RELEASE)

[2020-07-26][23:00:33][INFO ]  Starting HookeJeevesApplication on radicv144 with PID 30484 (/home/radic/dev/misc/github/hooke-jeeves-nlp-spring-boot-webflux-mongodb-reactive/target/classes started by radic in /home/radic/dev/misc/github/hooke-jeeves-nlp-spring-boot-webflux-mongodb-reactive)
[2020-07-26][23:00:33][DEBUG]  Running with Spring Boot v2.2.5.RELEASE, Spring v5.2.4.RELEASE
[2020-07-26][23:00:33][INFO ]  No active profile set, falling back to default profiles: default
[2020-07-26][23:00:34][INFO ]  Bootstrapping Spring Data Reactive MongoDB repositories in DEFAULT mode.
[2020-07-26][23:00:34][INFO ]  Finished Spring Data repository scanning in 20ms. Found 0 Reactive MongoDB repository interfaces.
[2020-07-26][23:00:34][INFO ]  Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
[2020-07-26][23:00:34][INFO ]  Finished Spring Data repository scanning in 2ms. Found 0 MongoDB repository interfaces.
[2020-07-26][23:00:35][INFO ]  Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
[2020-07-26][23:00:35][INFO ]  Opened connection [connectionId{localValue:2, serverValue:434}] to localhost:27017
[2020-07-26][23:00:35][INFO ]  Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[2, 6, 10]}, minWireVersion=0, maxWireVersion=2, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=2703599}
[2020-07-26][23:00:35][INFO ]  Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
[2020-07-26][23:00:35][INFO ]  Opened connection [connectionId{localValue:3, serverValue:435}] to localhost:27017
[2020-07-26][23:00:35][INFO ]  Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[2, 6, 10]}, minWireVersion=0, maxWireVersion=2, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=4068457}
[2020-07-26][23:00:36][INFO ]  Netty started on port(s): 8080
[2020-07-26][23:00:36][INFO ]  Started HookeJeevesApplication in 2.844 seconds (JVM running for 3.528)

[2020-07-26][23:00:53][DEBUG]  GET   fx=rosenbrock
[2020-07-26][23:00:53][INFO ]  Opened connection [connectionId{localValue:4, serverValue:436}] to localhost:27017
[2020-07-26][23:00:53][DEBUG]  GET   nvars=2 | startpt0=-0.1111 | startpt1=0.2222 | rho=0.5678
...
```
