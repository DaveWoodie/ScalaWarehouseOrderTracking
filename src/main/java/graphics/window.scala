/**
 * @author dwoodward
 */
package graphics

//Main imports
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
//Layout setup components
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.geometry.{Pos, VPos}
import scalafx.scene.Scene
import scalafx.scene.control.{Accordion, Label, ScrollPane, TitledPane}
import scalafx.scene.layout.Pane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.GridPane
import scalafx.scene.control.TextField
import scalafx.scene.control.PasswordField
import scalafx.scene.control.Button
//Event handling for buttons
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.Includes._
//Style parts of the import
import scalafx.scene.paint.Color._
import scalafx.scene.text.{Font, Text}
import scalafx.scene.layout.BorderPane
import javax.swing.text.Position
import scalafx.geometry.Pos
//CSS for possible further use

//Imports the logic classes, buttonLogic is currently unused.
import logic.loginLogic
import graphics.mainWindow

object window extends JFXApp {

//  lazy val bl: buttonLogic = new buttonLogic()
  
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
      
      //STYLESHEET!!!
      stylesheets = List(getClass.getResource("login.css").toExternalForm)
//      fill = White
      
      //Creates the pane for the objects to be put in.
      root = new BorderPane {
        id = "mainpane" 
          //Padding to put the pane in the right place.
          //Padding is assigned from top, left, bottom, right
          padding = Insets(50, 50, 60, 80)  
          
          //Locations for the Two components of the display.
          top_=(buildTitle)
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
//      padding = Insets(10)
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
      var b: Button = new Button{ 
        text = "Login"
        alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
        //Here is where the action the button performs is assigned. 
        onAction = handle (loginButton(usr.text.value.toString(), pss.text.value.toString()))
      }
//      val q: buttonLogic = new buttonLogic(loginButton)
//      q.assign(b)
      
      add(b, 1, 2)
      
      hgap_=(10)
      vgap_=(10)
      
      alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
    }
    return g
  }
  
  /**
   * Applies the logic of the login button
   */
  def loginButton(usr: String, pss: String): Unit = {
    
    lazy val log: loginLogic = new loginLogic
    
    //println(usr + " " + pss)
    
    if (log.loginCheck(usr, pss)) {
      println("Login Successful")
      
      val m: mainWindow = new mainWindow()
      
      stage = m.buildMainStage()
      
    }
    else {
      //LEARNING STUFF HERE NEED MORE THOUGH 
      println("Login Failed")
      //I attempted to have the label change but have yet
      //to get it working.
    }
  }
}