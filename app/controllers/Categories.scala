package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import anorm.{Pk, NotAssigned}
import models.{Bookmark, Category}

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
object Categories extends Controller {
  def categories = Action {
    implicit request =>
      Ok(views.html.category(Categories.categoryForm,  Category.all()))
  }


  def create = Action {
    implicit request =>
      categoryForm.bindFromRequest.fold(
        errors => {
          Logger.error("Error Save " + errors)
          //Redirect(routes.Application.index())
          BadRequest(views.html.category(errors,  Category.all()))
        }, // BadRequest(views.html.bookmark.form(categoryErrors)),
        category => {
          Category.save(category)
          Redirect(routes.Categories.categories()).flashing("success" -> "Category %s has been created".format(category.label))
        } // { Save; Ok(views.html.bookmark.summary(bookmark) }
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
