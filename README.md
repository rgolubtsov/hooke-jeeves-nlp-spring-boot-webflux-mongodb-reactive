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

The microservice is known to be built and run successfully under **Ubuntu Server (Ubuntu 20.04.4 LTS x86-64)**. Install the necessary dependencies (`openjdk-11-jdk-headless`, `make`, `mongodb`, `doxygen`):

```
$ sudo apt-get update && \
  sudo apt-get install openjdk-11-jdk-headless make mongodb doxygen -y
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
$ java -jar target/hooke-jeeves-0.8.9.jar; echo $?
...
```

## Consuming

**Store** (put) initial guess data to the database using default values:

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

**Solve** a nonlinear optimization problem using the algorithm of Hooke and Jeeves (against **Rosenbrock** or **Woods** test function):

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
