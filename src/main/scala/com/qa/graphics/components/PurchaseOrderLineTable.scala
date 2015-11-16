package com.qa.graphics.components

import scalafx.scene.control.TableView
import com.qa.entities.{purchaseOrderLine, purchaseOrder}
import scalafx.collections.ObservableBuffer
import com.qa.controllers.purchaseOrderController
import com.qa.controllers.purchaseOrderLineController
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn._

class PurchaseOrderLineTable extends TableView[purchaseOrderLine] {

  def makePOLT(purchaseOrderID: String): TableView[purchaseOrderLine] = {
    val t: TableView[purchaseOrderLine] = buildPOLTable(purchaseOrderID)
    return t
  }

  def buildPOLTable(purchaseOrderID: String): TableView[purchaseOrderLine] = {

    //Reason for taking in the purchase order ID here is so that when the method is called
    //The individual purchase order ID that is passed can be pulled from the database
    val p: purchaseOrderLineController = new purchaseOrderLineController()
    val purchaseOrderLines: ObservableBuffer[purchaseOrderLine] = p.getPurchaseOrderLines(purchaseOrderID)

    val poc: purchaseOrderController = new purchaseOrderController
    val purchaseOrder: ObservableBuffer[purchaseOrder] = poc.getSinglePO(purchaseOrderID)

    if (purchaseOrder(0).statusID.value.equals("1") /* || purchaseOrder(0).statusID.value.equals("3")*/ ) {
      unDamTable(purchaseOrderLines)
    } else {
      damTable(purchaseOrderLines)
    }
  }
  def unDamTable(purchaseOrderLines: ObservableBuffer[purchaseOrderLine]): TableView[purchaseOrderLine] = {
    new TableView[purchaseOrderLine](purchaseOrderLines) {
      minWidth = 752
      minHeight = 496

      //      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(scalafx.geometry.Pos.Center)
      columns ++= List(

        new TableColumn[purchaseOrderLine, String] {
          text = "Item ID Number"
          cellValueFactory = { _.value.itemID }
          prefWidth = 200
        },
        new TableColumn[purchaseOrderLine, String] {
          text = "Quantity of items"
          cellValueFactory = { _.value.quantity }
          prefWidth = 200
        })
    }
  }

  def damTable(purchaseOrderLines: ObservableBuffer[purchaseOrderLine]): TableView[purchaseOrderLine] = {
    new TableView[purchaseOrderLine](purchaseOrderLines) {
      minWidth = 752
      minHeight = 496

      //      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(scalafx.geometry.Pos.Center)
      columns ++= List(

        new TableColumn[purchaseOrderLine, String] {
          text = "Item ID Number"
          cellValueFactory = { _.value.itemID }
          prefWidth = 200
        },
        new TableColumn[purchaseOrderLine, String] {
          text = "Quantity of items"
          cellValueFactory = { _.value.quantity }
          prefWidth = 200
        },
        new TableColumn[purchaseOrderLine, String] {
          text = "Quantity of items damaged"
          cellValueFactory = { _.value.quantityDamg }
          prefWidth = 200
        })
    }
  }
}