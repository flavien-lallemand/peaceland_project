package analyse

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import scala.collection.immutable._
import java.io._

object Analyse {

    
    def main(args: Array[String]){
        val spark = SparkSession.builder.config("spark.master", "local").getOrCreate()
        spark.sparkContext.setLogLevel("ERROR")
        import spark.implicits._
        val sqlContext = spark.sqlContext

   

        val schema = new StructType()
                        .add("pwId", StringType)
                        .add("pwLong", StringType)
                        .add("pwLat", StringType)
                        .add("peaceScores", StringType)
                        .add("names", StringType)
                        .add("words", StringType)
                        .add("date", StringType)
                        .add("battery", StringType)
                        .add("temperature", StringType)

        val df = sqlContext.read.format("csv")
                        .option("header", "false")
                        .option("delimiter", ";")
                        .schema(schema)
                        .load("archives/csv/*.csv")
                        .withColumn("ScoresArray", split(col("peaceScores"), ","))
                        



        
        //Quel est le mot qui revient le plus souvent ?

        val words = WordMaxOccur(df)
        println()
        println("mot qui revient le plus souvent et nombre de fois")
        println(words.first()) 
        println()
        //Quel est la moyenne de peaceScore de chaque report de drone ?

        MaxTemp(df)

        //Combien de drones ont besoin d’être rechargés ? (moins de 10% de batterie)

        HowmDroneBattery(df)
        //Quels mot sont utilisés par les groupes avec un mauvais peaceScore

        BadPeaceScoreWord(df)
        


    spark.stop()

    }
           

    //Quel est le mot qui revient le plus souvent ?

    def WordMaxOccur(df: DataFrame): DataFrame = {
        df.groupBy("words")
                    .count()
                    .sort(col("count").desc)
        

    }


    //combien de drones sont à plus de 80 degrés (surchauffe) ?

    def MaxTemp(df: DataFrame): Unit = {

       val maxt = df.filter(col("temperature").cast("Integer") > 80).count
        println()
        println("nombre de drones à plus de 80 degrés : ")
        println(maxt)
        println()
                  
        
    }

    //Combien de drones ont besoin d’être rechargés ? (moins de 10% de batterie)

    def HowmDroneBattery(df: DataFrame): Unit = {
        val nb = df.filter(col("battery").cast("Integer") < 10).count
        println()
        println("Nombre de Drones ayant besoin d'être rechargés : ")
        println(nb)
        println()
        


    }

    //Quels mot sont utilisés par les groupes avec un mauvais peaceScore

    def BadPeaceScoreWord(df: DataFrame): Unit = {
        val bad = df.filter(array_min(col("ScoresArray")) < 30).select(col("words")).distinct()
        println()
        println("mots utilisés par les groupes ayant un mauvais peace score : ")
        bad.foreach(println)
        println()
    }
    

    
}







