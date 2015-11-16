package com.qa.graphics.components

import com.qa.entities.customerOrder
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{TableView, TableColumn, TableCell}
import scalafx.scene.control.TableColumn._
import com.qa.logic.CaseSorter
import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._

/**
 * Class to make a table containing all the customer orders. 
 */
class AllCustomerOrderTable {
  
  /**
   * Method to build the customer order table.
   */
  def buildCOTable(customerOrders: ObservableBuffer[customerOrder]): TableView[customerOrder] = {

    new TableView[customerOrder](customerOrders) {
      minWidth = 752
      minHeight = 496
      alignmentInParent_=(scalafx.geometry.Pos.Center)

      columns ++= List(

        new TableColumn[customerOrder, String] {
          text = "Purchase Order ID"
          cellValueFactory = { _.value.customerID }
          prefWidth = 110
        },
        new TableColumn[customerOrder, String] {
          text = "Date Placed"
          cellValueFactory = { _.value.datePlaced }
          prefWidth = 210
        },
        new TableColumn[customerOrder, String] {
          text = "Date Shipped"
          cellValueFactory = { _.value.dateShipped }
          prefWidth = 210
        },

        new TableColumn[customerOrder, String] {
          text = "Status"
          cellValueFactory = { _.value.idCustomerOrderStatus }
          // Render the property value when it changes, 
          // including initial assignment
          cellFactory = { (col: TableColumn[customerOrder, String]) =>
            new TableCell[customerOrder, String] {
              item.onChange { (_, _, newColor) =>
                if (newColor != null) {
                  val sorter: CaseSorter = new CaseSorter
                  graphic = new Circle { fill = sorter.getCustomerColour(newColor); radius = 8 }
                } else {
                  graphic = new Circle { fill = Color.White; radius = 8 }
                }
              }
              alignmentInParent_=(scalafx.geometry.Pos.Center)
            }
          }
          prefWidth = 110
        }
        )
    }
  }
}