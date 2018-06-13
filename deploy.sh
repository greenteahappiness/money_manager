#!/bin/bash

#
# Note: this script is for deploying the release to Tomcat.
# You can use Jetty for development:
# 1. create a Sqlite database: ./sqlite/accounts.sqlite
# 2. run
#      $ mvn jetty:run
# 3. navigate your browser to http://localhost:8080/
#

if mvn clean compile package -DskipTests >error.log
then
  sudo cp target/money_manager-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps/
else
  echo "There were errors while executing Maven:"
  cat error.log
fi

rm error.log
