package fortyseven.howtotestspark.part3.model

sealed case class Manager(value: String) extends Typed
object DataScienceManager extends Manager("Luisa Gracia")
object BackEndManager extends Manager("Pierre Graz")
object OtherDepartments extends Manager("Mr. CEO")


