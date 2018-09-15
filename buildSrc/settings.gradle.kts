import java.io.FileInputStream
import java.util.Properties

file("../gradle.properties").transferProperties()

fun File.transferProperties() {
    FileInputStream(this@transferProperties).use { inputStream ->
        with(Properties()) {
            load(inputStream)
            forEach { property: Map.Entry<Any, Any> ->
                property.transferProperty()
            }
        }
    }
}

fun Map.Entry<Any, Any>.transferProperty() =
        (gradle as ExtensionAware).extensions.extraProperties.set(this.key as String, this.value.toString())

fun File.readProperties(getFunction: Properties.() -> Unit) {
    FileInputStream(this@readProperties).use { inputStream ->
        with(Properties()) {
            load(inputStream)
            getFunction(this@with)
        }
    }
}