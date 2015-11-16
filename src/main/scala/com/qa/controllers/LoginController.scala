package com.qa.controllers

import com.qa.logic.loginLogic
import com.qa.graphics.mainWindow
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.JFXApp

/**
 * Currently unused for the controller.
 */

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