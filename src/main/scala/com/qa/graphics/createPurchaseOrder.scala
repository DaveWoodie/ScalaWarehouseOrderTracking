/**
 * @author dwoodward
 */

package com.qa.graphics

import scalafx.application.JFXApp

import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.geometry.{Pos, VPos}
import scalafx.scene.layout.{HBox, VBox, GridPane}
import scalafx.scene.control.{TabPane, Tab, CheckBox, ComboBox, TextField, PasswordField, Button, Accordion, Label, ScrollPane, TitledPane, TableCell, TableColumn, TableView}
import scalafx.event.{ActionEvent, EventHandler}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Circle
import scalafx.scene.text.{Font, Text}
import scalafx.Includes._
import scalafx.scene.layout.BorderPane
import javax.swing.text.Position
import com.sun.javafx.css.Stylesheet
import javax.swing.text.html.StyleSheet
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn._
import scalafx.scene.paint.Color
import javafx.scene.input.MouseEvent
import com.qa.controllers.{purchaseOrderController, purchaseOrderLineController, NewPurchaseOrderController}
import com.qa.entities.{purchaseOrder, purchaseOrderLine}
import java.util.Calendar
import com.qa.graphics.components.{NewPurchaseOrderTable, TitleText, tableScrollPane, ItemIDComboBox}

/**
 * Class to populate and then generate a new purchase order in the GUI
 */

class createPurchaseOrder extends JFXApp {

  /**
   * Method to be called to overwrite the stage of whichever other GUI calls it.
   */
  def buildCPOStage(poid: String): PrimaryStage = {

    stage = new PrimaryStage {
      title = "New Purchase Order " + poid
      width = 800
      height = 782
      resizable_=(false)

      val m: mainWindow = new mainWindow()

      scene = new Scene {

        root = new BorderPane {

          val supplierComboBox = new ComboBox[String] {
            //here are the options for the combo Box 
            val testStrings = ObservableBuffer[String]("1", "2", "3", "4")

            promptText = "Choose a supplier"
            minWidth = 150
            items = testStrings
          }
          val custo: ObservableBuffer[purchaseOrderLine] = new ObservableBuffer[purchaseOrderLine]
          val newpolt: NewPurchaseOrderTable = new NewPurchaseOrderTable
          val t: TableView[purchaseOrderLine] = newpolt.makeNPOLT(t, custo)

          padding = Insets(20, 20, 20, 20)
          top_=(new Label {
            text = " "
            alignmentInParent_=(scalafx.geometry.Pos.Center)
          })
          center_=(new VBox {

            spacing = 20
            children = List(

              new HBox {

                spacing = 20
                children = List(
                  new TitleText("Warehouse Order Tracking Application"),
                  supplierComboBox,
                  new Button {
                    text = "Cancel"
                    minWidth = 150
                    onAction = handle(stage = m.buildMainStage())
                  })
              },
              //Still in the VBox of the Pane
              new tableScrollPane(t),
              new HBox {
                spacing = 10
                val quan: TextField = new TextField {
                  promptText = "Enter an Item Quantity"
                  minWidth = 150
                }
                val ID = new ItemIDComboBox
                children = List(
                  ID,
                  quan,

                  new Button {
                    text = "Add to Order"
                    minWidth = 150
                    onAction = handle({
                      //Another Higher order function in my handling.
                      addLineToTable(custo, makePOLineFromInput, ID.value.value.toString(), poid, quan.text.value)
                    })
                  })
              })
          })

          bottom_=(new VBox {
            spacing = 20
            children = List(
              new Button {
                text = "Remove Selected Row"
                //println(poid)
                minWidth = 150
                onAction = handle({
                  custo.remove(t.selectionModel.value.getFocusedIndex)
                })
              },

              new Button {
                text = "Add to Purchase Orders"
                //println(poid)
                minWidth = 150
                onAction = handle({
                  val npoc = new NewPurchaseOrderController
                  npoc.pushPOToDB(custo, poid.toString(), supplierComboBox.value.value.toString())
                  stage = m.buildMainStage()
                })
              })

          })
        }
      }
    }
    return stage
  }
  /**
   * Method to make the purchase order line from the input fields of the GUI.
   */
  def makePOLineFromInput(itemID: String, poid: String, quantity: String): purchaseOrderLine = {
    new purchaseOrderLine(itemID, poid, quantity)
  }

  /**
   * Method to add a purchase order line to a new purchase order table. HIGHER ORDER!
   */
  def addLineToTable(data: ObservableBuffer[purchaseOrderLine], f: (String, String, String) => purchaseOrderLine, itemID: String, poID: String, quantity: String): Unit = {
    val pol = f(itemID, poID, quantity)
    data.+=(pol)
  }
}