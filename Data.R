
install.packages("rkafka")
install.packages("jsonlite")
library(jsonlite)
library(rkafka)

# Create Kafka Consumer object
consumer <- rkafka.createSimpleConsumer(kafkaServerURL = "broker",
                                        kafkaServerPort = "9092",
                                        connectionTimeOut = "10000",
                                        kafkaProducerBufferSize = "100000",
                                        clientId = "truck-topic")

data <- data.frame()
requiredMessage <- 100000

for(i in 0:requiredMessage){

  # Get data out of Kafka
  rkafka.receiveFromSimpleConsumer(SimpleConsumerObj = consumer,
                                   topicName = "truck-topic",
                                   partition = "0",
                                   Offset = i,
                                   msgReadSize = "10000")

  currentMessage <- rkafka.readFromSimpleConsumer(SimpleConsumerObj = consumer)

  # Create data frame
  currentMessageConverted <- as.data.frame(fromJSON(txt = currentMessage))
  data <- rbind(data, currentMessageConverted)
}
