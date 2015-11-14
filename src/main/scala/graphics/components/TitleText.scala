package graphics.components

import scalafx.scene.text.{Text, Font}

/**
 * Class to make the title text for the Window Panes.
 */
class TitleText(s: String) extends Text {
  text = s
  font = new Font("Verdana", 20)
}