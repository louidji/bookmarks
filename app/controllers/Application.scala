package controllers

import play.api._
import models._

//import i18n.Messages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller  {

  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => check(email, password)
    })
  )

  def index = Action {
    Ok(views.html.index(Bookmark.allFull()))
  }



  def check(username: String, password: String) = {
    (username == "admin@cgi.com" && password == "1234")
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      user => {
        Redirect(routes.Application.index).withSession(Security.username -> user._1)
      }
    )
  }

  def logout = Action {
    Redirect(routes.Application.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }
}