```
...
[INFO] --- spring-boot-maven-plugin:2.2.5.RELEASE:run (default-cli) @ hooke-jeeves ---
[INFO] Attaching agents: []
23:51:24.296 [main] INFO org.mongodb.driver.cluster - Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
23:51:24.342 [main] DEBUG org.mongodb.driver.cluster - Updating cluster description to  {type=UNKNOWN, servers=[{address=localhost:27017, type=UNKNOWN, state=CONNECTING}]
23:51:24.411 [cluster-ClusterId{value='5f18445c231f9d3647dba7a7', description='null'}-localhost:27017] INFO org.mongodb.driver.connection - Opened connection [connectionId{localValue:1, serverValue:146}] to localhost:27017
23:51:24.412 [cluster-ClusterId{value='5f18445c231f9d3647dba7a7', description='null'}-localhost:27017] DEBUG org.mongodb.driver.cluster - Checking status of localhost:27017
23:51:24.472 [cluster-ClusterId{value='5f18445c231f9d3647dba7a7', description='null'}-localhost:27017] DEBUG org.mongodb.driver.protocol.command - Sending command '{"ismaster": 1}' with request id 4 to database admin on connection [connectionId{localValue:1, serverValue:146}] to server localhost:27017
23:51:24.480 [cluster-ClusterId{value='5f18445c231f9d3647dba7a7', description='null'}-localhost:27017] DEBUG org.mongodb.driver.protocol.command - Execution of command with request id 4 completed successfully in 63.08 ms on connection [connectionId{localValue:1, serverValue:146}] to server localhost:27017
23:51:24.486 [cluster-ClusterId{value='5f18445c231f9d3647dba7a7', description='null'}-localhost:27017] INFO org.mongodb.driver.cluster - Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[2, 6, 10]}, minWireVersion=0, maxWireVersion=2, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=70116820}
23:51:24.489 [cluster-ClusterId{value='5f18445c231f9d3647dba7a7', description='null'}-localhost:27017] DEBUG org.mongodb.driver.cluster - Updating cluster description to  {type=STANDALONE, servers=[{address=localhost:27017, type=STANDALONE, roundTripTime=70.1 ms, state=CONNECTED}]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.5.RELEASE)

2020-07-22 23:51:25.033  INFO 18104 --- [           main] c.m.n.u.h.HookeJeevesApplication         : Starting HookeJeevesApplication on radicv144 with PID 18104 (/home/radic/dev/misc/github/hooke-jeeves-nlp-spring-boot-webflux-mongodb-reactive/target/classes started by radic in /home/radic/dev/misc/github/hooke-jeeves-nlp-spring-boot-webflux-mongodb-reactive)
2020-07-22 23:51:25.035  INFO 18104 --- [           main] c.m.n.u.h.HookeJeevesApplication         : No active profile set, falling back to default profiles: default
2020-07-22 23:51:25.850  INFO 18104 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Reactive MongoDB repositories in DEFAULT mode.
2020-07-22 23:51:25.878  INFO 18104 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 21ms. Found 0 Reactive MongoDB repository interfaces.
2020-07-22 23:51:25.887  INFO 18104 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2020-07-22 23:51:25.890  INFO 18104 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 2ms. Found 0 MongoDB repository interfaces.
2020-07-22 23:51:26.788  INFO 18104 --- [           main] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
2020-07-22 23:51:26.815  INFO 18104 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:147}] to localhost:27017
2020-07-22 23:51:26.818  INFO 18104 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[2, 6, 10]}, minWireVersion=0, maxWireVersion=2, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=1581316}
2020-07-22 23:51:27.129  INFO 18104 --- [           main] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
2020-07-22 23:51:27.383  INFO 18104 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:148}] to localhost:27017
2020-07-22 23:51:27.387  INFO 18104 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[2, 6, 10]}, minWireVersion=0, maxWireVersion=2, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=2956450}
2020-07-22 23:51:27.411  INFO 18104 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8080
2020-07-22 23:51:27.417  INFO 18104 --- [           main] c.m.n.u.h.HookeJeevesApplication         : Started HookeJeevesApplication in 2.819 seconds (JVM running for 3.513)

fx=rosenbrock
2020-07-22 23:51:35.819  INFO 18104 --- [or-http-epoll-2] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:4, serverValue:149}] to localhost:27017
nvars=2
startpt0=-0.1111
startpt1=0.2222
rho=0.5678
...
```
