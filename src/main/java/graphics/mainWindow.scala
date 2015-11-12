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
import Entities.purchaseOrder

class mainWindow extends JFXApp {

  /**
   * Main method to build the panels for holding items
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
          //How this works is the filters are applied to the custo object, this is the 
          //ObservableBuffer of purchaseOrders and the table relies on this
          var custo: ObservableBuffer[purchaseOrder] = getPurchaseOrders(0, false)
          var t: TableView[purchaseOrder] = buildPOTable(custo)
          //*******************************************************************************************
          padding = Insets(20, 20, 20, 20)
          top_=(new Label {
            text = " "
            //padding = Insets(60, 100, 0, 40)
            alignmentInParent_=(scalafx.geometry.Pos.Center)
          })
          //A lot of the V and HBoxes inside objects are just to make the layout look pretty. Not essential.
          center_=(new VBox {

            //Children of the VBox
            children = List(

              new Text {
                text = "Warehouse Order Tracking Application"
                //                  padding = Insets(0, 10, 10, 10)
                font = new Font("Verdana", 20)
              },

              //Here is the tabPane to contain both the purchase and customer orders.
              new TabPane {
                minWidth = 400
                tabs = Seq(
                  new Tab {
                    text = "Purchase Orders"
                    content = new BorderPane {
                      center_=(
                        new ScrollPane {
                          alignmentInParent_=(javafx.geometry.Pos.CENTER)
                          minWidth = 400
                          minHeight = 500
                          maxHeight = 500
                          content = t
                          //*******************************************************************
                          //Heres the double click functionality
                          //*******************************************************************
                          t.onMouseClicked = new EventHandler[MouseEvent] {
                            override def handle(event: MouseEvent) {
                              //println(t.selectionModel.value.getFocusedIndex.toString())
                              //the t.selectionModel condtion is no longer nessesary but was used for bug testing and will
                              //be used again so it has been left in
                              if (event.getClickCount == 2 && (t.selectionModel.value.getFocusedIndex + 1).toString() != "0") {
                                //                                println("Double Clicked")
                                //I have yet to work out why event.consume is nessesary, but it is.
                                event.consume
                                //println(t.getSelectionModel.selectedItemProperty.get.purchaseID.value)
                                //Without the .value on the end this returns the identity of the selected items 'bean'
                                indvPO(t.getSelectionModel.selectedItemProperty.get.purchaseID.value, t.getSelectionModel.selectedItemProperty.get.statusID.value)
                              }
                            }
                          }
                        })
                        //Functional buttons for the bottom of the Purchase Order Tab
                      bottom_=(new BorderPane {
                        
                        left_=(new GridPane {

                          hgap_=(20)
                          vgap_=(6)

                          alignmentInParent_=(scalafx.geometry.Pos.TopLeft)
                          add(new Text { text = "Filters"; font = new Font("Verdana", 15) }, 0, 0)
                          add(new Label("Status to filter by: "), 0, 1)

                          val comboBox: ComboBox[String] = new ComboBox[String] {
                            //here are the options for the combo Box 
                            val testStrings = ObservableBuffer[String]("", "Status 1", "Status 2", "Status 3")

                            //ID for the box so other parts can access it  
                            //I never managed to get this designation of ID to work. Maybe it can be used.
                            id = "STATUSBOX"

                            //Possibly a silly prompt but never mind!
                            promptText = "Choose one"
                            minWidth = 150
                            //Here is where the options for the comboBox are added to it
                            items = testStrings
                          }
                          
                          //Adds are how to add the components to the GridPane the numbers specify the column and then row
                          add(comboBox, 1, 1)

                          //CheckBoxs and idBox have to be defined outside the add method in order
                          //to be able to access their values in another Object or Function
                          //(This is unless the id= thing can ever be made to work by me)
                          val checkBox: CheckBox = new CheckBox { text = "Exclude Status?" }
                          add(checkBox, 2, 1)

                          add(new Label("Filter by ID: "), 0, 2)

                          val idBox: TextField = new TextField {
                            promptText = "Enter an ID"
                            minWidth = 150
                            editable = false
                          }

                          add(idBox, 1, 2)
                          add(new Button {

                            text = "Search"
                            minWidth = 110
                            //Basic method to handle button clicks
                            //ComboBox requires .value.value to get the value, the first value returns the ID of the bean of the comboBox
                            onAction = handle(reSetCusto(custo, comboBoxInterpret(comboBox.value.value.toString()), checkBox.selected.value))
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
                  
                  //Finally here is the other tab for the customer orders.
                  //Currently there is no functionality tied to this
                  new Tab {
                    text = "Customer Orders"

                    content = new BorderPane {
                      center_=(
                        new ScrollPane {
                          alignmentInParent_=(javafx.geometry.Pos.CENTER)
                          minWidth = 400
                          minHeight = 500
                          maxHeight = 500
                          //Here is where the table is built
                          content = buildCOTable()
                        })
                    }

                    closable = false
                  })
              } //***********
              )
          })

          //       
        }
      }
    }
    return stage
  }
  
  /**
   * Method to open the pane to create a new Purchase Order
   */
  def newPurchaseOrder(poid: String): Unit = {
    val npo: createPurchaseOrder = new createPurchaseOrder
    //Overwrites the stage
    stage = npo.buildCPOStage(poid)
  }
  
  /**
   * Method to get the next Purchase Order ID Number to use
   */
  def getNextPOID(): String = {
    val poc: purchaseOrderController = new purchaseOrderController()
    val po: ObservableBuffer[purchaseOrder] = poc.getPurchaseOrders(0, false)
    return (po.length + 1).toString()
  }

  /**
   * Method to interpret the status into a Colour for printing 
   */
  def comboBoxInterpret(s: String): Int = {
    var i: Int = 0;
    if (s.equals("Status 1")) {
      i = 1
    } else if (s.equals("Status 2")) {
      i = 2
    } else if (s.equals("Status 3")) {
      i = 3
    }
    return i
  }

  def getPurchaseOrders(status: Int, filter: Boolean /*, poID: Int*/ ): ObservableBuffer[purchaseOrder] = {

    println("HERE")
    val poc: purchaseOrderController = new purchaseOrderController()
    return poc.getPurchaseOrders(status, filter)
  }

  def reSetCusto(c: ObservableBuffer[purchaseOrder], status: Int, filter: Boolean): Unit = {
    c.clear()
    c.++=(getPurchaseOrders(status, filter))
  }

  def getColour(i: String): Color = {
    var c: Color = Color.White
    if (i.equals("1")) {
      c = Color.Red
    } else if (i.equals("2")) {
      c = Color.Orange
    } else if (i.equals("3")) {
      c = Color.LimeGreen
    }
    return c
  }

  def buildPOTable(purchaseOrders: ObservableBuffer[purchaseOrder]): TableView[purchaseOrder] = {

    new TableView[purchaseOrder](purchaseOrders) {
      minWidth = 752
      minHeight = 496
      alignmentInParent_=(javafx.geometry.Pos.CENTER)

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
                  graphic = new Circle { fill = getColour(newColor); radius = 8 }
                }
                else {
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

  def indvPO(orderID: String, statusID: String) {
    val m: indvPurchaseOrderWindow = new indvPurchaseOrderWindow(orderID, statusID)
    println("HERE")
    stage = m.buildIndvPOStage()
  }

  def buildCOTable(): TableView[Person] = {

    val characters = ObservableBuffer[Person](
      new Person("1", "Filler", "555-6798", Color.Black),
      new Person("2", "The Bear", "555-6798", Color.GreenYellow),
      new Person("3", "Gottcha-Back", "555-9275", Color.Aquamarine))

    new TableView[Person](characters) {
      minWidth = 752
      minHeight = 496
      //      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(javafx.geometry.Pos.CENTER)
      columns ++= List(

        new TableColumn[Person, String] {
          text = "Customer Order ID"
          cellValueFactory = { _.value.firstName }
          prefWidth = 300
        },
        new TableColumn[Person, String]() {
          text = "Last Name"
          cellValueFactory = { _.value.lastName }
          prefWidth = 100
        },

        new TableColumn[Person, Color] {
          text = "Favorite Color"
          cellValueFactory = { _.value.favoriteColor }
          // Render the property value when it changes, 
          // including initial assignment
          cellFactory = { (col: TableColumn[Person, Color]) =>
            new TableCell[Person, Color] {
              item.onChange { (_, _, newColor) =>
                graphic = new Circle { fill = newColor; radius = 8; alignmentInParent_=(javafx.geometry.Pos.CENTER) }
              }
              alignmentInParent_=(scalafx.geometry.Pos.Center)
            }
          }
          prefWidth = 200
        })
    }
  }
}