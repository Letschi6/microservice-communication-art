#!/usr/bin/env bash
mvn clean package
trap 'kill $(jobs -p)' SIGINT
java -jar classic/target/classic-1.0.0-SNAPSHOT.jar &
java -jar graphql-service/target/graphql-service-1.0.0-SNAPSHOT.jar &
read -p "Press enter to continue"