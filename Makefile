#
# Makefile
# =============================================================================
# The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
# Microservice. Version 0.0.1
# =============================================================================
# A Spring Boot-based application, designed and intended to run
# as a microservice, implementing the nonlinear unconstrained
# minimization algorithm of Hooke and Jeeves.
# =============================================================================
# Copyright (C) 2020 Radislav (Radicchio) Golubtsov
#
# (See the LICENSE file at the top of the source tree.)
#

MSVC = target
TEST = test
DOCS = docs/api     \
       docs/doxygen

# Specify flags and other vars here.
MAVEN_W = ./mvnw
JAVADOC = javadoc
JDFLAGS = @Joxyfile
DOXYGEN = doxygen
RMFLAGS = -vR

# Making the first target (the microservice itself).
$(MSVC):
	$(MAVEN_W) compile

# Making the second target (tests).
$(TEST):
	$(MAVEN_W) $(TEST)

# Making the third target (API docs).
$(DOCS):
	$(JAVADOC) $(JDFLAGS)
	$(DOXYGEN)

.PHONY: all clean

all: $(MSVC) $(TEST) $(DOCS)

clean:
	$(MAVEN_W) clean
	$(RM) $(RMFLAGS) $(DOCS)

# vim:set nu ts=4 sw=4:
