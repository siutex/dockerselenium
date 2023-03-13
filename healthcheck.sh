#!/usr/bin/env bash

echo "Checking if hub is ready - $HUB_HOST"

while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

# start the java command
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* \
    org.testng.TestNG -testclass cucumberOptions.TestNGTestRunner \
    -DHUB_HOST=$HUB_HOST \
    -DBROWSER=$BROWSER