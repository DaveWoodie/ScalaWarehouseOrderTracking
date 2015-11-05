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
import logic.loginLogic

object window extends JFXApp{
  
  lazy val log: loginLogic = new loginLogic
  
  /**
   * Main method to create the Login panel.
   * Generates a small login panel with fields of username, and password.
   * As well as a button to submit the login.
   */
  stage = new PrimaryStage {
    //Title
    title = "Login"
    
    //Set dimensions and whether the window can be re-sized
    width = 360
    height = 280
    resizable_=(false)
    
    //Makes the new scene.
    scene = new Scene {
      fill = White
      
      //Creates the pane for the objects to be put in.
      content = new BorderPane {
        
        //Padding to put the pane in the right place.
        //Padding is assigned from top, left, bottom, right
        padding = Insets(50, 60, 10, 60)  
        
        //Locations for the Two components of the display.
        top_=(buildTitle())
        center_=(buildInputGrid)
      }
    }
    
    //Add a css style sheet! Maybe one day!
    //Stylesheet.loadBinary("login.css")
  }
  
  /**
   * Method to build the title of the page,
   * sits in the top box of the border layout.
   * Returns nothing but text, custom effects may be added later.
   */
  def buildTitle(): Text = {
    new Text {
      //Defines the text to be added
      text = "Login"
      style = "-fx-font-size: 26pt"
      alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
    }
  }
  
  /**
   * Method to build the input table for username and password.
   */
  def buildInputGrid():GridPane = {
   
    //Makes the new grid pane to hold the components
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
        //This will be changed to an instanciation of a class that takes in
        // a Method and a Button and assigns the method to the button,
        //Very functional!! 
        onAction = {ae: ActionEvent => {
          
            if (log.loginCheck(usr.text.value.toString(), pss.text.value.toString())) {
              println("Login Successful")
            }
            else {
              //LEARNING STUFF HERE NEED MORE THOUGH 
              println("Login Failed")
              //I attempted to have the label change but have yet
              //to get it working.
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