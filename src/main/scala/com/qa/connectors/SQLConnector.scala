/**
 * @author dwoodward
 */

package com.qa.connectors

import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet
import scalafx.collections.ObservableBuffer
import com.qa.entities.{purchaseOrder, purchaseOrderLine, customerOrder, customerOrderLine}

/**
 * This class could be written much better to have just and UPDATE and a SELECT method and return based on one or the other.
 * However time constraints and trying to get functionality over good coding design limited my more than I liked...
 */

/**
 * NOTE: METHODS MERGED
 * 
 * doInsert
 * doPurchaseOrderLineUpdate
 * doPurchaseOrderStatusUpdate
 * Are IDENTICAL methods.  They are different as this is a merge of three individual connection classes.
 */

class SQLConnector {

  /**
   * The only place in my code where there are class variables ;___;
   */
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306"
  val usname = "root"
  val passd = "netbuilder"

  // there's probably a better way to do this
  var connection: Connection = null
  /**
   * Method to do the Login Query
   * @param s : Takes in the SQL statement.
   */
  def doLoginQuery(s: String): Array[String] = {

    val results: Array[String] = new Array(2)

    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)
      while (resultSet.next()) {
        results.update(0, resultSet.getString("username"))
        results.update(1, resultSet.getString("password"))
      }
    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }
  
  /**
   * Method to insert anything into any table.
   * Unsure of how to actually test this method without also testing a push to the database.
   * @param s : Takes in the SQL statement.
   */
  def doUpdate(s: String): Unit = {
    println("Pushing changes to the database.")
    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.execute(s)

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
  }

  /**
   * Method to get a purchase Order
   * @param s : Takes in the SQL statement.
   * @param fields : Takes in the fields of data to extract from the database.
   * @param results: Takes in the ObservableBuffer to return the results of the query in.
   */
  def doPurchaseOrderQuery(s: String, fields: Array[String], results: ObservableBuffer[purchaseOrder]): ObservableBuffer[purchaseOrder] = {

    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)

      while (resultSet.next()) {

        var f: Array[String] = new Array[String](fields.length)
        val i: Int = 0
        
        def loop(a: Array[String], in: Int): Unit = {
          if(in == fields.length) {
          }
          else {
            a(in) = resultSet.getString(fields(in))
            loop(a, in.+(1))
          }
        }
        
        loop(f, i)
        val po: purchaseOrder = new purchaseOrder(f(0), f(1), f(2), f(3), f(4), f(5))
        results.add(po)
      }

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }
  
  /**
   * Method to query the database for the customer orders.
   * @param s : Takes in the SQL statement.
   * @param fields : Takes in the fields of data to extract from the database.
   * @param results: Takes in the ObservableBuffer to return the results of the query in.
   */
  def doCustomerOrderQuery(s: String, fields: Array[String], results: ObservableBuffer[customerOrder]): ObservableBuffer[customerOrder] = {

    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)

      while (resultSet.next()) {

        var f: Array[String] = new Array[String](fields.length)
        val i: Int = 0
        
        def loop(a: Array[String], in: Int): Unit = {
          if(in == fields.length) {
          }
          else {
            a(in) = resultSet.getString(fields(in))
            loop(a, in.+(1))
          }
        }
        
        loop(f, i)
        val po: customerOrder = new customerOrder(f(0), f(1))
        //Done this way as the entities have defaults for whenever the functionality for adding in 
        po.dateShipped.value_=(f(2))
        po.idAddress.value_=(f(3))
        po.idCustomerOrderStatus.value_=(f(4))
        po.idEmployee.value_=(f(5))
        results.add(po)
      }

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }
  /**
   * Method to return the purchase order lines of a single order.
   * @param s : Takes in the SQL statement.
   * @param fields : Takes in the fields of data to extract from the database.
   * @param results: Takes in the ObservableBuffer to return the results of the query in.
   */
  def doPurchaseOrderLineQuery(s: String, fields: Array[String], results: ObservableBuffer[purchaseOrderLine]): ObservableBuffer[purchaseOrderLine] = {

    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)

      //Same as the other while loop.
      while (resultSet.next()) {

        var f: Array[String] = new Array[String](fields.length)
        val i: Int = 0
        
        def loop(a: Array[String], in: Int): Unit = {
          if(in == fields.length) {
          }
          else {
            a(in) = resultSet.getString(fields(in))
            loop(a, in.+(1))
          }
        }
        
        loop(f, i)
        
        val po: purchaseOrderLine = new purchaseOrderLine(f(0), f(1), f(2))
        po.quantityDamg.value_=(f(3))
        results.add(po)
      }

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }
  
  /**
   * Method to query the database for a set of customer order lines.
   * @param s : Takes in the SQL statement.
   * @param fields : Takes in the fields of data to extract from the database.
   * @param results: Takes in the ObservableBuffer to return the results of the query in.
   */
  def doCustomerOrderLineQuery(s: String, fields: Array[String], results: ObservableBuffer[customerOrderLine]): ObservableBuffer[customerOrderLine] = {

    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)

      //Same as the other while loop.
      while (resultSet.next()) {

        var f: Array[String] = new Array[String](fields.length)
        val i: Int = 0
        
        def loop(a: Array[String], in: Int): Unit = {
          if(in == fields.length) {
          }
          else {
            a(in) = resultSet.getString(fields(in))
            loop(a, in.+(1))
          }
        }
        
        loop(f, i)
        
        val co: customerOrderLine = new customerOrderLine(f(0), f(1))
        results.add(co)
      }

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }

}
