package com.qa.connectors

import org.scalatest._

class ScalaTestMC extends FlatSpec with Matchers {
  
  "The connector" should "throw a com.mongodb.MongoTimeoutException if the database if offline" in {
    val mc: MongoConnector = new MongoConnector()
    val args = new Array[String](1)
    
    a [com.mongodb.MongoTimeoutException] should be thrownBy {
      mc.main(args)
    }
  }
}