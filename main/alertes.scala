package alertes

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming._
import models.Message
import java.sql.Struct
import play.api.libs.json._


object Alertes {

  def main(args: Array[String]) {

    val spark = SparkSession
      .builder
      .appName("Peaceland")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    
    val subschema = new StructType()
      .add("name", StringType)
      .add("peaceScore", StringType)
    
      
    val schema = new StructType()
      .add("pwId", StringType)
      .add("pwLong", StringType)
      .add("pwLat", StringType)
      .add("citizensAndScores",ArrayType(subschema)) 
      .add("words", StringType)
      .add("date", StringType)
      .add("battery", StringType)
      .add("temperature", StringType)


   
    
    

        val data = spark.readStream
            .format("org.apache.spark.sql.kafka010.KafkaSourceProvider")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "peaceland")
      .option("startingOffsets", "latest")
      .option("failOnDataLoss", false)
      .load()
      .selectExpr("CAST(value AS STRING)")
      .select(from_json($"value", schema).as("message"))
      .select("message.pwId","message.pwLong","message.pwLat","message.CitizensAndScores","message.date")

      .filter(array_min(($"citizensAndScores.peaceScore")) < "30")
      .writeStream
      .format("console")
      .option("truncate", false)
      .start()
      .awaitTermination()


     

 
  }


}
