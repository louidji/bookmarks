package controllers

import play.api._
import i18n.Messages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  def index = Action {
    //Ok(views.html.index("Your new application is ready."))
    Ok(Messages("home.title"))
  }


}