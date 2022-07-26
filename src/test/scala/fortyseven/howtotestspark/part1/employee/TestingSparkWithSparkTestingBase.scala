package fortyseven.howtotestspark.part1.employee

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import fortyseven.howtotestspark._
import fortyseven.howtotestspark.manual.EmployeeSample
import fortyseven.howtotestspark.part1.businesslogic.Transformations
import fortyseven.howtotestspark.part1.model.Employee
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, lit}
import org.scalatest.FunSuite

class TestingSparkWithSparkTestingBase extends FunSuite with DataFrameSuiteBase  {

  test("Testing Capitalized Names") {
    val dataframe: DataFrame = spark.createDataFrame(EmployeeSample.entryData)
    val capitalizedNames: DataFrame = Transformations.capitalizeNames(dataframe)

    assert(capitalizedNames.where(col(FIRST_NAME).rlike("[A-Z]{1}[a-z]*")).count() === 4)
    assert(capitalizedNames.where(col(SUR_NAME).rlike("[A-Z]{1}[a-z]*")).count() === 4)
  }

  test("Testing Assign Manager") {
    val dataframe: DataFrame = spark.createDataFrame(EmployeeSample.entryData)
    val updatedManager: DataFrame = Transformations.assignManager(dataframe)

    assert(updatedManager.where(col(MANAGER) === lit(MR_CEO)).count() === 2)
    assert(updatedManager.where(col(MANAGER) === lit(PIERRE_GRAZ)).count() === 1)
    assert(updatedManager.where(col(MANAGER) === lit(LUISA_GARCIA)).count() === 1)
  }

  test("Testing to ISO dates") {
    val dataframe: DataFrame = spark.createDataFrame(EmployeeSample.entryData)
    val ISODates: DataFrame = Transformations.to_ISO8601_Date(dataframe)

    assert(ISODates.where(col(ENROLLMENT_DATE).rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}")).count() === 4)
  }
  test("Testing Employee Logic") {
    val entryDataset: DataFrame = spark.createDataFrame(EmployeeSample.entryData)
    val transformedFDF: DataFrame = Employee.applyTransformations(entryDataset)
    val exitDataset: DataFrame = spark.createDataFrame(EmployeeSample.expectedExit)
    // We use assertDataFrameDataEquals and not assertDataFrameEquals
    // because we cannot enforce nullability during spark transformations,
    // causing the schemas of expected and resulting dataframes not to be equal and crushing the test
    assertDataFrameDataEquals(exitDataset, transformedFDF)
 }

}
