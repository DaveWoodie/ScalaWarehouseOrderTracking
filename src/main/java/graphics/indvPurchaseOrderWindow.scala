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
import Entities.{ purchaseOrderLine, purchaseOrder }
import controllers.{ purchaseOrderLineController, purchaseOrderController }
import scalafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import javafx.event.ActionEvent
import scalafx.scene.control.TextField

class indvPurchaseOrderWindow(purchaseOrderID : String, statusOfPOID: String) extends JFXApp {

  def buildIndvPOStage(): PrimaryStage = {

    stage = new PrimaryStage {

      title = "Purchase Order ID : " + purchaseOrderID.toString()

      val poc: purchaseOrderController = new purchaseOrderController()
      val po: ObservableBuffer[purchaseOrder] = poc.getSinglePO(purchaseOrderID)
      val polength: Int = po.length
      
      println("Purchase Order componenent: " + po(0).purchaseID.value)

      width = 800
      height = 762
      resizable_=(false)

      scene = new Scene {

        root = new BorderPane {

          var t: TableView[purchaseOrderLine] = buildPurchaseOrderTable(purchaseOrderID)

          padding = Insets(20, 20, 20, 20)
          //Nothing more than a bit of alignment magic to make the panel look a little nicer
          top_=(new Label {
            text = " "
            //padding = Insets(60, 100, 0, 40)
            alignmentInParent_=(scalafx.geometry.Pos.Center)
          })
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
                  content = t
                })

                println("Line 66 poid: " + po(0).purchaseID.value)

                bottom_=(new VBox {

                  var damBox: TextField = new TextField {
                    promptText = "Number Damaged"
                    minWidth = 150

                    if (po(0).statusID.value.equals("1") || po(0).statusID.value.equals("3")) {
                      disable = true
                    }
                  }

                  padding = Insets(20)
                  spacing = 20

                  children = List(
                    new HBox {
                      alignmentInParent_=(javafx.geometry.Pos.CENTER)
                      spacing = 20
                      children = List(
                        new Button {
                          text = "Back to Purchase Orders"
                          minWidth = 150
                          onAction = handle(backToMain)
                        },

                        makeUpdateButton(po(0).statusID.value, po(0).purchaseID.value/*, t, polength*/))
                    },

                    new HBox {
                      spacing = 20
                      children = List(
                        new Label {
                          text = "Quantity damaged Items: "
                          minWidth = 150
                        },
                        damBox)
                    },
                    new HBox {
                      spacing = 20
                      children = List(
                        new Label {
                          text = "Update selected Row"
                          minWidth = 150
                        },
                        new Button {
                          text = "Update"
                          minWidth = 150
                          onAction = handle(updateDamagedStock(t, damBox))
                        })

                    })
                })
              })
          })
        }

      }

    }
    return stage
  }
  
  def updateDamagedStock (t: TableView[purchaseOrderLine], damBox: TextField): Unit = {
    
    t.getSelectionModel.selectedItemProperty.get.quantityDamg.value_=(damBox.text.value)
    val polc: purchaseOrderLineController = new purchaseOrderLineController
    polc.updateDamagedStock(t.getSelectionModel.selectedItemProperty.get.purchaseOrderID.value, t.getSelectionModel.selectedItemProperty.get.itemID.value, t.getSelectionModel.selectedItemProperty.get.quantityDamg.value)
    
  }

  //  def popupDamageBox(): String = {
  //
  //  }

  def makeUpdateButton(status: String, id: String/*, t: TableView[purchaseOrderLine], length: Int*/): Button = {
    var b: Button = new Button()

    if (status.equals("1")) {
      println("Line 94 id: " + id)
      b = placedStatusButton(status, id)
    } else if (status.equals("2")) {
      b = deliveredStatusButton(status, id/*, t, length*/)
    } else if (status.equals("3")) {
      b = completeStatusButton
    }
    return b
  }

  def placedStatusButton(status: String, poid: String): Button = {
    //println("Line 116 PLACED STATUS ID: " + poid)
    new Button {
      println("Made Placed Button")
      text = "Update Status"
      minWidth = 150
      //onAction = handle(updatePlacedStatus(id.toString(), status))
      onAction = {
        handle(combinedAction(status, poid))
        //handle (disable)
      }
    }
  }

  def combinedAction(status: String, id: String): Unit = {
    //println("Status: " + status + " ID: " + id)
    updatePlacedStatus(status, id)
  }

  def updatePlacedStatus(status: String, id: String): Unit = {
    val poc: purchaseOrderController = new purchaseOrderController()
    poc.updatePOStatus(status, id)
  }

  def deliveredStatusButton(status: String, ID: String/*, t: TableView[purchaseOrderLine], length: Int*/): Button = {
    new Button {
      println("Made Delivered Button")
      text = "Update Status"
      minWidth = 150
      //onAction = handle(printDamQuants(readDamagedQuantities(t, length)))
      onAction = handle(updatePlacedStatus(status, ID))
    }
  }
  
//  def readDamagedQuantities(T: TableView[purchaseOrderLine], length: Int): ObservableBuffer[String] = {
//    
//    var results: ObservableBuffer[String] = new ObservableBuffer[String]
//    println("Line 191 " + T.getSelectionModel.selectedItemProperty.get.quantityDamg.value)
//    while(T.quantityDamg.value != null) {
//      //println("Line 193")
//      results.+=(T.getSelectionModel.selectedItemProperty.get.quantityDamg.value)
//    }
//    return results
//  }
//  
//  def printDamQuants(ob: ObservableBuffer[String]): Unit = {
//    var k: Int = 0
//    println(ob(0))
//    while (ob(k) != null) {
//      println(ob(k))
//    }
//  }

  def completeStatusButton(): Button = {
    new Button {
      println("Made Complete Button")
      text = "Order Complete"
      minWidth = 150
      disable = true
    }
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

    val poc: purchaseOrderController = new purchaseOrderController
    val purchaseOrder: ObservableBuffer[purchaseOrder] = poc.getSinglePO(purchaseOrderID)

    if (purchaseOrder(0).statusID.value.equals("1")/* || purchaseOrder(0).statusID.value.equals("3")*/) {
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
    } else {
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
          },
          new TableColumn[purchaseOrderLine, String] {
            text = "Quantity of items damaged"
            cellValueFactory = { _.value.quantityDamg }
            prefWidth = 200
          })
      }
    }
  }

}