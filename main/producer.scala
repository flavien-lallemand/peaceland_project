package main

import java.util.Properties
import org.apache.kafka.clients.producer._
import models.Message
import printer.Printer.printReport
import scala.collection.mutable.ListBuffer



import play.api.libs.json._

import models.Message
import scala.collection.mutable

object Producer {

    


    def send(message: String): Unit = {
        val props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer") 
        val producer = new KafkaProducer[String, String](props)
        println("Message to be sent:", message)



        val record = new ProducerRecord[String, String ]("peaceland",message.toString)

        producer.send(record)
        println("Record send:", record)
    }



}
