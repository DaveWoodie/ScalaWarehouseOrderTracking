package graphics

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.scene.paint.Color

class Person(firstName_ : String, lastName_ : String, phone_ : String, favoriteColor_ : Color = Color.Blue) {

  val firstName = new StringProperty(this, "firstName", firstName_)
  val lastName = new StringProperty(this, "lastName", lastName_)
  val phone = new StringProperty(this, "phone", phone_)
  val favoriteColor = new ObjectProperty(this, "favoriteColor", favoriteColor_)
}