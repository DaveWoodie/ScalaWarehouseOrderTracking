package connectors

import org.scalatest._

import com.qa.connectors.SQLConnector;

class ScalaTestSQL extends FlatSpec with Matchers {
  
  /**
   * This will always fail if the database is turned on.
   */
  "Is the database active?" should "Throw an  java.lang.NullPointerException  if not" in {
    val sql: SQLConnector = new SQLConnector()
    
    a [java.lang.NullPointerException] should be thrownBy {
      sql.doLoginQuery("Hey")
    }
  }
  
  "On a failed login the connector" should "contain null values in the return statement if incorrect details are entered" in {
    val sql: SQLConnector = new SQLConnector()
    
    val usr: String = "Al"
    val pwd: String = "ashduisf"
    
    val s: Array[String] = sql.doLoginQuery("SELECT username, password FROM nbgardens.user WHERE (username = '" + usr + "' AND password = '" + pwd + "')")
    assertResult(null)(s(0))
    assertResult(null)(s(1))
  }
  
  "On a successful login the returned Array" should "contain the same values as entered into the connection if the correct details are entered" in {
    val sql: SQLConnector = new SQLConnector()
    
    val usr: String = "Al"
    val pwd: String = "1"
    
    val s: Array[String] = sql.doLoginQuery("SELECT username, password FROM nbgardens.user WHERE (username = '" + usr + "' AND password = '" + pwd + "')")
    assertResult("Al")(s(0))
    assertResult("1")(s(1))
  }
  //Can't test the insert without first testing if the pull back of information is correct.
  
  //The other methods will be tested from the Controllers
  
}