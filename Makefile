#!/usr/bin/env make -f

GRADLE = ./gradlew


.PHONY: deploy
deploy: deploy/postgresql deploy/kafka deploy/ftgo

.PHONY: deploy/postgresql
deploy/postgresql:
	kubectl apply -f deployment/postgresql/postgresql-init.yaml
	skaffold run -f deployment/postgresql/skaffold.yaml

.PHONY: deploy/kafka
deploy/kafka:
	skaffold run -f deployment/kafka/skaffold.yaml

.PHONY: deploy/ftgo
deploy/ftgo:
	skaffold run -f deployment/ftgo/skaffold.yaml


.PHONY: undeploy
undeploy: undeploy/postgresql undeploy/kafka undeploy/ftgo

.PHONY: undeploy/postgresql
undeploy/postgresql:
	skaffold delete -f deployment/postgresql/skaffold.yaml
	kubectl delete -f deployment/postgresql/postgresql-init.yaml

.PHONY: undeploy/kafka
undeploy/kafka:
	skaffold delete -f deployment/kafka/skaffold.yaml

.PHONY: undeploy/ftgo
undeploy/ftgo:
	skaffold delete -f deployment/ftgo/skaffold.yaml


.PHONY: build
build:
	$(GRADLE) build

.PHONY: clean
clean:
	$(GRADLE) clean