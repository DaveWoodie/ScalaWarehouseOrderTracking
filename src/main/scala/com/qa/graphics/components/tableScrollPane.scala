package com.qa.graphics.components

import scalafx.scene.control.ScrollPane
import scalafx.scene.control.TableView
import com.qa.entities.purchaseOrder
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

/**
 * Class for making a scrolling pane with a table in it.
 * Partialy defined for the building of a scroll pane containing any table view.
 */
class tableScrollPane(t: TableView[_]) extends ScrollPane {
  alignmentInParent_=(scalafx.geometry.Pos.Center)
  minWidth = 400
  minHeight = 500
  maxHeight = 500
  content = t
}