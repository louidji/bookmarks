package models

import anorm._
import anorm.SqlParser._
import anorm.~
import play.api.db.DB
import play.api.Play.current
import play.api.Logger
import java.sql.SQLException

/**
 * Created with IntelliJ IDEA.
 * User: tournayret
 * Date: 01/02/13
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
case class Bookmark(id: Pk[Int] = NotAssigned, title: String, url: String, details: Option[String], categoryId: Int)

case class FullBookmark(id: Pk[Int] = NotAssigned, title: String, url: String, categoryId: Int, categoryName: String)


object Bookmark {

  /**
   * Parse a Bookmark from a ResultSet
   */
  val simple = {
    get[Pk[Int]]("bookmark.id") ~
      get[String]("bookmark.title") ~
      get[String]("bookmark.url") ~
      get[Option[String]]("bookmark.details") ~
      get[Int]("bookmark.categoryId") map {
      case id ~ title ~ url ~ details ~ categoryId => Bookmark(id, title, url, details, categoryId)
    }
  }

  /**
   * Parse a Bookmark from a ResultSet
   */
  val full = {
    get[Pk[Int]]("bookmark.id") ~
      get[String]("bookmark.title") ~
      get[String]("bookmark.url") ~
      get[Int]("bookmark.categoryId") ~
      get[String]("category.label") map {
      case id ~ title ~ url ~ categoryId ~ label => FullBookmark(id, title, url, categoryId, label)
    }
  }

  def all(): List[Bookmark] = DB.withConnection { implicit connection =>
    SQL("select * from bookmark order by title").as(Bookmark.simple *)
  }

  def allFull(): List[FullBookmark] = DB.withConnection { implicit connection =>
    SQL("select bookmark.*, category.label from bookmark, category where bookmark.categoryId = category.id order by category.label, bookmark.title").as(Bookmark.full *)
  }

  def findById(id: Int): Option[Bookmark]  = DB.withConnection {
    implicit connection =>
      SQL("select * from bookmark where id = {id}").on('id -> id).as(Bookmark.simple.singleOpt)
  }

  def delete(id: Int) {
    DB.withConnection {
      implicit connection => {
        SQL("delete from bookmark where id = {id}").on('id -> id).executeUpdate()
      }
    }
  }

  def save(bookmark: Bookmark): Bookmark = {
    if (Logger.isDebugEnabled) Logger.debug("Sauvegarde du bookmark : (" + bookmark.id + ", " + bookmark.title + ")")
    if (!bookmark.id.isDefined) {
      insert(bookmark)
    } else {
      update(bookmark)
    }

    bookmark
  }

  private def insert(bookmark: Bookmark): Bookmark = {
    DB.withConnection {
      implicit connection => {
        // TODO finir le code d'insertion
        SQL("insert into bookmark(title, url, details, categoryId) values ({title}, {url}, {details}, {categoryId})").on(
          'title -> bookmark.title, 'url -> bookmark.url, 'details -> bookmark.details, 'categoryId -> bookmark.categoryId
        ).executeInsert() match {
          case None => throw new SQLException("Erreur d'insertion")
          case Some(long) =>  Bookmark(anorm.Id(long.asInstanceOf[Int]), bookmark.title, bookmark.url, bookmark.details, bookmark.categoryId)
        }
      }
    }

  }


  private def update(bookmark: Bookmark): Bookmark = {
    DB.withConnection {
      implicit connection => {
        SQL("update bookmark set title = {title}, url = {url}, details = {details}, categoryId={categoryId} where id = {id}").on('title -> bookmark.title, 'url -> bookmark.url, 'details -> bookmark.details, 'categoryId -> bookmark.categoryId, 'id -> bookmark.id).executeUpdate()
        bookmark
      }
    }
  }


}
