#!/bin/bash

echo "Starting to clone the repository"
repository=https://github.com/ballerina-platform/ballerina-distribution.git
localFolder="`dirname $0`"/../build/cloned-repo

mkdir "$localFolder"
git clone "$repository" "$localFolder"