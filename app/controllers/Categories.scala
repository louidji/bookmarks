package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Category

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
object Categories extends Controller {
  def categories = TODO

  def add = TODO

  def delete(id: Long) = TODO

  val categoryForm = Form(
    mapping(
      "id" -> longNumber,
      "label" -> nonEmptyText
    )(Category.apply)(Category.unapply)
  )
}
