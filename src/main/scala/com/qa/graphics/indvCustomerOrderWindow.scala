package com.qa.graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, VBox, HBox}
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.text.{ Font, Text }
import scalafx.scene.control.{Label, ScrollPane, TableCell, TableColumn, TableView}
import scalafx.scene.control.TableColumn._
import scalafx.collections.ObservableBuffer
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import com.qa.entities.{purchaseOrderLine, purchaseOrder, customerOrder, customerOrderLine}
import com.qa.controllers.{purchaseOrderLineController, purchaseOrderController}
import scalafx.scene.control.{Button, TextField}
//Had to use the javafx event handlers...
import javafx.scene.input.MouseEvent
import javafx.event.{EventHandler, ActionEvent}
import com.qa.logic.CaseSorter
import com.qa.controllers.CustomerOrderController
import com.qa.graphics.components.CustomerOrderLineTable

/**
 * Class to create and view and individual customer orders, order lines and status.
 */
class indvCustomerOrderWindow(customerOrderID: String, statusOfCO: String) extends JFXApp {
  
  /**
   * Method to use to overwrite the currently used stage of the graphics. 
   */
  def buildIndvCOStage(): PrimaryStage = {

    stage = new PrimaryStage {

      //to String is still here for testing as sometimes I was passing the StringProperty not the String itself.
      title = "Customer Order ID : " + customerOrderID.toString()

      //This is the logic for pulling a single purchase order.

      val coc: CustomerOrderController = new CustomerOrderController
      val co: ObservableBuffer[customerOrder] = coc.getSingleCO(customerOrderID)
      val colength: Int = co.length
      width = 800
      height = 762
      resizable_=(false)

      scene = new Scene {

        root = new BorderPane {

          val c: CaseSorter = new CaseSorter
          //Table view defined outside so I can change the ObservableBuffer that is used to populate it. Vars.... Eugh
          val colt: CustomerOrderLineTable = new CustomerOrderLineTable
          val t: TableView[customerOrderLine] = colt.makeCOLT(co(0).customerID.value)

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
                text = "Customer Order Contents"
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
                bottom_=(new VBox {
                  padding = Insets(20)
                  spacing = 20

                  children = List(
                    new HBox {
                      alignmentInParent_=(javafx.geometry.Pos.CENTER)
                      spacing = 20
                      children = List(
                        new Button {
                          text = "Back to Main Screen Orders"
                          minWidth = 150

                          if (co(0).idCustomerOrderStatus.value.equals("2") || co(0).idCustomerOrderStatus.value.equals("3")) {
                            disable = true
                          }
                          onAction = handle(backToMain)
                        })
                    },

                    new HBox {
                      spacing = 20
                      children = List(
                        new Label {
                          text = "Quantity damaged Items: "
                          minWidth = 150
                        })
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

                          disable = true
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
   * Method to return to the main window.
   */
  def backToMain(): Unit = {
    val m: mainWindow = new mainWindow()
    stage = m.buildMainStage()
  }
}
