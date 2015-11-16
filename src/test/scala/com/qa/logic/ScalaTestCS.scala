package com.qa.logic

import org.scalatest._

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._

/**
 * Class to test nearly all the case and match statements that the project uses.
 * This should run without a database connection
 * Update button method cannot be tested except that it returns a button which 
 * is not an informative test. 
 */

class ScalaTestCS extends FlatSpec with Matchers {
  
  "The getColour method" should "return Color.Red if the input '1' is entered" in {
    val cs = new CaseSorter
    assertResult(Color.Red)(cs.getColour("1"))
  }
  
  "The getColour method" should "return Color.Orange if the input '2' is entered" in {
    val cs = new CaseSorter
    assertResult(Color.Orange)(cs.getColour("2"))
  }
  
  "The getColour method" should "return Color.LimeGreen if the input '3' is entered" in {
    val cs = new CaseSorter
    assertResult(Color.LimeGreen)(cs.getColour("3"))
  }
  
  "The getColour method" should "return Color.White if the input anything is entered" in {
    val cs = new CaseSorter
    assertResult(Color.White)(cs.getColour("antyhitnfd"))
  }

  "The getCustomerColour method" should "return Color.Red if the input '1' is entered" in {
    val cs = new CaseSorter
    assertResult(Color.Red)(cs.getCustomerColour("1"))
  }
  
  "The getCustomerColour method" should "return Color.Orange if the input '4' is entered" in {
    val cs = new CaseSorter
    assertResult(Color.Orange)(cs.getCustomerColour("4"))
  }
  
  "The getCustomerColour method" should "return Color.LimeGreen if the input '5' is entered" in {
    val cs = new CaseSorter
    assertResult(Color.LimeGreen)(cs.getCustomerColour("5"))
  }
  
  "The getCustomerColour method" should "return Color.White if the input anything is entered" in {
    val cs = new CaseSorter
    assertResult(Color.Black)(cs.getCustomerColour("antyhitnfd"))
  }
  
  
  "The CustomerComboBoxInterpretFilterStatus method" should "return 1 if the input 'Placed' is entered" in {
    val cs = new CaseSorter
    assertResult(1)(cs.CustomerComboBoxInterpretFilterStatus("Placed"))
  }
  
  "The CustomerComboBoxInterpretFilterStatus method" should "return 2 if the input 'Claimed' is entered" in {
    val cs = new CaseSorter
    assertResult(2)(cs.CustomerComboBoxInterpretFilterStatus("Claimed"))
  }
  
  "The CustomerComboBoxInterpretFilterStatus method" should "return 3 if the input 'Picked' is entered" in {
    val cs = new CaseSorter
    assertResult(3)(cs.CustomerComboBoxInterpretFilterStatus("Picked"))
  }
  
  "The CustomerComboBoxInterpretFilterStatus method" should "return 4 if the input Packed is entered" in {
    val cs = new CaseSorter
    assertResult(4)(cs.CustomerComboBoxInterpretFilterStatus("Packed"))
  }
  
  "The CustomerComboBoxInterpretFilterStatus method" should "return 5 if the input 'Out for Delivery' is entered" in {
    val cs = new CaseSorter
    assertResult(5)(cs.CustomerComboBoxInterpretFilterStatus("Out for Delivery"))
  }
  
  "The CustomerComboBoxInterpretFilterStatus method" should "return 0 if the input anythignelse is entered" in {
    val cs = new CaseSorter
    assertResult(0)(cs.CustomerComboBoxInterpretFilterStatus("antyhitnfd"))
  }
  
  
  "The comboBoxInterpretFilterStatus method" should "return 3 if the input 'Status 1' is entered" in {
    val cs = new CaseSorter
    assertResult(1)(cs.comboBoxInterpretFilterStatus("Status 1"))
  }
  
  "The comboBoxInterpretFilterStatus method" should "return 4 if the input Status 2 is entered" in {
    val cs = new CaseSorter
    assertResult(2)(cs.comboBoxInterpretFilterStatus("Status 2"))
  }
  
  "The comboBoxInterpretFilterStatus method" should "return 5 if the input 'Status 3' is entered" in {
    val cs = new CaseSorter
    assertResult(3)(cs.comboBoxInterpretFilterStatus("Status 3"))
  }
  
  "The comboBoxInterpretFilterStatus method" should "return 0 if the input anythignelse is entered" in {
    val cs = new CaseSorter
    assertResult(0)(cs.comboBoxInterpretFilterStatus("antyhitnfd"))
  }
  
}