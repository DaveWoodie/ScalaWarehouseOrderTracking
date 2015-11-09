package connectors

import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.Imports._

//.results() for no a-syncronous

object MongoConnector {
    
  def main(args: Array[String]): Unit = {
  
    val mongoClient: MongoClient = MongoClient("localhost", 27017)
   
    val mongoColl = mongoClient("warehouseOSdb")("addresses")
//    val user1 = MongoDBObject("House name/number" -> "5")
  
//    mongoColl += user1
    val x = mongoColl.find()
    
    for { s <- x } println (s)
  }

}
