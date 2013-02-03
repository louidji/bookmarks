/**
 *
 */
package models

/**
 * @author louis
 *
 */
case class Category(id: Long, label: String)

object Category {

  def all(): List[Category] = Nil

  def create(label: String) {}

  def delete(id: Long) {}

  def find(id: Long): Category = {
    Category(id, "TODO")
  }

}