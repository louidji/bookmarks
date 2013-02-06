package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import anorm.{Pk, NotAssigned}
import play.api.Logger

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
object Bookmarks extends Controller {

  def bookmarks = Action {
    implicit request =>
    Ok(views.html.bookmark(Bookmarks.bookmarkForm, Bookmark.all(), Category.all()))
  }
  def create = Action {
    implicit request =>
      bookmarkForm.bindFromRequest.fold(
        errors => {
          Logger.error("Error Save " + errors)
          //Redirect(routes.Application.index())
          BadRequest(views.html.bookmark(errors, Bookmark.all(), Category.all()))
        }, // BadRequest(views.html.bookmark.form(categoryErrors)),
        bookmark => {
          Logger.info("TODO Save " + bookmark)
          Redirect(routes.Bookmarks.bookmarks()).flashing("success" -> "Title %s has been created".format(bookmark.title))
        } // { Save; Ok(views.html.bookmark.summary(bookmark) }
      )
  }

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
