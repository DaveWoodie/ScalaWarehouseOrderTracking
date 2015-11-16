package com.qa.graphics.components

import scalafx.scene.control.ComboBox
import scalafx.collections.ObservableBuffer

/**
 * Class to make the Id Item box object.
 * Currently this only includes a hard coded values of the item IDs,
 * In future this data will be pulled from the database.
 */
class ItemIDComboBox extends ComboBox[String] {
  val testStrings = ObservableBuffer[String]("1", "2", "3", "4", "5", "6", "7")
  promptText = "Choose an Item ID"
  minWidth = 150
  items = testStrings
}