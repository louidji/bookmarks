package models

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 01/02/13
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
case class Bookmark(id: Option[Int], title: String, url: String, details: String, category: Option[Category])

  object Bookmark {

    def all(): List[Bookmark] = Nil

    def delete(id: Int) {}

    def save(bookmark: Bookmark) : Bookmark = {
      //TODO

      bookmark
    }




}
