package com.qa.graphics.components

import scalafx.scene.control.TextField

/**
 * Class to make the Id Text field 
 * Currently disabled as the functionality to make a search based on ID Will be added later.
 */
class IDTextField extends TextField {
  promptText = "Enter an ID"
  minWidth = 150
  //Just here until the functionality to filter by ID is added
  disable = true
}