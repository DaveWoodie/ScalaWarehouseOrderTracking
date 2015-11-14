package controllers

import logic.loginLogic
import graphics.mainWindow
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.JFXApp

class LoginController {
  
    /**
   * Applies the logic of the login button
   */
  def loginButton(usr: String, pss: String): PrimaryStage = {

    lazy val log: loginLogic = new loginLogic

    if (log.loginCheck(usr, pss)) {
      println("Login Successful")

      val m: mainWindow = new mainWindow()

      return m.buildMainStage()

    } else {
      //LEARNING STUFF HERE NEED MORE THOUGH 
      println("Login Failed")
      return null
    }
  }
}