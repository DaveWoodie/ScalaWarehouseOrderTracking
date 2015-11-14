/**
 * @author dwoodward
 */
package graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.geometry.{ Pos, VPos, Insets }
import scalafx.scene.layout.{ HBox, VBox, GridPane, BorderPane }
import scalafx.scene.control.{ TabPane, Tab, CheckBox, ComboBox, TextField, PasswordField, Button, Accordion, Label, ScrollPane, TitledPane, TableCell, TableColumn, TableView }
import scalafx.event.{ ActionEvent }
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Circle
import scalafx.scene.text.{ Font, Text }
import scalafx.Includes._
import javax.swing.text.Position
import com.sun.javafx.css.Stylesheet
import javax.swing.text.html.StyleSheet
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn._
import scalafx.scene.paint.Color
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import controllers.{ purchaseOrderController, purchaseOrderLineController }
import entities.{ purchaseOrder, purchaseOrderLine }
import java.util.Calendar
import graphics.components.{CustomerOrderTab, spacingLabel, TitleText, tableScrollPane, StatusComboBox, IDTextField, PurchaseOrderTable, CustomerOrderTable}
import logic.CaseSorter


class mainWindow extends JFXApp {

  /**
   * Main method to build the panels for holding items.
   */
  def buildMainStage(): PrimaryStage = {

    stage = new PrimaryStage {
      title = "Welcome to the Warehouse Order Tracking System"
      width = 800
      height = 782
      resizable_=(false)

      scene = new Scene {

        //Root of the scene
        root = new BorderPane {
          //*******************************************************************************************
          //NEED TO BUILD THE TABLE OUT HERE OR I CAN NEVER CHANGE IT TO FILTER
          val custo: ObservableBuffer[purchaseOrder] = getPurchaseOrders(0, false)
          val temp: PurchaseOrderTable = new PurchaseOrderTable
          val t: TableView[purchaseOrder] = temp.makePO(custo)

          val custtemp: CustomerOrderTable = new CustomerOrderTable
          val c: TableView[Person] = custtemp.buildCOTable()
          //Would like to make this more modular but not managed to find a way to pass in,
          //the method and tie it to the mouse highlight.
          t.onMouseClicked = new EventHandler[MouseEvent] {
            override def handle(event: MouseEvent) {
              if (event.getClickCount == 2 && (t.selectionModel.value.getFocusedIndex + 1).toString() != "0") {
                event.consume
                indvPO(t.getSelectionModel.selectedItemProperty.get.purchaseID.value, t.getSelectionModel.selectedItemProperty.get.statusID.value)
              }
            }
          }

          //*******************************************************************************************
          padding = Insets(20, 20, 20, 20)
          top_=(new spacingLabel)
          //A lot of the V and HBoxes inside objects are just to make the layout look pretty. Not essential.
          center_=(new VBox {
            children = List(
              new TitleText("Warehouse Order Tracking Application"),
              //Here is the tabPane to contain both the purchase and customer orders.
              new TabPane {
                minWidth = 400
                tabs = Seq(
                  new Tab {
                    text = "Purchase Orders"
                    content = new BorderPane {
                      center_=(new tableScrollPane(t))

                      //Functional buttons for the bottom of the Purchase Order Tab
                      bottom_=(new BorderPane {

                        left_=(new GridPane {

                          hgap_=(20)
                          vgap_=(6)

                          alignmentInParent_=(scalafx.geometry.Pos.TopLeft)
                          add(new Text { text = "Filters"; font = new Font("Verdana", 15) }, 0, 0)
                          add(new Label("Status to filter by: "), 0, 1)

                          val comboBox: StatusComboBox = new StatusComboBox
                          val checkBox: CheckBox = new CheckBox { text = "Exclude Status?" }
                          val idBox: IDTextField = new IDTextField

                          add(comboBox, 1, 1)
                          add(checkBox, 2, 1)
                          add(new Label("Filter by ID: "), 0, 2)
                          add(idBox, 1, 2)
                          add(new Button {
                            text = "Search"
                            minWidth = 110
                            val sorter: CaseSorter = new CaseSorter
                            onAction = handle(reSetCusto(custo, sorter.comboBoxInterpretFilterStatus, comboBox.value.value.toString(), checkBox.selected.value))
                          }, 2, 3)
                          add(new Button {
                            text = "Create PO"
                            minWidth = 110
                            onAction = handle(newPurchaseOrder(getNextPOID()))
                          }, 5, 1)
                        })
                      })
                    }
                    closable = false
                  },
                  new CustomerOrderTab(c))
              })
          })
        }
      }
    }
    return stage
  }

  /**
   * Method to open the pane to create a new Purchase Order.
   */
  def newPurchaseOrder(poid: String): Unit = {
    val npo: createPurchaseOrder = new createPurchaseOrder
    stage = npo.buildCPOStage(poid)
  }

  /**
   * Method to get the next Purchase Order ID Number to use.
   */
  def getNextPOID(): String = {
    val poc: purchaseOrderController = new purchaseOrderController()
    val po: ObservableBuffer[purchaseOrder] = poc.getPurchaseOrders(0, false)
    return (po.length + 1).toString()
  }

  /**
   * Method to return the status of a customer order.
   */
  def getPurchaseOrders(status: Int, filter: Boolean): ObservableBuffer[purchaseOrder] = {
    val poc: purchaseOrderController = new purchaseOrderController()
    return poc.getPurchaseOrders(status, filter)
  }

  /**
   * Method to re-set the observable buffer of purchase orders when filtering occurs.
   */
  def reSetCusto(c: ObservableBuffer[purchaseOrder], f: String => Int, statusFilter: String, filter: Boolean): Unit = {
    val status = f(statusFilter)
    c.clear()
    c.++=(getPurchaseOrders(status, filter))
  }

  /**
   * Method to build the individual Purchase Order Page.
   * Works as all other window views, by overriding the stage currently displayed.
   * All stages therefore have some kind of 'back' button.
   */
  def indvPO(orderID: String, statusID: String) {
    val m: indvPurchaseOrderWindow = new indvPurchaseOrderWindow(orderID, statusID)
    //println("HERE")
    stage = m.buildIndvPOStage()
  }
}