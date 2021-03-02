#!/usr/bin/env make -f

GRADLE = ./gradlew


.PHONY: build
build:
	$(GRADLE) build

.PHONY: clean
clean:
	$(GRADLE) clean