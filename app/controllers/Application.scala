package controllers

import java.io.{File, FileOutputStream}

import org.apache.commons.io.IOUtils
import play.api.Play.current
import play.api.mvc.{Action, Controller}
import play.api.{Logger, Play}

object Application extends Controller {

  val outputFile = new File(Play.configuration.getString("output-file").get)
  val os = new FileOutputStream(outputFile, true)

  def call(path: String) = Action(parse.tolerantText) { implicit request =>
    val message = s"${request.method} /$path ${request.rawQueryString} ${request.body}"
    IOUtils.write(message+"\n", os)
    Logger.info(message)

    Ok("")
  }
}
