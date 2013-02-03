/**
 *
 */
package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import anorm.~
import play.api.Logger
import java.sql.SQLException

/**
 * @author louis
 *
 */
case class Category(id: Pk[Int] = NotAssigned, label: String)

object Category {

  /**
   * Parse a Category from a ResultSet
   */
  val simple = {
    get[Pk[Int]]("category.id") ~
      get[String]("category.label") map {
      case id ~ label => Category(id, label)
    }
  }


  def all(): List[Category] = DB.withConnection {
    implicit connection =>
      SQL("select * from category order by label").as(Category.simple *)
  }



  def save(category: Category): Category = {
    if (Logger.isDebugEnabled) Logger.debug("Sauvegarde de la catégorie : (" + category.id + ", " + category.label + ")")
    if (!category.id.isDefined) {
      insert(category)
    } else {
      update(category)
    }

    category
  }

  private def insert(category: Category): Category = {
    DB.withConnection {
      implicit connection => {
        SQL("insert into category(label) values ({label})").on(
          'label -> category.label
        ).executeInsert() match {
          case None => throw new SQLException("Erreur d'insertion")
          case Some(long) =>  Category(anorm.Id(long.asInstanceOf[Int]), category.label)
        }
      }
    }

  }


  private def update(category: Category): Category = {
    DB.withConnection {
      implicit connection => {
        SQL("update category set label = {label} where id = {id}").on('label -> category.label, 'id -> category.id).executeUpdate()
        category
      }
    }
  }


}