#!/usr/bin/env bash
mvn clean package
trap 'kill $(jobs -p)' SIGINT
java -jar config-server/target/config-server-1.0.0-SNAPSHOT.jar &
sleep 5
java -jar client/target/client-1.0.0-SNAPSHOT.jar &
read -p "Press enter to continue"