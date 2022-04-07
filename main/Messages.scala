package models

import play.api.libs.json.{JsPath, Reads,JsString, JsResult, JsError,Json, __}
import scala.util.{ Try}

import play.api.libs.functional.syntax._

case class Citizens(name: String, peaceScore: Int)
object Citizens {
    implicit val reads: Reads[Citizens] = (
      (JsPath \ "name").read[String] and
      (JsPath \ "peaceScore").read[Int]
        
      )(Citizens.apply _)

      implicit val StatusWrites = Json.writes[Citizens]

  }

case class Message (
	  pwId: Int,
    pwLong: String,
    pwLat: String,
    citizensAndScores: Array[Citizens],
    words: String,
    date: String,
    battery: Int,
    temperature: Int,
		)


    object Message {

    implicit val reads: Reads[Message] = (
      (JsPath \ "pwId").read[Int] and
        (JsPath \ "pwLong").read[String]  and
        (JsPath \ "pwLat").read[String] and
        (JsPath \ "citizensAndScores").read[Array[Citizens]] and
        (JsPath \ "words").read[String] and
        (JsPath \ "date").read[String] and
        (JsPath \ "battery").read[Int] and
        (JsPath \ "temperature").read[Int]
      )(Message.apply _)

      implicit val StatusWrites = Json.writes[Message]


  }


