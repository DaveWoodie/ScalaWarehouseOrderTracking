package connectors

import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet

class SQLConnector {
  
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306"
  val usname = "root"
  val passd = "netbuilder"
 
    // there's probably a better way to do this
  var connection:Connection = null

  def doLoginQuery(s: String): Array[String] = {
 
    val results: Array[String] = new Array(2)
    
    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, usname, passd)
 
      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s)
      while ( resultSet.next() ) {
        results.update(0, resultSet.getString("username"))
        results.update(1, resultSet.getString("password"))
      }
    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
    return results
  }
  
}
