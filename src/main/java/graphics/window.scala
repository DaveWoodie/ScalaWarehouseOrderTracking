/**
 * @author dwoodward
 */
package graphics

import java.net.URL
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
import logic.loginLogic

object window extends JFXApp{
  
  lazy val log: loginLogic = new loginLogic
  
  /**
   * Main method to build the panels for holding items.
   */
  stage = new PrimaryStage {
    title = "Login"
    width = 360
    height = 280
    
    resizable_=(false)
    
    scene = new Scene {
      fill = White
      
      content = new BorderPane {
        
        padding = Insets(10)
        
//        padding = Insets(30)
        //Something here is messing up moving the border pane to the center of the scene.
      //  alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
        
        top_=(new Text {
          text = "Login"
          
          //Padding is assigned from top, left, bottom, right
          padding = Insets(60, 60, 10, 60)
          style = "-fx-font-size: 26pt"
          alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
        })
        
        center_=(buildInputGrid)
      }
    }
    
    //Add a css style sheet!
    
    //Stylesheet.loadBinary("login.css")
     
  }
  
  
  /**
   * Method to build the input login table for username and password.
   */
  def buildInputGrid():GridPane = {
   
    val g: GridPane = new GridPane {
      
      //In order to give the button access to the text in the fields you use these vals.
      val usr = new TextField
      val pss = new PasswordField
      var lbl = new Label
      
      add(new Label ("Username:"), 0, 0)
      add(usr, 1, 0)
      add(new Label ("Password:"), 0, 1)
      add(pss, 1, 1)
      add(new  Button {
        text = "Login"
        //Here is where the action the button performs is assigned. 
        onAction = {ae: ActionEvent => {
          
            if (log.loginCheck(usr.text.value.toString(), pss.text.value.toString())) {
              println("Login Successful")
            }
            else {
              //LEARNING STUFF HERE NEED MORE THOUGH 
              println("Login Failed")
              lbl = new Label("Incorrect login details entered.")
              add(lbl, 2, 2)
            }
          }
        
        }
        alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
      }, 1, 2)
//      add(lbl, 2, 2)
      
      hgap_=(10)
      vgap_=(10)
      
      alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
    }
    return g
  }
  
}