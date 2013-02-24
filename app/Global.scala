/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */

import controllers.routes
import play.api._
import play.api.mvc._

object Global extends WithFilters(AuthorizedFilter("private", "categories", "bookmarks", "delete", "edit", "save")) with GlobalSettings {
  override def onStart(app: Application) {
    Logger.info("Application has started")
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }


}

object AccessLog extends Filter {
  override def apply(next: RequestHeader => Result)(request: RequestHeader): Result = {
    val result = next(request)
    play.Logger.info(request + "\n\t => " + result)
    result
  }
}

object AuthorizedFilter {
  def apply(actionNames: String*) = new AuthorizedFilter(actionNames)
}

class AuthorizedFilter(actionNames: Seq[String]) extends Filter {

  /**
   * Retrieve the connected user email.
   */
  private def username(request: RequestHeader) = request.session.get(Security.username)

  override def apply(next: RequestHeader => Result)(request: RequestHeader): Result = {
    if (authorizationRequired(request)) {
      /* do the auth stuff here */
      val user = username(request)
      user match {
        case Some(_) => next(request)
        case None => play.api.mvc.Results.Redirect(routes.Application.login)

      }


    }
    else next(request)
  }

  private def authorizationRequired(request: RequestHeader) = {
    val actionInvoked: String = request.tags.getOrElse(play.api.Routes.ROUTE_ACTION_METHOD, "")
    //val actionInvoked: String = request.tags.getOrElse(play.api.Routes.ROUTE_PATTERN, "")
    actionNames.contains(actionInvoked)



  }
}
