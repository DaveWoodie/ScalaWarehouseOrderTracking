/**
 * @author dwoodward
 */

package com.qa.logic

import com.qa.connectors.SQLConnector
import java.sql.SQLException

/**
 * Class to apply the logic of the login to the SQL
 */
class loginLogic {
  
  /**
   * Main logic method to check
   */
  def loginCheck(usr: String, pwd: String): Boolean = {
    
    val connection: SQLConnector = new SQLConnector
    val s: String = "SELECT username, password FROM nbgardens.user WHERE (username = '" + usr + "' AND password = '" + pwd + "')"
    var res: Array[String] = null
    try {
      res = connection.doLoginQuery(s)
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
    if (usr.equals(res(0)) && pwd.equals(res(1))) {
      true
    }
    else {
      false
    }
  }
}