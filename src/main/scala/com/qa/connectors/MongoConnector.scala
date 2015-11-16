/**
 * @author dwoodward
 */

package com.qa.connectors

import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.Imports._

//.results() for no a-syncronous

class MongoConnector {
    
  /**
   * Method to access the mongo client, this is just a test method at the moment, but; providing the database is working does run well.
   */
  def main(args: Array[String]): Unit = {
  
    val mongoClient: MongoClient = MongoClient("localhost", 27017)
   
    val mongoColl = mongoClient("warehouseOSdb")("addresses")
//    val user1 = MongoDBObject("House name/number" -> "5")
  
//    mongoColl += user1
    val x = mongoColl.find()
    
    for { s <- x } println (s)
  }

}
