#
# Makefile
# =============================================================================
# The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
# Microservice. Version 0.6.0
# =============================================================================
# A Spring Boot-based application, designed and intended to be run
# as a microservice, implementing the nonlinear unconstrained
# minimization algorithm of Hooke and Jeeves.
# =============================================================================
# Copyright (C) 2020-2021 Radislav (Radicchio) Golubtsov
#
# (See the LICENSE file at the top of the source tree.)
#

SERV = target
TEST = test
JAR  = jar
DOCS = docs
API  = $(DOCS)/api
DOX  = $(DOCS)/doxygen
HTML = $(DOX)/html

DOXYGEN_BACKGROUND_IMG = \
static/img/doxygen/hookejeeves-doxygen-background-795x195.png

# Specify flags and other vars here.
MAVEN_W    = ./mvnw
JAVADOC    = javadoc
JDFLAGS    = @Joxyfile
DOXYGEN    = doxygen
ECHO       = @echo
SKIP_TESTS = -DskipTests
CP         = cp
CPFLAGS    = -vf
RMFLAGS    = -vR

# Making the first target (the microservice itself).
$(SERV):
	$(MAVEN_W) compile
	$(ECHO)

# Making the second target (tests).
$(TEST):
	$(MAVEN_W) $(TEST)

# Making the third target (runnable JAR file).
$(JAR):
	$(MAVEN_W) package $(SKIP_TESTS)
	$(ECHO)

# Making the fourth target (API docs).
$(API):
	$(JAVADOC) $(JDFLAGS)
	$(ECHO)
$(DOX):
	$(DOXYGEN)
	$(ECHO)
	$(CP) $(CPFLAGS) $(DOXYGEN_BACKGROUND_IMG) $(HTML)/
$(DOCS): $(API) $(DOX)

.PHONY: all clean

all: $(SERV) $(JAR) $(DOCS)

clean:
	$(MAVEN_W) clean
	$(RM) $(RMFLAGS) $(API) $(DOX)

# vim:set nu ts=4 sw=4:
