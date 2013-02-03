package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import anorm.{Pk, NotAssigned}
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

  def add = Action {
    implicit request =>
      categoryForm.bindFromRequest.fold(
        errors => {
          Logger.error("Error Save " + errors)
          //Redirect(routes.Application.index())
          BadRequest(views.html.index(errors, Nil, Category.all()))
        }, // BadRequest(views.html.category.form(categoryErrors)),
        category => {
          Category.save(category)
          Redirect(routes.Application.index()).flashing("success" -> "Category %s has been created".format(category.label))
        } // { Save; Ok(views.html.category.summary(bookmark) }
      )


  }

  def delete(id: Int) = TODO

  val categoryForm = Form[Category](
    mapping(
      "id" -> ignored(NotAssigned: Pk[Int]),
      "label" -> nonEmptyText(4, 50)
    )(Category.apply)(Category.unapply)
  )
}
