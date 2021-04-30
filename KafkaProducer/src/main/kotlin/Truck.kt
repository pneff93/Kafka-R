import java.text.SimpleDateFormat
import java.util.*

data class Truck(
    val longitude: Double,
    val latitude: Double,
    val speed_kmh: Double,
    val timestamp: String? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'").format(Date())
)
