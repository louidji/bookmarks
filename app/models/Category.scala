/**
 *
 */
package models

/**
 * @author louis
 *
 */
case class Category(id: Option[Long], label: String)

object Category {

  def all(): List[Category] = Nil

  def delete(id: Long) {}

  def find(id: Long): Category = {
    Category(Some(id), "TODO")
  }

  def save(category: Category) : Category = {
    // TODO

    category
  }

}