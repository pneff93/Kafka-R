import com.beust.klaxon.Klaxon
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.logging.log4j.kotlin.logger

class TruckEventsProducer {

    private val logger = logger(javaClass.name)

    fun produceEvents(properties: ProducerProperties, data: List<String>): Thread {

        val thread = Thread {
            val klaxon = Klaxon()
            val kafkaProducer = KafkaProducer<String, String>(properties.configureProperties())
            logger.info("Kafka Producer started")

            data.forEach { event ->

                val truck = klaxon.parse<Truck>(event)
                val dataJSON = klaxon.toJsonString(truck)

                kafkaProducer.send(ProducerRecord("truck-topic", "truck_1", dataJSON))
                logger.info("Event produced with value: $dataJSON")
                Thread.sleep(2000)
            }
        }

        thread.start()
        return thread
    }
}