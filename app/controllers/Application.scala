package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.categories())
  }

  def categories = TODO

  def newCategory = TODO

  def deleteCategory(id: Long) = TODO

  val categoryForm = Form(
    "label" -> nonEmptyText
  )


}