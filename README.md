# The Hooke and Jeeves nonlinear unconstrained minimization algorithm. Microservice [![Build Status](https://travis-ci.org/rgolubtsov/hooke-jeeves-nlp-spring-boot-webflux-mongodb-reactive.svg?branch=master)](https://travis-ci.org/rgolubtsov/hooke-jeeves-nlp-spring-boot-webflux-mongodb-reactive)

![Hooke and Jeeves NLP](static/img/doxygen/hookejeeves-doxygen-logo-260x50.png)

**A Spring Boot-based application, designed and intended to be run as a microservice,
<br />implementing the nonlinear unconstrained minimization algorithm of Hooke and Jeeves**

---

## Table of Contents

* **[Building](#building)**
* **[Running](#running)**
* **[Consuming](#consuming)**

## Building

The microservice might be built and run successfully under **Ubuntu Server (Ubuntu 22.04.1 LTS x86-64)**. Install the necessary dependencies (`openjdk-11-jdk-headless`, `make`, `doxygen`):

```
$ sudo apt-get update && \
  sudo apt-get install openjdk-11-jdk-headless make doxygen -y
```

**MongoDB** has to be installed from its official website, because its support has been dropped in Ubuntu 22.04 LTS and their official repositories doesn't contain prebuilt packages of MongoDB for this Ubuntu release. The following compound command will download both MongoDB server and shell packages, and install them into the system:

```
$ curl -LOs https://repo.mongodb.org/apt/ubuntu/dists/jammy/mongodb-org/6.0/multiverse/binary-amd64/mongodb-org-server_6.0.3_amd64.deb \
       -LOs https://repo.mongodb.org/apt/ubuntu/dists/jammy/mongodb-org/6.0/multiverse/binary-amd64/mongodb-mongosh_1.6.0_amd64.deb && \
  sudo dpkg -i mongodb-org-server_6.0.3_amd64.deb \
               mongodb-mongosh_1.6.0_amd64.deb
...
```

Then it needs to create a dedicated directory where MongoDB server should store and manage databases. By default it should be `/var/lib/mongodb/`:

```
$ sudo mkdir -p /var/lib/mongodb && \
  sudo chown -R mongodb:mongodb /var/lib/mongodb
```

Start up MongoDB server and then check its execution status:

```
$ sudo systemctl start mongod
$
$ systemctl status mongod -l
● mongod.service - MongoDB Database Server
     Loaded: loaded (/lib/systemd/system/mongod.service; disabled; vendor preset: enabled)
     Active: active (running) since Wed 2022-11-23 17:50:30 +03; 2min 3s ago
       Docs: https://docs.mongodb.org/manual
   Main PID: 6766 (mongod)
     Memory: 65.5M
        CPU: 2.213s
     CGroup: /system.slice/mongod.service
             └─6766 /usr/bin/mongod --config /etc/mongod.conf

Nov 23 17:50:30 <hostname> systemd[1]: Started MongoDB Database Server.
```

**Build** the microservice using **Maven Wrapper**:

```
$ ./mvnw clean
...
$ ./mvnw compile
...
$ ./mvnw test  # <== Optional. This currently does nothing except emitting a huge (most likely useless) log output.
...
$ ./mvnw package
...
```

(**Note:** the `package` target above includes `test`.)

Or **build** the microservice using **GNU Make** (optional, but for convenience &mdash; it covers the same **Maven Wrapper** build workflow under the hood):

```
$ make clean
...
$ make      # <== Compilation phase.
...
$ make test
...
$ make jar
...
$ make docs
...
$ make all  # <== Or make all the targets at one pass: compile, jar, docs (except test).
...
```

**Generate** API documentation: `$ javadoc @Joxyfile`, or using **[Doxygen](http://doxygen.org "Doxygen")**: `$ doxygen`. (**Note:** the `docs` target above covers both variants.)

## Running

**Run** the microservice using **Maven Wrapper** (generally for development and debugging purposes):

```
$ ./mvnw spring-boot:run; echo $?
$ #                     ^   ^   ^
$ #                     |   |   |
$ # --------------------+---+---+
$ # Whilst this is not necessary, it's beneficial knowing the exit code.
...
```

**Run** the microservice using its all-in-one JAR file, built previously by the `package` or `jar` targets:

```
$ java -jar target/hooke-jeeves-0.8.11.jar; echo $?
...
```

## Consuming

Suppose MongoDB database server is up and running, then the following communication with the microservice is available from a user side:

1. **Store** (put) initial guess data to the database using default values:

HTTP request param | *Rosenbrock* test problem | *Woods* test problem
------------------ | ------------------------- | --------------------
`nvars`            |  `2`                      |  `4`
`startpt0`         | `-1.2`                    | `-3`
`startpt1`         |  `1.0`                    | `-1`
`startpt2`         | &ndash;                   | `-3`
`startpt3`         | &ndash;                   | `-1`
`rho`              |  `0.5`                    |  `0.6`

```
$ curl -vXPUT http://localhost:8765/store/rosenbrock
...
$ curl -vXPUT http://localhost:8765/store/woods
...
```

or using explicit values:

```
$ curl -vXPUT 'http://localhost:8765/store/rosenbrock?nvars=2&startpt0=-0.1111&startpt1=0.2222&rho=0.5678'
...
$ curl -vXPUT 'http://localhost:8765/store/woods?nvars=4&startpt0=-4.4444&startpt1=-2.2222&startpt2=-4.4444&startpt3=-2.2222&rho=0.6789'
...
```

2. **Solve** a nonlinear optimization problem using the algorithm of Hooke and Jeeves (against **Rosenbrock** or **Woods** test function):

```
$ curl -v http://localhost:8765/solve      # <== GET.  Defaults to ?fx=rosenbrock
...
$ curl -v http://localhost:8765/solve?fx=rosenbrock
...
$ curl -v http://localhost:8765/solve?fx=woods
...
$ curl -vd '' http://localhost:8765/solve  # <== POST. Defaults to ?fx=rosenbrock
...
$ curl -vd '' http://localhost:8765/solve?fx=rosenbrock
...
$ curl -vd '' http://localhost:8765/solve?fx=woods
...
```
