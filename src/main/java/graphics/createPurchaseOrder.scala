/**
 * @author dwoodward
 */

package graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.geometry.{ Pos, VPos }
import scalafx.scene.Scene
import scalafx.scene.control.{ Accordion, Label, ScrollPane, TitledPane }
import scalafx.scene.layout.{ HBox, VBox }
import scalafx.scene.layout.GridPane
import scalafx.scene.control.{ TabPane, Tab, CheckBox, ComboBox }
import scalafx.scene.control.TextField
import scalafx.scene.control.PasswordField
import scalafx.scene.control.Button
import scalafx.geometry.Pos
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Circle
import scalafx.scene.text.{ Font, Text }
import scalafx.Includes._
import scalafx.scene.layout.BorderPane
import javax.swing.text.Position
import com.sun.javafx.css.Stylesheet
import javax.swing.text.html.StyleSheet
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{ TableCell, TableColumn, TableView }
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.control.Tab
//java....
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import javafx.event.ActionEvent
//Classes 
import controllers.purchaseOrderController
import controllers.purchaseOrderLineController
import Entities.purchaseOrder
import Entities.purchaseOrderLine
import java.util.Calendar

class createPurchaseOrder extends JFXApp {

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
          var custo: ObservableBuffer[purchaseOrderLine] = new ObservableBuffer[purchaseOrderLine]
          var t: TableView[purchaseOrderLine] = makeTableViewFromBuffer(t, custo)

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
                  new Text {
                    text = "Warehouse Order Tracking Application"
                    //                  padding = Insets(0, 10, 10, 10)
                    font = new Font("Verdana", 20)
                  },

                  supplierComboBox,

                  new Button {
                    text = "Cancel"
                    minWidth = 150

                    onAction = handle(stage = m.buildMainStage())
                  })
              },
              //Still in the VBox of the Pane
              new ScrollPane {
                alignmentInParent_=(javafx.geometry.Pos.CENTER)
                minWidth = 400
                minHeight = 500
                maxHeight = 500
                content = t
              },

              new HBox {

                spacing = 10

                val quan: TextField = new TextField {
                  promptText = "Enter an Item Quantity"
                  minWidth = 150
                }

                val ID: ComboBox[String] = new ComboBox[String] {
                  //here are the options for the combo Box 
                  val testStrings = ObservableBuffer[String]("1", "2", "3", "4", "5", "6", "7")

                  promptText = "Choose an Item ID"
                  minWidth = 150
                  items = testStrings
                }

                children = List(
                  ID,
                  quan,

                  new Button {
                    text = "Add to Order"
                    minWidth = 150
                    onAction = handle({
                      addLineToTable(custo, makePOLineFromInput(ID.value.value.toString(), poid, quan.text.value))
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
                  pushPOToDB(custo, poid.toString(), supplierComboBox.value.value.toString(), stage)
                  stage = m.buildMainStage()
                })
              })

          })
        }
      }
    }
    return stage
  }

  def pushPOToDB(data: ObservableBuffer[purchaseOrderLine], poid: String, supplierID: String, stage: PrimaryStage): Unit = {

    pushPO(poid, supplierID)
    pushPOLines(data, supplierID)
  }
  def pushPOLines(data: ObservableBuffer[purchaseOrderLine], supplierID: String): Unit = {
    val polc: purchaseOrderLineController = new purchaseOrderLineController
    var k: Int = 0
    for (i <- data) {
      polc.addNewLine(i, supplierID)
    }
  }

  def pushPO(poid: String, supplierID: String): Unit = {
    val poc: purchaseOrderController = new purchaseOrderController

    //println("Line 172 POID: " + poid + " SUPPLIER ID: " + supplierID)

    poc.addPO(makePO(poid.toString(), supplierID.toString()))
  }

  def makePO(poid: String, supplierID: String): purchaseOrder = {
    val currentDate: String = Calendar.getInstance().getTime().toString()
    new purchaseOrder(poid, currentDate, "TBC", "1", "1", supplierID)
  }

  def makePOLineFromInput(itemID: String, poid: String, quantity: String): purchaseOrderLine = {
    new purchaseOrderLine(itemID, poid, quantity)
  }

  def addLineToTable(data: ObservableBuffer[purchaseOrderLine], pol: purchaseOrderLine): Unit = {
    data.+=(pol)
  }

  def makeTableViewFromBuffer(t: TableView[purchaseOrderLine], data: ObservableBuffer[purchaseOrderLine]): TableView[purchaseOrderLine] = {
    new TableView[purchaseOrderLine](data) {
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