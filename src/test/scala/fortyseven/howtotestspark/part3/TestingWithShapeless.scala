package fortyseven.howtotestspark.part3

import com.holdenkarau.spark.testing.{Column, DataFrameSuiteBase, DataframeGenerator}
import fortyseven.howtotestspark.part1.businesslogic.{Combinator, Transformations}
import fortyseven.howtotestspark.part3.model.{Employee, Manager}
import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers
import fortyseven.howtotestspark.part1.model.EmployeeSchema
import fortyseven.howtotestspark.part3.arbitrary.ArbitraryColumns
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType
import org.scalacheck.Prop.passed
import arbitrary.Implicits._

class TestingWithShapeless extends FunSuite with DataFrameSuiteBase with Checkers {


  def generateCustomDF(schema: StructType, customGenerators: Vector[Column]): Arbitrary[DataFrame] =
    DataframeGenerator.arbitraryDataFrameWithCustomFields(
      spark.sqlContext, schema, 1
    )(customGenerators:_*)

  def employeeDfGenerator: Arbitrary[DataFrame] =
    generateCustomDF(EmployeeSchema.schema, ArbitraryColumns.employeeColGens)


  def applyTransformations(dataFrame: DataFrame): DataFrame = {
    val listOfTransformations: List[DataFrame => DataFrame] = List(
      Transformations.capitalizeNames,
      Transformations.assignManager,
      Transformations.to_ISO8601_Date
    )
    Combinator.combineTransformations(dataFrame, listOfTransformations)
  }

 //test("Employee"){

 //  val property = Prop.forAll{
 //    employee: Employee =>
 //      println(employee)
 //      Vector("Pierre Graz", "Luisa Garcia", "Mr. CEO").contains(employee.manager.value)
 //  }
 //  check(property)
 //}


  test("Spark"){

    val property: Prop = Prop.forAll(employeeDfGenerator.arbitrary){
      df =>
        val result = applyTransformations(df)
        df.show()
        result.show()
        passed
    }
    check(property)

  }




}
