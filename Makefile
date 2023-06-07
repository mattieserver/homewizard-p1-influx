_BUILD_ARGS_TAG ?= latest
_BUILD_ARGS_RELEASE_TAG ?= latest
_BUILD_ARGS_DOCKERFILE ?= Dockerfile

_DOCKER_REPO ?= ghcr.io
 
_builder:
	docker build --tag ${_DOCKER_REPO}/mattieserver/homewizard-p1-influx:collector-${_BUILD_ARGS_TAG} -f  src/collector/Dockerfile src/collector

_pusher:
	docker push ${_DOCKER_REPO}/mattieserver/homewizard-p1-influx:collector-${_BUILD_ARGS_TAG}



build:
	$(MAKE) _builder

push:
	$(MAKE) _pusher