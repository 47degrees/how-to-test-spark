package fortyseven.howtotestspark.part2.businesslogic

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import fortyseven.howtotestspark._
import fortyseven.howtotestspark.part2.businesslogic.DoricTransformations._
import org.apache.spark.sql.DataFrame
import org.scalatest.FunSuite

class DoricTransformationsTest extends FunSuite with DataFrameSuiteBase {

  import spark.implicits._
  test("testTo_ISO8601_Date") {
    val inputDF: DataFrame = {
      Seq("05012021", "12012022", "12312020", "").toDF(ENROLLMENT_DATE)
    }
    val result: DataFrame = to_ISO8601_Date(inputDF)

    val inputDF2: DataFrame = {
      Seq(15012021, 12012022, 12312020).toDF(ENROLLMENT_DATE)
    }
    inputDF2.printSchema(3)
    assertThrows[doric.sem.DoricMultiError](to_ISO8601_Date(inputDF2))



  }

  test("testAssignManager") {
    val inputDF: DataFrame = {
      Seq(
        (BACK_END, ""),
        (FRONT_END, "John Smith"),
        (DATA_SCIENCE, "Anyone"),
        (DATA_ENGINEERING, "Mary SmithSon"),
        (SALES, ""),
        (MANAGEMENT, "Pierre Graz")
      ).toDF(DEPARTMENT, MANAGER)
    }
    val result: DataFrame = assignManager(inputDF)

    result.show()



  }

  test("testCapitalizeNames") {

    val df = {
      Seq(
        ("hellen", "johnson"),
        ("PATRICK", "SHEEN"),
        ("Lucy", "Ray"),
        ("rob", "Man")
      ).toDF(FIRST_NAME, SUR_NAME)
    }

    val result = capitalizeNames(df)
    result.show()
  }

}
