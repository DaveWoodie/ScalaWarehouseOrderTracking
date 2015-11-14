package graphics.components

import scalafx.scene.control.TableView
import graphics.Person
import scalafx.collections.ObservableBuffer
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableCell
import scalafx.scene.control.TableColumn._
import scalafx.scene.shape.Circle

class CustomerOrderTable extends TableView[Person] {
  
  def buildCOTable(): TableView[Person] = {

    val characters = ObservableBuffer[Person](
      new Person("1", "Filler", "555-6798", Color.Black),
      new Person("2", "The Bear", "555-6798", Color.GreenYellow),
      new Person("3", "Gottcha-Back", "555-9275", Color.Aquamarine))

    new TableView[Person](characters) {
      minWidth = 752
      minHeight = 496
      //      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(scalafx.geometry.Pos.Center)
      columns ++= List(

        new TableColumn[Person, String] {
          text = "Customer Order ID"
          cellValueFactory = { _.value.firstName }
          prefWidth = 300
        },
        new TableColumn[Person, String]() {
          text = "Last Name"
          cellValueFactory = { _.value.lastName }
          prefWidth = 100
        },

        new TableColumn[Person, Color] {
          text = "Favorite Color"
          cellValueFactory = { _.value.favoriteColor }
          // Render the property value when it changes, 
          // including initial assignment
          cellFactory = { (col: TableColumn[Person, Color]) =>
            new TableCell[Person, Color] {
              item.onChange { (_, _, newColor) =>
                graphic = new Circle { fill = newColor; radius = 8; alignmentInParent_=(scalafx.geometry.Pos.Center) }
              }
              alignmentInParent_=(scalafx.geometry.Pos.Center)
            }
          }
          prefWidth = 200
        })
    }
  }
}