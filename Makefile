GIT_HASH ?= $(shell git log --format="%h" -n 1)

_BUILD_ARGS_TAG ?= ${GIT_HASH}
_DOCKER_REPO ?= ghcr.io/mattieserver/homewizard-p1-influx

 
_builder:
	docker build --tag ${_DOCKER_REPO}:collector-${_BUILD_ARGS_TAG} -f src/collector/Dockerfile src/collector

_pusher:
	docker push ${_DOCKER_REPO}:collector-${_BUILD_ARGS_TAG}



build:
	$(MAKE) _builder

push:
	$(MAKE) _pusher