package fortyseven.howtotestspark.part3.model

import fortyseven.howtotestspark._

sealed case class Department(value: String) extends Typed
object BackEnd extends Department(BACK_END)
object DataScience extends Department(DATA_SCIENCE)
object Sales extends Department(SALES)
object Management extends Department(MANAGEMENT)
object FrontEnd extends Department(FRONT_END)
object DataEngineering extends Department(DATA_ENGINEERING)