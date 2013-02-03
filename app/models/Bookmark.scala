package models

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 01/02/13
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
case class Bookmark (id: Long, title: String, url: String, details: String, category: Category) {

  object Bookmark {

    def all(): List[Bookmark] = Nil

    def create(label: String) {}

    def delete(id: Long) {}


  }
}
