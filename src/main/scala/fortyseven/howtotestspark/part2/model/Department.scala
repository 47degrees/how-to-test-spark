package fortyseven.howtotestspark.part2.model

sealed case class Department(value: String)
object BackEnd extends Department("Back End")
object DataScience extends Department("Data Science")
object Sales extends Department("Sales")
object Management extends Department("Management")
object FrontEnd extends Department("Front End")
object DataEngineering extends Department("Data Engineering")