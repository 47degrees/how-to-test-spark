package fortyseven.howtotestspark.generators

import com.holdenkarau.spark.testing.Column
import fortyseven.howtotestspark.generators.EmployeeGenerators.{anyNameGen, departmentGen, enrollmentDateGen, managerGen}

object EmployeeColGen {

  lazy val employeeColGens: Vector[Column] = Vector(
    firstNameColGen,
    sureNameColGen,
    departmentColGen,
    managerColGen,
    enrollmentDateColGen
  )

  lazy val firstNameColGen: Column = new Column("firstName", anyNameGen)

  lazy val sureNameColGen: Column = new Column("sureName", anyNameGen)

  lazy val departmentColGen: Column = new Column("department", departmentGen)

  lazy val managerColGen: Column = new Column("manager", managerGen)

  lazy val enrollmentDateColGen: Column = new Column("enrollmentDate", enrollmentDateGen)

}
