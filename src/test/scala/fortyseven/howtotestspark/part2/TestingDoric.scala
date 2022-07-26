package fortyseven.howtotestspark.part2

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import doric._
import fortyseven.howtotestspark.part2.model._
import org.scalatest.FunSuite

class TestingDoric extends FunSuite with DataFrameSuiteBase {


  test("doric") {
    import spark.implicits._
    //List(1,2,3).toDF.select(col[Int]("value") > lit(1)).show()

    val df = List(DataScienceManager, DataEngineeringManager, OtherDepartments, BackEndManager, FrontEndManager)
      .toDF("manager")
    df.select(col[String]("manager").upper.as("manager")).show()

  }

}
