package fortyseven.howtotestspark.part1.generators

import com.holdenkarau.spark.testing.Column
import fortyseven.howtotestspark.part1.generators.EmployeeGenerators._
import fortyseven.howtotestspark._

object EmployeeColGen {

  lazy val employeeColGens: Vector[Column] = Vector(
    firstNameColGen,
    surNameColGen,
    departmentColGen,
    managerColGen,
    enrollmentDateColGen
  )

  lazy val firstNameColGen: Column = new Column(FIRST_NAME, anyNameGen)

  lazy val surNameColGen: Column = new Column(SUR_NAME, anyNameGen)

  lazy val departmentColGen: Column = new Column(DEPARTMENT, departmentGen)

  lazy val managerColGen: Column = new Column(MANAGER, managerGen)

  lazy val enrollmentDateColGen: Column = new Column(ENROLLMENT_DATE, enrollmentDateGen)

}
