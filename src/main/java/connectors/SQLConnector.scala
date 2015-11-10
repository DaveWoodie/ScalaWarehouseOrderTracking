package connectors

import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet
import scalafx.collections.ObservableBuffer
import Entities.{purchaseOrder, purchaseOrderLine}

class SQLConnector {

  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306"
  val usname = "root"
  val passd = "netbuilder"

  // there's probably a better way to do this
  var connection: Connection = null
  //*********************************************************
  //PASS IN THE ARRAY OF THE RIGHT LENGTH
  //*********************************************************
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

  def doPurchaseOrderQuery(s: String, fields: Array[String], results: ObservableBuffer[purchaseOrder]): ObservableBuffer[purchaseOrder] = {

    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)

      while (resultSet.next()) {

        var f: Array[String] = new Array[String](fields.length)
        var k: Int = 0
        for (i <- f) {
          f(k) = resultSet.getString(fields(k))
          k += 1
        }
        val po: purchaseOrder = new purchaseOrder(f(0), f(1), f(2), f(3), f(4), f(5))
        results.add(po)
      }

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }
  
  def doPurchaseOrderLineQuery(s: String, fields: Array[String], results: ObservableBuffer[purchaseOrderLine]): ObservableBuffer[purchaseOrderLine] = {

    try {

      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)

      while (resultSet.next()) {

        var f: Array[String] = new Array[String](fields.length)
        var k: Int = 0
        for (i <- f) {
          f(k) = resultSet.getString(fields(k))
          k += 1
        }
        val po: purchaseOrderLine = new purchaseOrderLine(f(0), f(1), f(2))
        results.add(po)
      }

    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    results
  }
}
