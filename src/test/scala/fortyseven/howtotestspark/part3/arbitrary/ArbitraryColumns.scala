package fortyseven.howtotestspark.part3.arbitrary

import com.holdenkarau.spark.testing.Column
import Implicits._
import fortyseven.howtotestspark.part3.model._
import org.scalacheck.Arbitrary

object ArbitraryColumns {
  lazy val employeeColGens: Vector[Column] = Vector(
    firstNameColGen,
    surNameColGen,
    departmentColGen,
    managerColGen,
    enrollmentDateColGen
  )


  def buildColumnFromArbitrary[O <: Typed](columnName: String)(implicit gen: Arbitrary[O]): Column =
    new Column(columnName, gen.arbitrary.map(_.value))

  lazy val firstNameColGen: Column = buildColumnFromArbitrary[Name]("firstName")
  lazy val surNameColGen: Column = buildColumnFromArbitrary[Name]("surName")
  lazy val departmentColGen: Column = buildColumnFromArbitrary[Department]("department")
  lazy val managerColGen: Column = buildColumnFromArbitrary[Manager]("manager")
  lazy val enrollmentDateColGen: Column = new Column("enrollmentDate", arbitraryEnrollmentDate.arbitrary)
}
