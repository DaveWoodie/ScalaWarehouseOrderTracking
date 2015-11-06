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
import scalafx.scene.layout.HBox
import scalafx.scene.layout.GridPane
import scalafx.scene.control.{TabPane, Tab}
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

class mainWindow extends JFXApp{
  
  val characters = ObservableBuffer[Person](
    new Person("Peggy", "Sue", "555-6798", Color.Violet),
    new Person("Rocky", "Raccoon", "555-6798", Color.GreenYellow),
    new Person("Bungalow ", "Bill", "555-9275", Color.DarkSalmon)
  )

  
  /**
   * Main method to build the panels for holding items
   */
  def buildMainStage():PrimaryStage = {
    
    val characters = ObservableBuffer[Person](
    new Person("Peggy", "Sue", "555-6798", Color.Violet),
    new Person("Rocky", "Raccoon", "555-6798", Color.GreenYellow),
    new Person("Bungalow ", "Bill", "555-9275", Color.DarkSalmon)
    )
    
    
    stage = new PrimaryStage { 
      title = "Welcome Page"
      width = 800
      height = 762
      resizable_=(false)
      
      scene = new Scene {

        content = new BorderPane {
          
          padding = Insets(20, 10, 20, 20)
          
          var tabs: TabPane = new TabPane()
          var table: TableView[Person] = buildTable()
          
          var f: Tab = new Tab("ONE")
          
          center_=(
              
              
          
          )
          
          top_=(new Label {
            text = "Heyo"
            //padding = Insets(60, 100, 0, 40)
            alignmentInParent_=(scalafx.geometry.Pos.Center)
          })
          
          
        }
      }
    }
    return stage
  }
  
  def buildTable(): TableView[Person] = {
    new TableView[Person](characters) {
      columns ++= List(
        new TableColumn[Person, String] {
          text = "First Name"
          cellValueFactory = {_.value.firstName }
          prefWidth = 100
        },
        new TableColumn[Person, String]() {
          text = "Last Name"
          cellValueFactory = {_.value.lastName }
          prefWidth = 100
        }
      )
    }
  }
}