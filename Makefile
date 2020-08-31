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

SERV = target
TEST = test
DOCS = docs
API  = $(DOCS)/api
DOX  = $(DOCS)/doxygen
HTML = $(DOX)/html

DOXYGEN_BACKGROUND_IMG = \
static/img/doxygen/hookejeeves-doxygen-background-795x195.png

# Specify flags and other vars here.
MAVEN_W = ./mvnw
JAVADOC = javadoc
JDFLAGS = @Joxyfile
DOXYGEN = doxygen
CP      = cp
CPFLAGS = -vf
RMFLAGS = -vR

# Making the first target (the microservice itself).
$(SERV):
	$(MAVEN_W) compile

# Making the second target (tests).
$(TEST):
	$(MAVEN_W) $(TEST)

# Making the third target (API docs).
$(API):
	$(JAVADOC) $(JDFLAGS)
$(DOX):
	$(DOXYGEN)
	$(CP) $(CPFLAGS) $(DOXYGEN_BACKGROUND_IMG) $(HTML)/
$(DOCS): $(API) $(DOX)

.PHONY: all clean

all: $(SERV) $(TEST) $(DOCS)

clean:
	$(MAVEN_W) clean
	$(RM) $(RMFLAGS) $(API) $(DOX)

# vim:set nu ts=4 sw=4:
