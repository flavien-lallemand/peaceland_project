package main 
import models.Message



import play.api.libs.json._


import scala.io.Source  // from the Scala standard library
import java.time._


object Main {


  def main(args: Array[String]): Unit = {

    val absPath = "./ressources/"
    val fileName = absPath + "data_sample.json"

    val random = scala.util.Random

    val test = Source.fromFile(fileName).mkString
   
    val json: JsValue = Json.parse(test)

    println("---ici-----------")
    val messagesList = json.as[List[Message]]

    messagesList.foreach((x : Message) => {
      println(Json.stringify(Json.toJson(x)))
      Producer.send(Json.stringify(Json.toJson(x)))

      //Add delay to simulate differents drones sending reports not at the exact same time ! :)
      
      Thread.sleep(random.nextInt(5000)) 
    })

  }
}
