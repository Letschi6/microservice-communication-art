#!/usr/bin/env bash
mvn clean package
trap 'kill $(jobs -p)' SIGINT
~/tools/kafka_2.12-1.0.0/bin/zookeeper-server-start.sh ~/tools/kafka_2.12-1.0.0/config/zookeeper.properties &
sleep 10
~/tools/kafka_2.12-1.0.0/bin/kafka-server-start.sh ~/tools/kafka_2.12-1.0.0/config/server.properties &
sleep 10
java -jar config-server/target/config-server-1.0.0-SNAPSHOT.jar &
sleep 15
java -Dspring.application.name=eureka-primary -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar &
java -Dspring.application.name=eureka-secondary -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar &
java -Dspring.application.name=eureka-tertiary -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar &
sleep 30
java -jar turbine-server/target/turbine-server-1.0.0-SNAPSHOT.jar &
java -Dspring.application.name=eureka-client-operand -jar client-response/target/client-response-1.0.0-SNAPSHOT.jar &
java -Dspring.application.name=eureka-client-operator -jar client-response/target/client-response-1.0.0-SNAPSHOT.jar &
sleep 30
java -Dspring.application.name=eureka-client-root -jar client-request/target/client-request-1.0.0-SNAPSHOT.jar &
sleep 60
java -jar zuul-server/target/zuul-server-1.0.0-SNAPSHOT.jar &
read -p "Press enter to continue"
