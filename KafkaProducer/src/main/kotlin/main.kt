import kotlinx.coroutines.runBlocking

fun main() {

    val eventsProducer = TruckEventsProducer()
    val properties = ProducerProperties()
    val data = Truck::class.java.getResource("/TruckData.txt").readText().split("\n")

    runBlocking {

        while (true) {

            val thread = eventsProducer.produceEvents(properties, data)
            thread.join()
        }
    }
}