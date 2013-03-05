

package models

import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current
import play.api.Play

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.MongoURI
import com.mongodb.casbah.MongoDB
import play.Logger


case class Task(id: Long, label: String)

object Task {
	
    val mongoUri = MongoURI(Play.configuration.getString("mongodb.uri").get)
	
	val mongoConnection = mongoUri.connectDB match {
	  case Left(thrown) => throw thrown
	  case Right(db) => db("mycollection")
	}
	
	def findSomething(something:String) = {
	  val searchTerm = MongoDBObject("something" -> something)
	  mongoConnection.findOne(searchTerm) map { mongoObject =>
	    Logger.info("Found %s".format(mongoObject.getAs[String]("somethingElse")))
	  }
	}
	
	Logger.info("MongoDB = " + mongoConnection.toString)
	Logger.info("MongoDB = " + mongoConnection.db.toString)
	
	val db = mongoConnection
	

	def all():List[Task] = { db.find().toList.asInstanceOf[List[Task]] }
	  // def create(task: Task):List[Task] = { db.save(task.asInstanceOf[List[Task]]) }
	  // def findAll() = { db.find().toArray }
  	def delete(id: Long) = {
  		db.findOne(id)
		}
	  

	
	val task = {
	  get[Long]("id") ~ 
	  get[String]("label") map {
	    case id~label => Task(id, label)
	  }
	}
  

  
	

  
}