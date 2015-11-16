package com.qa.graphics.components

import scalafx.scene.text.Text

/**
 * Class to build the title of the page,
 * sits in the top box of the border layout.
 * Returns nothing but text, custom effects may be added later.
 */
class LoginTitle extends Text {

  //Defines the text to be added
  text = "Login"
  style = "-fx-font-size: 26pt"
  alignmentInParent_=(scalafx.geometry.Pos.CenterRight)

}