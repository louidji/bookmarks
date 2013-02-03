package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import anorm.{Pk, NotAssigned}

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
object Bookmarks extends Controller {
  def add = Action {
    implicit request =>
      bookmarkForm.bindFromRequest.fold(
        errors => TODO, // BadRequest(views.html.bookmark.form(categoryErrors)),
        bookmark => TODO // { Save; Ok(views.html.bookmark.summary(bookmark) }
      )

      Redirect(routes.Application.index())
  }

  def bookmarks = TODO

  def delete(id: Int) = TODO

  val bookmarkForm = Form(
    mapping(
      "id" -> ignored(NotAssigned: Pk[Int]),
      "title" -> nonEmptyText(4, 50),
      "url" -> nonEmptyText(4, 100),
      "details" -> optional(text(maxLength=255)),
      "bookmark" -> optional(number)
    )(Bookmark.apply)(Bookmark.unapply)
  )
}
