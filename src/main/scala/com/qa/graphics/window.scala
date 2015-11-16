/**
 * @author dwoodward
 */
package com.qa.graphics

//Main imports
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.geometry.{Pos, VPos}
import scalafx.scene.control.{Accordion, Label, ScrollPane, TitledPane, TextField, PasswordField, Button}
import scalafx.scene.layout.{Pane, HBox, GridPane, BorderPane}
import scalafx.event.{ActionEvent, EventHandler}
import scalafx.Includes._
import scalafx.scene.paint.Color._
import scalafx.scene.text.{Font, Text}
import javax.swing.text.Position
import scalafx.geometry.Pos
import com.qa.logic.loginLogic
import com.qa.graphics.components.LoginTitle
import com.qa.controllers.LoginController

/**
 * Class to make the main login window.
 */
object window extends JFXApp {
  /**
   * Main method to create the Login panel.
   * Generates a small login panel with fields of username, and password.
   * As well as a button to submit the login.
   */
  stage = new PrimaryStage {
    title = "Login"
    width = 360
    height = 280
    resizable_=(false)

    scene = new Scene {
      //Creates the pane for the objects to be put in.
      root = new BorderPane {
        //STYLESHEET!!!
        id = "mainpane"
        stylesheets = List(getClass.getResource("login.css").toExternalForm)
        
        padding = Insets(50, 50, 60, 80)
        top_=(new LoginTitle)
        center_=(buildInputGrid)
      }
    }
  }
  /**
   * Method to build the input table for username and password.
   * @return : returns the GridPane populated with the buttons and fields.
   */
  def buildInputGrid(): GridPane = {

    //Makes the new grid pane to hold the components
    val g: GridPane = new GridPane {

      val usr = new TextField
      val pss = new PasswordField

      add(new Label("Username:"), 0, 0)
      add(usr, 1, 0)
      add(new Label("Password:"), 0, 1)
      add(pss, 1, 1)
      var b: Button = new Button {
        text = "Login"
        alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
        val l: LoginController = new LoginController
        onAction = handle(stage = l.loginButton(usr.text.value.toString(), pss.text.value.toString()))
      }
      add(b, 1, 2)
      hgap_=(10)
      vgap_=(10)
      alignmentInParent_=(scalafx.geometry.Pos.CenterRight)
    }
    return g
  }
}