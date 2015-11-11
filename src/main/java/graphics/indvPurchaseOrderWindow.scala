package graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{ BorderPane, VBox, HBox }
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.text.{ Font, Text }
import scalafx.scene.control.{ Label, ScrollPane }
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{ TableCell, TableColumn, TableView }
import scalafx.collections.ObservableBuffer
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import controllers.purchaseOrderLineController
import Entities.{purchaseOrderLine, purchaseOrder}
import controllers.{purchaseOrderLineController, purchaseOrderController}
import scalafx.scene.control.Button

class indvPurchaseOrderWindow(purchaseOrderID_ : String) extends JFXApp {

  def buildIndvPOStage(): PrimaryStage = {

    stage = new PrimaryStage {

      title = "Purchase Order ID : " + purchaseOrderID_.toString()
      
      val poc: purchaseOrderController = new purchaseOrderController()
      val po: ObservableBuffer[purchaseOrder] = poc.getSinglePO(purchaseOrderID_)
      println("Purchase Order componenent: " + po(0).purchaseID.value)
      
      width = 800
      height = 762
      resizable_=(false)

      scene = new Scene {

        root = new BorderPane {
          padding = Insets(20, 20, 20, 20)
          center_=(new VBox {

            children = List(
              new Text {
                text = "Purchase Order Contents"
                //                  padding = Insets(0, 10, 10, 10)
                font = new Font("Verdana", 20)
              },
              new BorderPane {
                center_=(new ScrollPane {
                  alignmentInParent_=(javafx.geometry.Pos.CENTER)
                  minWidth = 400
                  minHeight = 500
                  maxHeight = 500
                  //Here is where the table is built
                  content = buildPurchaseOrderTable(purchaseOrderID_)
                })
                bottom_=(new HBox {
                  alignmentInParent_=(javafx.geometry.Pos.CENTER)
                  spacing = 20
                  children = List(
                    new Button {
                      text = "Back to Purchase Orders"
                      minWidth = 150
                      onAction = handle(backToMain)
                    },
                    new Button {
                      text = "Update Order Status"
                      minWidth = 150

                      onAction = handle(println("TO DO"))

                    })
                })
              })
          })
        }

      }

    }
    return stage
  }

  def backToMain(): Unit = {
    val m: mainWindow = new mainWindow()
    stage = m.buildMainStage()
  }

  def buildPurchaseOrderTable(purchaseOrderID: String): TableView[purchaseOrderLine] = {

    //Reason for taking in the purchase order ID here is so that when the method is called
    //The individual purchase order ID that is passed can be pulled from the database
    val p: purchaseOrderLineController = new purchaseOrderLineController()
    val purchaseOrderLines: ObservableBuffer[purchaseOrderLine] = p.getPurchaseOrderLines(purchaseOrderID)

    new TableView[purchaseOrderLine](purchaseOrderLines) {
      minWidth = 752
      minHeight = 496

      //      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(javafx.geometry.Pos.CENTER)
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

}