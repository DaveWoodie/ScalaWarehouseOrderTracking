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

class createPurchaseOrder extends JFXApp {

  def buildCPOStage(): PrimaryStage = {

    stage = new PrimaryStage {
      title = "Create a new Purchase Order"
      width = 800
      height = 782
      resizable_=(false)

      scene = new Scene {

        root = new BorderPane {

          var custo: ObservableBuffer[purchaseOrder] = new ObservableBuffer[purchaseOrder]
          var t: TableView[purchaseOrder] = new TableView[purchaseOrder]

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

                  new ComboBox[String] {
                    //here are the options for the combo Box 
                    val testStrings = ObservableBuffer[String]("1", "2", "3", "4")

                    promptText = "Choose a supplier"
                    minWidth = 150
                    items = testStrings
                  },

                  new Button {
                    text = "Cancel"
                    minWidth = 150
                    val m: mainWindow = new mainWindow()
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
                  val testStrings = ObservableBuffer[String]("1", "2", "3", "4")

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
                    onAction = handle(println("TO DO STUBB"))
                  })
              })
          })

          bottom_=(
            new Button {
              text = "Add to Purchase Orders"
              onAction = handle(println("Push to the database HERE"))
            })
        }
      }
    }
    return stage
  }

}