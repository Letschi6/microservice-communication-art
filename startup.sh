#!/usr/bin/env bash
trap 'kill $(jobs -p)' SIGINT
~/tools/kafka_2.12-1.0.1/bin/zookeeper-server-start.sh ~/tools/kafka_2.12-1.0.1/config/zookeeper.properties &
sleep 10
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server.properties &
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server2.properties &
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server3.properties &
sleep 20
~/tools/confluent-4.0.0/bin/schema-registry-start  ~/tools/confluent-4.0.0/etc/schema-registry/schema-registry.properties
sleep 10
#mvn clean package
#java -jar producer/target/producer-1.0.0-SNAPSHOT.jar
#java -jar consumer/target/consumer-1.0.0-SNAPSHOT.jar

read -p "Press enter to continue"