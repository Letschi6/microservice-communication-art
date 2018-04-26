#!/usr/bin/env bash
trap 'kill $(jobs -p)' SIGINT
~/tools/kafka_2.12-1.0.1/bin/zookeeper-server-start.sh ~/tools/kafka_2.12-1.0.1/config/zookeeper.properties &
sleep 10
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server.properties &
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server2.properties &
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server3.properties &
sleep 20
~/tools/confluent-4.1.0/bin/schema-registry-start  ~/tools/confluent-4.1.0/etc/schema-registry/schema-registry.properties
sleep 20
mvn clean package

read -p "Press enter to continue"