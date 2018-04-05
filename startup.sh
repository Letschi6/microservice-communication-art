#!/usr/bin/env bash
mvn clean package
trap 'kill $(jobs -p)' SIGINT
~/tools/kafka_2.12-1.0.1/bin/zookeeper-server-start.sh ~/tools/kafka_2.12-1.0.1/config/zookeeper.properties &
sleep 10
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server.properties &
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server2.properties &
~/tools/kafka_2.12-1.0.1/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.1/config/server3.properties &
sleep 20
java -jar producer/target/producer-1.0.0-SNAPSHOT.jar &
java -jar consumer/target/consumer-1.0.0-SNAPSHOT.jar &
java -Dserver.port=8201 -jar consumer/target/consumer-1.0.0-SNAPSHOT.jar &
read -p "Press enter to continue"