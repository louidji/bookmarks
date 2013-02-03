package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
object Bookmarks extends Controller {
  def add = Action { implicit request =>
    bookmarkForm.bindFromRequest.fold(
      errors => TODO, // BadRequest(views.html.bookmark.form(errors)),
      bookmark => TODO // { Save; Ok(views.html.bookmark.summary(bookmark) }
    )

    Redirect(routes.Application.index())
  }

  def bookmarks = TODO

  def delete(id: Long) = TODO

  val bookmarkForm = Form(
    mapping(
      "id" -> longNumber,
      "title" -> nonEmptyText,
      "url" -> nonEmptyText,
      "details" -> text(minLength = 6),
      "category" -> optional[Category](Categories.categoryForm.mapping)
    )(Bookmark.apply)(Bookmark.unapply)
  )
}
