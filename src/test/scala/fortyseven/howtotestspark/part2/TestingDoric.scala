package fortyseven.howtotestspark.part2

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.FunSuite
import doric._
import doric._
import doric.syntax._
import doric.types.SparkType
import fortyseven.howtotestspark.part2.model.{BackEnd, BackEndManager, DATA_SCIENCE, DataEngineering, DataEngineeringManager, DataScience, DataScienceManager, FrontEndManager, Manager, OtherDepartments, Sales}
import org.apache.spark.sql.types.{DataType, StringType}


class TestingDoric extends FunSuite with DataFrameSuiteBase {


  test("doric") {
    import spark.implicits._
    //List(1,2,3).toDF.select(col[Int]("value") > lit(1)).show()

    val df = List(DataScienceManager, DataEngineeringManager, OtherDepartments, BackEndManager, FrontEndManager)
      .toDF("manager")
    df.select(col[String]("manager").upper.as("manager")).show()

  }

}
