import com.example.marsphotos.network.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


/**
 * The base URL for the Mars photos web service.
 */
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

/**
 * A Retrofit object configured with [BASE_URL] and a JSON converter factory.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * A service interface for the Mars photos web service.
 * This interface defines a method to get Mars photos.
 */
interface MarsApiService {
    /**
     * Get a list of Mars photos from the web service.
     * This method is a suspending function, which means it can be paused and resumed,
     * for example, for network calls.
     * @return A list of [MarsPhoto] objects.
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

/**
 * An object that provides a lazy instance of [MarsApiService].
 * The instance will be created the first time it is accessed.
 */
object MarsApi {
    /**
     * A lazy instance of [MarsApiService].
     */
    val retrofitService : MarsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
        retrofit.create(MarsApiService::class.java)
    }
}