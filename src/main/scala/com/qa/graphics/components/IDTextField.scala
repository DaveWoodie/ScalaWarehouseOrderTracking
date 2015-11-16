package com.qa.graphics.components

import scalafx.scene.control.TextField

class IDTextField extends TextField {
  promptText = "Enter an ID"
  minWidth = 150
  //Just here until the functionality to filter by ID is added
  disable = true
}