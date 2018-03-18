#!/bin/bash

if mvn clean compile package -DskipTests >error.log
then
  sudo cp target/money_manager-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps/
else
  echo "There were errors while executing Maven:"
  cat error.log
fi

rm error.log
