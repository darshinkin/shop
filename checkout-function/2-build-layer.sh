#!/bin/bash
set -eo pipefail
./gradlew -q packageLibs
mv build/distributions/checkout-function.zip build/checkout-function-lib.zip