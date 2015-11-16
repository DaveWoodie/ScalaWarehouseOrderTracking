/**
 * @author dwoodward
 */

package com.qa.graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, VBox, HBox}
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.text.{Font, Text}
import scalafx.scene.control.{Label, ScrollPane, TableCell, TableColumn, TableView}
import scalafx.scene.control.TableColumn._
import scalafx.collections.ObservableBuffer
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import com.qa.entities.{ purchaseOrderLine, purchaseOrder }
import com.qa.controllers.{ purchaseOrderLineController, purchaseOrderController, DamagedStockController }
import scalafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import javafx.event.ActionEvent
import scalafx.scene.control.TextField
import com.qa.graphics.components.PurchaseOrderLineTable
import com.qa.logic.CaseSorter
import com.qa.graphics.components.tableScrollPane

/**
 * Class to view an individual purchase orders order lines and update it's status. 
 */

class indvPurchaseOrderWindow(purchaseOrderID: String, statusOfPOID: String) extends JFXApp {

  /**
   * Method to build a new stage to overwrite any stage that calls it.
   */
  def buildIndvPOStage(): PrimaryStage = {

    stage = new PrimaryStage {

      //to String is still here for testing as sometimes I was passing the StringProperty not the String itself.
      title = "Purchase Order ID : " + purchaseOrderID.toString()

      //This is the logic for pulling a single purchase order.
      
      val poc: purchaseOrderController = new purchaseOrderController()
      val po: ObservableBuffer[purchaseOrder] = poc.getSinglePO(purchaseOrderID)
      val polength: Int = po.length
      width = 800
      height = 762
      resizable_=(false)

      scene = new Scene {

        root = new BorderPane {

          val c: CaseSorter = new CaseSorter
          //Table view defined outside so I can change the ObservableBuffer that is used to populate it. Vars.... Eugh
          val polt: PurchaseOrderLineTable = new PurchaseOrderLineTable
          val t: TableView[purchaseOrderLine] = polt.makePOLT(po(0).purchaseID.value)

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
                center_=(new tableScrollPane(t)) 
                bottom_=(new VBox {
                  
                  val damBox: TextField = new TextField {
                    promptText = "Number Damaged"
                    minWidth = 150
                    //Locks the button out if the purchase order is of the wrong type.
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
                        
                        c.makeUpdateButton(po(0).statusID.value, po(0).purchaseID.value))
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
                          if (po(0).statusID.value.equals("1") || po(0).statusID.value.equals("3")) {
                            disable = true
                          }
                          val d = new DamagedStockController
                          onAction = handle(d.updateDamagedStock(t, damBox))
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

  /**
   * Method to return to the main page
   */
  def backToMain(): Unit = {
    val m: mainWindow = new mainWindow()
    stage = m.buildMainStage()
  }
}