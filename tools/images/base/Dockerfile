ARG UBUNTU_VERSION=22.04
ARG UBUNTU_HASH="@sha256:94db6b944510db19c0ff5eb13281cf166abfe6f9e01a6f8e716e976664537c60"

FROM ubuntu:${UBUNTU_VERSION}${UBUNTU_HASH}

LABEL org.opencontainers.image.vendor=Elide
LABEL org.opencontainers.image.title="Elide Base"
LABEL org.opencontainers.image.description="Base image for Elide apps and tools"
LABEL org.opencontainers.image.version=v1.0.0-alpha10
LABEL org.opencontainers.image.url=https://github.com/elide-dev/elide
LABEL org.opencontainers.image.base.name=ubuntu:${UBUNTU_VERSION}
LABEL org.opencontainers.image.source=https://github.com/elide-dev/elide/blob/main/tools/images/base/Dockerfile

RUN apt-get update \
  && apt-get -y upgrade \
  && DEBIAN_FRONTEND=noninteractive apt-get -y install --no-install-recommends \
    libc6 \
    zlib1g \
  && apt-get remove perl \
  && apt-get autoremove \
  && rm -rf /var/lib/apt/lists/*

ENV LANG=en_US.UTF-8 \
  LANGUAGE=en_US \
  PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/lib/gvm/bin:/sbin:/bin
