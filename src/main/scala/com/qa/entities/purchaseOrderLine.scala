/**
 * @author dwoodward
 */

package com.qa.entities

import scalafx.beans.property.{ObjectProperty, StringProperty, IntegerProperty}

class purchaseOrderLine (itemID_ : String, purchaseOrderID_ : String, quantity_ : String) {
  
  val itemID = new StringProperty(this, "itemID", itemID_)
  val purchaseOrderID = new StringProperty(this, "poID", purchaseOrderID_)  
  val quantity = new StringProperty(this, "quantity", quantity_)  
  val quantityDamg = new StringProperty(this, "dmgquantity", "unchecked")
}