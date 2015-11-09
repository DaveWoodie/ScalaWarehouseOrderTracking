/**
 * @author dwoodward
 */
package graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.geometry.{Pos, VPos}
import scalafx.scene.Scene
import scalafx.scene.control.{Accordion, Label, ScrollPane, TitledPane}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.layout.GridPane
import scalafx.scene.control.{TabPane, Tab, CheckBox, ComboBox}
import scalafx.scene.control.TextField
import scalafx.scene.control.PasswordField
import scalafx.scene.control.Button
import scalafx.geometry.Pos
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Circle
import scalafx.scene.text.{Font, Text}
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
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.control.Tab
import Entities.purchaseOrder
//java....
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import javafx.event.ActionEvent

class mainWindow extends JFXApp{
  
  /**
   * Main method to build the panels for holding items
   */
  def buildMainStage():PrimaryStage = {
    

  
    stage = new PrimaryStage { 
      title = "Welcome to the Warehouse Order Tracking System"
      width = 800
      height = 762
      resizable_=(false)
      
      scene = new Scene {

        root = new BorderPane {
          
          padding = Insets(20, 20, 20, 20)
          top_=(new Label {
            text = " "
            //padding = Insets(60, 100, 0, 40)
            alignmentInParent_=(scalafx.geometry.Pos.Center)
          })
          center_=(new VBox {
              
            children = List(

                new Text {
                  text = "Warehouse Order Tracking Application"
//                  padding = Insets(0, 10, 10, 10)
                  font = new Font("Verdana", 20)
                },
                
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
                              //Here is where the table is built
                              content = buildPOTable()
                            }
                          )
                      }
                      closable = false
                    },
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
                            }
                          )
                      }
                      
                      closable = false
                    }
                  )
                }
              )
            }
          )
          
          bottom_=(new BorderPane {
            
            left_= (new GridPane { 
              hgap_=(20)
              vgap_=(6)
              
              alignmentInParent_=(scalafx.geometry.Pos.TopLeft)
              
              add(new Text { text = "Filters"; font = new Font("Verdana", 15) }, 0, 0)
              
              add(new Label ("Status to filter by: "), 0, 1)
              add(new Button { text = "Button 1" }, 1, 1)
              add(new CheckBox { text = "Exclude Status?" }, 2, 1)

              add(new Label ("Filter by ID: "), 0, 2)
              

              add(new ComboBox[String] { 
                //here are the options for the combo Box 
                val testStrings = ObservableBuffer[String] ("Option 1", "Option 2", "Option 3")
                promptText = "Choose one"
                minWidth = 100
                items = testStrings
              }, 1, 2)

              add(new Button { text = "Search" }, 2, 3)
            }
          )
          }
          )
        }
      }
    }
    return stage
  }
  
  def buildPOTable(): TableView[Person] = {
    
    //*************************************************************
    //Data here should be changed for the things from the database 
    //Entities are ready to store the data though so should be clean sailing
    
    val purchaseOrders = ObservableBuffer[purchaseOrder] (
      
        
    
    )
    
    //*************************************************************
    
    val characters = ObservableBuffer[Person](
    new Person("Peggy", "Sue", "555-6798", Color.Violet),
    new Person("Rocky", "Raccoon", "555-6798", Color.GreenYellow),
    new Person("Bungalow ", "Bill", "555-9275", Color.DarkSalmon)
    )
    
    new TableView[Person](characters) {
      minWidth = 752
      minHeight = 496
//      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(javafx.geometry.Pos.CENTER)
      
      //*****************************
      //On click actions!!
      onMouseClicked = new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent) {

          if(event.getClickCount == 2) {
            println("Double Clicked")
            event.consume
            indvPO
          }

        }
      }
      //*****************************
      
      columns ++= List(
            
        new TableColumn[Person, String] {
          text = "Purchase Order ID"
          cellValueFactory = {_.value.firstName }
          prefWidth = 300
        },
        new TableColumn[Person, String]() {
          text = "Last Name"
          cellValueFactory = {_.value.lastName }
          prefWidth = 100
        },
        
        new TableColumn[Person, Color] {
            text = "Favorite Color"
            cellValueFactory = { _.value.favoriteColor }
            // Render the property value when it changes, 
            // including initial assignment
            cellFactory = { (col:TableColumn[Person, Color]) => 
              new TableCell[Person, Color] {
                item.onChange { (_, _, newColor) => 
                  graphic = new Circle {fill = newColor; radius = 8; alignmentInParent_=(javafx.geometry.Pos.CENTER)}
                }
                alignmentInParent_=(scalafx.geometry.Pos.Center)
              }
            }
            prefWidth = 200
        }
      )
    }
  }
  
  
  
  def indvPO() {
    val m: indvPurchaseOrderWindow = new indvPurchaseOrderWindow(1)
    stage = m.buildIndvPOStage()
  }
  
  
  
  def buildCOTable(): TableView[Person] = {
    
    val characters = ObservableBuffer[Person](
    new Person("Phil", "Filler", "555-6798", Color.Black),
    new Person("Baloo", "The Bear", "555-6798", Color.GreenYellow),
    new Person("Ben", "Gottcha-Back", "555-9275", Color.Aquamarine)
    )
    
    new TableView[Person](characters) {
      minWidth = 752
      minHeight = 496
//      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(javafx.geometry.Pos.CENTER)
      columns ++= List(
            
        new TableColumn[Person, String] {
          text = "Customer Order ID"
          cellValueFactory = {_.value.firstName }
          prefWidth = 300
        },
        new TableColumn[Person, String]() {
          text = "Last Name"
          cellValueFactory = {_.value.lastName }
          prefWidth = 100
        },
        
        new TableColumn[Person, Color] {
            text = "Favorite Color"
            cellValueFactory = { _.value.favoriteColor }
            // Render the property value when it changes, 
            // including initial assignment
            cellFactory = { (col:TableColumn[Person, Color]) => 
              new TableCell[Person, Color] {
                item.onChange { (_, _, newColor) => 
                  graphic = new Circle {fill = newColor; radius = 8; alignmentInParent_=(javafx.geometry.Pos.CENTER)}
                }
                alignmentInParent_=(scalafx.geometry.Pos.Center)
              }
            }
            prefWidth = 200
        }
      )
    }
  }
}