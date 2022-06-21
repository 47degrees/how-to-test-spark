package fortyseven.howtotestspark.part1.generators

import com.holdenkarau.spark.testing.Column
import fortyseven.howtotestspark.part1.generators.EmployeeGenerators.{anyNameGen, departmentGen, enrollmentDateGen, managerGen}

object EmployeeColGen {

  lazy val employeeColGens: Vector[Column] = Vector(
    firstNameColGen,
    surNameColGen,
    departmentColGen,
    managerColGen,
    enrollmentDateColGen
  )

  lazy val firstNameColGen: Column = new Column("firstName", anyNameGen)

  lazy val surNameColGen: Column = new Column("surName", anyNameGen)

  lazy val departmentColGen: Column = new Column("department", departmentGen)

  lazy val managerColGen: Column = new Column("manager", managerGen)

  lazy val enrollmentDateColGen: Column = new Column("enrollmentDate", enrollmentDateGen)

}
