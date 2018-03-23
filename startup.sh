#!/usr/bin/env bash
#mvn clean package
trap 'kill $(jobs -p)' SIGINT
~/tools/kafka_2.12-1.0.0/bin/zookeeper-server-start.sh ~/tools/kafka_2.12-1.0.0/config/zookeeper.properties &
sleep 10
~/tools/kafka_2.12-1.0.0/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.0/config/server.properties &
~/tools/kafka_2.12-1.0.0/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.0/config/server2.properties &
~/tools/kafka_2.12-1.0.0/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.0/config/server3.properties &
sleep 10
~/tools/confluent-4.0.0/bin/schema-registry-start  ~/tools/confluent-4.0.0/etc/schema-registry/schema-registry.properties
sleep 10

read -p "Press enter to continue"