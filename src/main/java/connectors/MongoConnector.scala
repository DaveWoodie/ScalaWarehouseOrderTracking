package connectors

import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.collection.immutable.Document

//.results() for no a-syncronous

class MongoConnector {
    
    val connection: String = "mongodb://localhost:27017"
  
    val mongoClient: MongoClient = MongoClient(connection)
    val database: MongoDatabase = mongoClient.getDatabase("warehouseOSdb")
    val collection: MongoCollection[Document] = database.getCollection("addresses")
    
    def print(): Unit = {
      println(collection.find().first())
  }
}

object MongoTest {
  
  def main(args: Array[String]): Unit = {
    val v: MongoConnector = new MongoConnector()
    v.print()
  }
}