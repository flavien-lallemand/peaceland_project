package streamk

import java.util.Arrays
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.io.File
import com.github.tototoshi.csv._
import models.Message
import javax.annotation.processing.Messager

object Consumer {

    def main(args: Array[String]): Unit = {
        consumeFromKafka("peaceland")
    }

    def consumeFromKafka(topic: String) = {
        val props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
        props.put("auto.offset.reset", "latest")
        props.put("group.id", "consumer-group")
        
        val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String,String](props)

        try {
            consumer.subscribe(Arrays.asList(topic))
            consume(consumer)
        } catch {
            case e: Exception => e.printStackTrace()
        } finally {
            consumer.close()
        }
    }


    def write_alert(data : String): Unit = {
        val writer = CSVWriter.open("alert.csv", append = true)
        writer.writeRow(data.toString)
        writer.close()
    }


    def consume(consumer: KafkaConsumer[String, String ]): Unit = {
        val record = consumer.poll(1000).asScala
        record.iterator.foreach(data => println(data.value()))

        consume(consumer)
    }
}
