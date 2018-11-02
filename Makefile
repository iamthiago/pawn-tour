
VERSION = $(shell git rev-parse --short HEAD | tr -d "\n")
COMPANY = thiagoandrade6
PROJECT_NAME = pawn-tour
TAG = $(COMPANY)/$(PROJECT_NAME):$(VERSION)

#########
# Tasks #
#########

# Build application (fat jar)
build:
	$(_sbt-cmd) universal:packageZipTarball

# Build docker image
image: build
	- docker build --tag $(TAG) .
	- docker build --tag $(COMPANY)/$(PROJECT_NAME) .

test:
	$(_sbt-cmd) test

_sbt-cmd := docker run --rm -it \
		-v $(PWD):/target \
		-v $(HOME)/.ivy2:/root/.ivy2 \
		-v $(HOME)/.m2:/root/.m2 \
		-w /target \
		-e VERSION=$(VERSION) \
		hseeberger/scala-sbt:latest sbt