package controllers

import play.api._
import models.Category

//import i18n.Messages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  def index = Action {
    implicit request =>
    Ok(views.html.index(Categories.categoryForm, Nil, Category.all()))
  }



}