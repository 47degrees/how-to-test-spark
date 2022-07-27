package fortyseven.howtotestspark.part2.businesslogic

import com.holdenkarau.spark.testing.{DataFrameSuiteBase, DataframeGenerator}
import fortyseven.howtotestspark.ENROLLMENT_DATE
import fortyseven.howtotestspark.generators.EmployeeColGen
import fortyseven.howtotestspark.part2.businesslogic.DoricTransformations.to_ISO8601_Date
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.scalacheck.{Arbitrary, Prop}
import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers

class DoricTransformationsTest extends FunSuite with DataFrameSuiteBase with Checkers{

  test("testTo_ISO8601_Date") {

    val schema: StructType = StructType(Seq(
      StructField(ENROLLMENT_DATE, StringType, nullable = false)
    ))
    val arbitrary: Arbitrary[DataFrame] = DataframeGenerator.arbitraryDataFrameWithCustomFields(
      spark.sqlContext,
      schema,
      1)(EmployeeColGen.employeeColGens:_*)

    val property: Prop = Prop.forAll(arbitrary.arbitrary) {
      dataFrame =>
        to_ISO8601_Date(dataFrame)
          .where(col(ENROLLMENT_DATE).rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
          .count() === dataFrame.count()
    }

    check(property)

  }

  test("testCapitalize") {

  }

  test("testToISODate") {

  }

  test("testDepartmentLogic") {

  }

  test("testAssignManager") {

  }

  test("testCapitalizeNames") {

  }

}
