package fortyseven.howtotestspark.part2.businesslogic

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.DataFrame
import org.scalatest.FunSuite
import fortyseven.howtotestspark.part2.businesslogic.DoricTransformations._
import fortyseven.howtotestspark.part2.model.{BACK_END, DATA_ENGINEERING, DATA_SCIENCE, FRONT_END, MANAGEMENT, SALES}

class DoricTransformationsTest extends FunSuite with DataFrameSuiteBase {

  import spark.implicits._
  test("testTo_ISO8601_Date") {
    val inputDF: DataFrame = {
      Seq("05012021", "12012022", "12312020", "").toDF("enrollmentDate")
    }
    val result: DataFrame = to_ISO8601_Date(inputDF)

    result.show()

    val inputDF2: DataFrame = {
      Seq(15012021, 12012022, 12312020).toDF("enrollmentDate")
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
      ).toDF("department", "manager")
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
      ).toDF("firstName", "surName")
    }

    val result = capitalizeNames(df)
    result.show()
  }

}
