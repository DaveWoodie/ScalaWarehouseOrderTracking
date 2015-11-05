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

object mainWindow extends JFXApp{
  
  /**
   * Main method to build the panels for holding items
   */
  
  stage = new PrimaryStage { 
    title = "Welcome Page"
    width = 1298
    height = 762
    
    resizable_=(false)
    
    scene = new Scene {
      fill = White
     
      content = new HBox {
        new Text {
          text = "Heyo"

        }
        padding = Insets(60, 100, 0, 40)
        
      }
      
    }
    
  }
  
}