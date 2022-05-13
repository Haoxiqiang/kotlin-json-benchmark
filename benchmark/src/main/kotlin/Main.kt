import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.kotlin.json.eishay.EishayTest
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

val globalMoshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val globalGson: Gson = GsonBuilder().create()

val globalJackson: ObjectMapper = ObjectMapper().registerModule(
    KotlinModule.Builder()
        .build()
)

val content by lazy {
    EishayTest::class.java.classLoader
        .getResourceAsStream("data/eishay_compact.json")
        ?.bufferedReader()
        ?.use { bufferedReader ->
            bufferedReader.readText()
        } ?: ""
}

fun main(args: Array<String>) {

    println(content)

    // one shot benchmark
    val oneShotOptions: Options = OptionsBuilder()
        .include(EishayTest::class.java.name)
        .mode(Mode.SingleShotTime)
        .timeUnit(TimeUnit.NANOSECONDS)
        .forks(1)
        .build()
    Runner(oneShotOptions).run()

    // throughput benchmark
    val options: Options = OptionsBuilder()
        .include(EishayTest::class.java.name)
        .mode(Mode.Throughput)
        .timeUnit(TimeUnit.MILLISECONDS)
        .forks(1)
        .build()
    Runner(options).run()
}