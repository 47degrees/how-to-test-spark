package fortyseven.howtotestspark.part3.model

import fortyseven.howtotestspark._

sealed case class Manager (value: String) extends Typed
object DataScienceManager extends Manager(LUISA_GARCIA)
object DataEngineeringManager extends Manager(LUISA_GARCIA)
object BackEndManager extends Manager(PIERRE_GRAZ)
object FrontEndManager extends Manager(PIERRE_GRAZ)
object OtherDepartments extends Manager(MR_CEO)

object Manager {

  // This information should come from some external source that is alive like a RDB (managers change, eventually)
  private val assignedManager: Map[Department, Manager] = Map(
    BackEnd -> BackEndManager,
    FrontEnd -> FrontEndManager,
    DataScience -> DataScienceManager,
    DataEngineering -> DataEngineeringManager,
    Sales -> OtherDepartments,
    Management -> OtherDepartments
  )

  // Default value so we do not generate incorrect data
  def apply(department: Department): Manager = assignedManager.getOrElse(department, OtherDepartments)

}