package graphics

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, VBox}
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.text.{Font, Text}
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import scalafx.collections.ObservableBuffer
//MIGHT USE THESE to represent statuses 
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class indvPurchaseOrderWindow(purchaseOrderID_ : String) extends JFXApp {
  
  def buildIndvPOStage(): PrimaryStage = {
    
    stage = new PrimaryStage {
      
      title = "Purchase Order ID : " + purchaseOrderID_.toString()
      width = 800
      height = 762
      resizable_=(false)

        scene = new Scene {
        
        root = new BorderPane {
          padding = Insets(20, 20, 20, 20) 
          
          center_=(new VBox {
            
            children = List(
                
                new Text {
                  text = "Warehouse Order Tracking Application"
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
                }
            )
          }
          )
        }
        
      }
      
    }
    return stage
  }
  
  def buildPurchaseOrderTable(purchaseOrderID : String): TableView[Person] = {
    
    //Reason for taking in the purchase order ID here is so that when the method is called
    //The individual purchase order ID that is passed can be pulled from the database
   
    val characters: ObservableBuffer[purchaseOrderLine] = new ObservableBuffer[purchaseOrderLine]
    
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