package fortyseven.howtotestspark.part2.model


sealed case class Manager(value: String)
object DataScienceManager extends Manager("Luisa Gracia")
object BackEndManager extends Manager("Pierre Graz")
object OtherDepartments extends Manager("Mr. CEO")