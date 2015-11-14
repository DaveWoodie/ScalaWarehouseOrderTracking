package graphics.components

import scalafx.scene.layout.GridPane
import scalafx.scene.control.TextField
import scalafx.scene.control.PasswordField
import scalafx.scene.control.Label
import scalafx.scene.control.Button
import controllers.LoginController
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._

/**
 * Currently unused class.
 */

class LoginInputGrid extends GridPane {
  
  //This class was an attempt to get the login grid to display on the Login Page as an object
  //I have once again struggled with the buttons that overwrite the stages again.
  //Can't help but feel there's a simpler way to do it but I can't see it at the moment.

  def buildLoginGrid(loginStage: PrimaryStage): PrimaryStage = {
    var stage = loginStage
    val g: GridPane = new GridPane {

      val usr = new TextField
      val pss = new PasswordField

      add(new Label("Username:"), 0, 0)
      add(usr, 1, 0)
      add(new Label("Password:"), 0, 1)
      add(pss, 1, 1)
      val b: Button = new Button {
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
    return stage
  }
}