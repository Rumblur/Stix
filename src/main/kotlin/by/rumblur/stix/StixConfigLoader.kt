package by.rumblur.stix

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.nio.file.Files
import kotlin.reflect.KClass

object StixConfigLoader {

    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    fun getGson() = this.gson

    fun<T : Any> save(dataFolder: File, type: KClass<T>, name: String){
        dataFolder.mkdirs()
        val file = File(dataFolder, "$name.json")
        val json = gson.toJson(type.java.getDeclaredConstructor().newInstance())
        Files.write(file.toPath(), json.toByteArray(Charsets.UTF_8))
    }

    fun saveInstance(dataFolder: File, instance: Any, name: String) {
        dataFolder.mkdirs()
        val file = File(dataFolder, "$name.json")
        val json = gson.toJson(instance)
        Files.write(file.toPath(), json.toByteArray(Charsets.UTF_8))
    }

    fun <T : Any> load(dataFolder: File, type: KClass<T>, name: String) : T {
        val file = File(dataFolder, "$name.json")
        if (!file.exists()) save(dataFolder, type, name)
        val stringConfig = file.readText(Charsets.UTF_8)
        return gson.fromJson(stringConfig, type.java)
    }

}