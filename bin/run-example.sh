#!/usr/bin/env bash

#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#
# Shell script for running the example application

# Determine the current working directory
_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

function find_resource {
  local app_version=`grep "<version>" "${_DIR}/../pom.xml" | head -n1 | awk -F '[<>]' '{print $3}'`
  local jar_file="lljvm-example_${app_version}-with-dependencies.jar"
  local built_jar="${_DIR}/../target/${jar_file}"
  if [ -e "$built_jar" ]; then
    RESOURCE=${built_jar}
  else
    RESOURCE="${_DIR}/../assembly/${jar_file}"
    echo "${built_jar} not found, so use pre-compiled ${RESOURCE}" 1>&2
  fi
}

# Resolves a jar location
find_resource

# Invokes it
java -cp ${RESOURCE} io.github.maropu.LLJVMExample

