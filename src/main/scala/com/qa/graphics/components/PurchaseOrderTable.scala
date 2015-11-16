package com.qa.graphics.components

import com.qa.logic.CaseSorter
import com.qa.entities.purchaseOrder
import scalafx.scene.control.{ TableCell, TableColumn, TableView }
import scalafx.scene.control.TableColumn._
import scalafx.collections.ObservableBuffer
import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import com.qa.controllers.purchaseOrderController
import javafx.event.EventHandler
import scalafx.scene.input.MouseEvent

/**
 * Class to instanciate the purchase order table and populate it given the ObsevableBuffer of purchase orders.
 */
class PurchaseOrderTable() extends TableView[purchaseOrder] {

  /**
   * Method to build the Purchase Order Table right at the start.
   */
  def makePO(custo: ObservableBuffer[purchaseOrder]): TableView[purchaseOrder] = {
    val t: TableView[purchaseOrder] = buildPOTable(custo)
    return t
  }

  /**
   * Method to build the purchase order table from the custo object being passed in.
   */
  def buildPOTable(purchaseOrders: ObservableBuffer[purchaseOrder]): TableView[purchaseOrder] = {

    new TableView[purchaseOrder](purchaseOrders) {
      minWidth = 752
      minHeight = 496
      alignmentInParent_=(scalafx.geometry.Pos.Center)

      columns ++= List(

        new TableColumn[purchaseOrder, String] {
          text = "Purchase Order ID"
          cellValueFactory = { _.value.purchaseID }
          prefWidth = 110
        },
        new TableColumn[purchaseOrder, String] {
          text = "Date Placed"
          cellValueFactory = { _.value.datePlaced }
          prefWidth = 210
        },
        new TableColumn[purchaseOrder, String] {
          text = "Date Recieved"
          cellValueFactory = { _.value.dateRecieved }
          prefWidth = 210
        },

        new TableColumn[purchaseOrder, String] {
          text = "Status"
          cellValueFactory = { _.value.statusID }
          // Render the property value when it changes, 
          // including initial assignment
          cellFactory = { (col: TableColumn[purchaseOrder, String]) =>
            new TableCell[purchaseOrder, String] {
              item.onChange { (_, _, newColor) =>
                if (newColor != null) {
                  val sorter: CaseSorter = new CaseSorter
                  graphic = new Circle { fill = sorter.getColour(newColor); radius = 8 }
                } else {
                  graphic = new Circle { fill = Color.White; radius = 8 }
                }
              }
              alignmentInParent_=(scalafx.geometry.Pos.Center)
            }
          }
          prefWidth = 110
        },
        new TableColumn[purchaseOrder, String] {
          text = "Supplier ID"
          cellValueFactory = { _.value.supplierID }
          prefWidth = 110
        })
    }
  }
}