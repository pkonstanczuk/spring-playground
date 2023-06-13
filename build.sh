#!/usr/bin/env bash

export BASE_DIR=$(cd $(dirname $0) && pwd)

source "${BASE_DIR}/build-config.sh"

# shellcheck disable=SC2120
tests() {
  gradle test
}

format() {
  _log-target "formatting"
  kformat
}

lint() {
  _log-target "linting"
  klint
}

kformat() {
  gradle ktlintFormat --parallel
}

klint() {
  gradle ktlintCheck --parallel
}

build-dist() {
  _log-target "build-dist"
  gradle assemble --parallel
}

generateStubs() {
  _log-target "generate stubs"
  gradle openApiGenerate
}

bootRun() {
  _log-target "bootRun"
  gradle bootRun
}

clean() {
  _log-target "bootRun"
  gradle clean
}


all() {
  clean
  generateStubs
  kformat
  tests
  build-dist
#  bootRun
}

gradle() {
  _log-target "gradle $*"
  local gradle_cmd
  gradle_cmd="${BASE_DIR}/gradlew --project-dir=${BASE_DIR} $(if [ "${DEBUG:-}" == 1 ]; then echo -n "--debug"; fi)"
  $gradle_cmd "${@:-tasks}"
}

_log-target() {
  echo "------- $1 -------"
}

#####
## Your build logic goes _above_ this line.
#####
"${@:-}"
