/**
 * @author dwoodward
 */

package logic

import connectors.SQLConnector
import java.sql.SQLException

class loginLogic {
  
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