package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import anorm.{Pk, NotAssigned}
import models.{Bookmark, Category}
import play.api.i18n.Messages

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
      Ok(views.html.addCategory(Categories.categoryForm, Category.all(), Application.username(request)))
  }


  def create = Action {
    implicit request =>
      categoryForm.bindFromRequest.fold(
        errors => {
          Logger.error("Error Save " + errors)
          //Redirect(routes.Application.index())
          BadRequest(views.html.addCategory(errors, Category.all(), Application.username(request)))
        }, // BadRequest(views.html.bookmark.form(categoryErrors)),
        category => {
          Category.save(category)
          Redirect(routes.Categories.categories()).flashing("success" -> Messages("category.create.success").format(category.label))
        } // { Save; Ok(views.html.bookmark.summary(bookmark) }
      )
  }


  def delete(id: Int) = Action {
    implicit request => {
      Category.delete(id)
      Application.index(request)
    }
  }

  def edit(id: Int) = Action {
    implicit request =>
      Category.findById(id) match {
        case Some(category) => Ok(views.html.editCategory(id, Categories.categoryForm.fill(category), Application.username(request)))
        case None => BadRequest
      }

  }

  def save(id: Int) = Action {
    implicit request =>
      categoryForm.bindFromRequest.fold(
        errors => {
          Logger.error("Error Save " + errors)
          //Redirect(routes.Application.index())
          BadRequest(views.html.editCategory(id, errors, Application.username(request)))
        }, // BadRequest(views.html.bookmark.form(categoryErrors)),
        category => {
          Category.save(Category(anorm.Id(id), category.label))
          Redirect(routes.Categories.edit(id)).flashing("success" -> Messages("category.update.success").format(category.label))
        } // { Save; Ok(views.html.bookmark.summary(bookmark) }
      )

  }

  val categoryForm = Form[Category](
    mapping(
      "id" -> ignored(NotAssigned: Pk[Int]),
      "label" -> nonEmptyText(3, 50)
    )(Category.apply)(Category.unapply)
  )
}
