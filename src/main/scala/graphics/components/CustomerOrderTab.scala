package graphics.components

import scalafx.scene.layout.BorderPane
import scalafx.scene.control.{Tab, ScrollPane}
import scalafx.geometry.Pos.Center
import scalafx.scene.control.TableView
import graphics.Person

class CustomerOrderTab(tableContent: TableView[Person]) extends Tab {
  text = "Customer Orders"
  content = new BorderPane {
    center_=(
      new ScrollPane {
        alignmentInParent_=(Center)
        minWidth = 400
        minHeight = 500
        maxHeight = 500
        //Here is where the table is built
        content = tableContent
      })
  }
  closable = false
}