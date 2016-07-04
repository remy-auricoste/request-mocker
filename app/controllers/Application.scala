package controllers

import java.io.{File, FileOutputStream}

import org.apache.commons.io.IOUtils
import play.api.Play
import play.api.Play.current
import play.api.mvc.{Action, Controller}

object Application extends Controller {

  val outputFile = new File(Play.configuration.getString("output-file").get)
  val os = new FileOutputStream(outputFile, true)

  def call(path: String) = Action(parse.tolerantText) { implicit request =>
    val message = s"${request.method} /$path ${request.rawQueryString} ${request.body.hashCode} ${request.body.replaceAll("\n", " ")}"
    IOUtils.write(message+"\n", os)
//    Logger.info(message)
    path match {
      case string: String if string.startsWith("v2/partners/") => if (path.hashCode % 2 == 0) Ok else InternalServerError("random error")
      case string: String if string.contains("_search") => Ok("{}")
      case _ => Ok("unknown path")
    }
  }
}
