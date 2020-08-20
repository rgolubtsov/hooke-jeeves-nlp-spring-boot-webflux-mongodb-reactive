# The Hooke and Jeeves nonlinear unconstrained minimization algorithm. Microservice

![Hooke and Jeeves NLP](static/img/doxygen/hookejeeves-doxygen-logo-260x50.png)

**A Spring Boot-based application, designed and intended to run as a microservice,
<br />implementing the nonlinear unconstrained minimization algorithm of Hooke and Jeeves**

---

**Build** and **run** the microservice:

```
$ ./mvnw clean
...
$ ./mvnw compile
...
$ ./mvnw test  # <== Optionally: This currently does nothing except emitting a huge (most likely useless) log output.
...
$ ./mvnw spring-boot:run
...
```

**Store** (put) initial guess data in the database using default values:

HTTP request param | *Rosenbrock* test problem | *Woods* test problem
------------------ | ------------------------- | --------------------
`nvars`            |  `2`                      |  `4`
`startpt0`         | `-1.2`                    | `-3`
`startpt1`         |  `1.0`                    | `-1`
`startpt2`         | &ndash;                   | `-3`
`startpt3`         | &ndash;                   | `-1`
`rho`              |  `0.5`                    |  `0.6`

```
$ curl -vXPUT http://localhost:8080/store/rosenbrock
...
$ curl -vXPUT http://localhost:8080/store/woods
...
```

or using explicit values:

```
$ curl -vXPUT 'http://localhost:8080/store/rosenbrock?nvars=2&startpt0=-0.1111&startpt1=0.2222&rho=0.5678'
...
$ curl -vXPUT 'http://localhost:8080/store/woods?nvars=4&startpt0=-4.4444&startpt1=-2.2222&startpt2=-4.4444&startpt3=-2.2222&rho=0.6789'
...
```

**Solve** a nonlinear optimization problem using the algorithm of Hooke and Jeeves (against **Rosenbrock** or **Woods** test function):

```
$ curl -v http://localhost:8080/solve      # <== GET.  Defaults to ?fx=rosenbrock
...
$ curl -v http://localhost:8080/solve?fx=rosenbrock
...
$ curl -v http://localhost:8080/solve?fx=woods
...
$ curl -vd '' http://localhost:8080/solve  # <== POST. Defaults to ?fx=rosenbrock
...
$ curl -vd '' http://localhost:8080/solve?fx=rosenbrock
...
$ curl -vd '' http://localhost:8080/solve?fx=woods
...
```

**Generate** API documentation:

```
$ javadoc @Joxyfile
...
```

or using **[Doxygen](http://doxygen.org "Doxygen")**:

```
$ doxygen
...
```
