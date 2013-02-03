package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 03/02/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
object Bookmarks extends Controller {
       def add = Action {

         Redirect(routes.Application.categories())
       }
}
