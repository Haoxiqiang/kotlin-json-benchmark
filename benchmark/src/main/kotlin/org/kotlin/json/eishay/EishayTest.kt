package org.kotlin.json.eishay

import com.alibaba.fastjson.JSON
import com.dslplatform.json.DslJson
import com.dslplatform.json.runtime.Settings
import content
import globalGson
import globalJackson
import globalMoshi
import org.json.JSONObject
import org.kotlin.json.Eishay
import org.kotlin.json.Image
import org.kotlin.json.Media
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
open class EishayTest {

    @Benchmark
    @Throws(Exception::class)
    fun fastjson2(bh: Blackhole) {
        val obj = JSON.parseObject(content, Eishay::class.java)
        bh.consume(obj)
    }

    @Benchmark
    @Throws(Exception::class)
    fun moshi(bh: Blackhole) {
        val obj = globalMoshi.adapter(Eishay::class.java).fromJson(content)
        bh.consume(obj)
    }

    @Benchmark
    @Throws(Exception::class)
    fun gson(bh: Blackhole) {
        val obj = globalGson.fromJson(content, Eishay::class.java)
        bh.consume(obj)
    }

    @Benchmark
    @Throws(Exception::class)
    fun jackson(bh: Blackhole) {
        val obj = globalJackson.readValue(content, Eishay::class.java)
        bh.consume(obj)
    }

    @Benchmark
    @Throws(Exception::class)
    fun origin(bh: Blackhole) {
        val json = JSONObject(content)
        val images = json.getJSONArray("images")

        val objList = mutableListOf<Image>()
        for (index in 0 until images.length()) {
            val child = images.getJSONObject(index)
            val width = child.getInt("width")
            val height = child.getInt("height")
            val size = child.getString("size")
            val title = child.getString("title")
            val uri = child.getString("uri")
            val imageChild = Image(height = height, size = size, title = title, uri = uri, width = width)
            objList.add(imageChild)
        }

        val media = json.getJSONObject("media")

        val bitrate = media.getLong("bitrate")
        val duration = media.getLong("duration")
        val size = media.getLong("size")
        val format = media.getString("format")
        val player = media.getString("player")
        val title = media.getString("title")
        val uri = media.getString("uri")
        val height = media.getInt("height")
        val width = media.getInt("width")
        val persons = media.getJSONArray("persons")
        val personList = mutableListOf<String>()
        for (index in 0 until persons.length()) {
            val child = persons.getString(index)
            personList.add(child)
        }
        val mediaObj = Media(
            bitrate = bitrate,
            duration = duration,
            format = format,
            height = height,
            player = player,
            size = size,
            title = title,
            uri = uri,
            width = width,
            persons = personList
        )
        val obj = Eishay(
            images = objList,
            media = mediaObj,
        )
        bh.consume(obj)
    }

    @Benchmark
    @Throws(Exception::class)
    fun dslJson(bh: Blackhole) {
        //include service loader will load up classes created via annotation processor
        val settings = Settings.withRuntime<Any>()
            .includeServiceLoader()
        val dslJson = DslJson(settings)
        val obj = dslJson.deserialize(Eishay::class.java, content.toByteArray(), content.length)
        bh.consume(obj)
    }
}