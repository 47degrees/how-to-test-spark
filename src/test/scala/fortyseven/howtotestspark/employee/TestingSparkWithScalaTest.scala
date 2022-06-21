package fortyseven.howtotestspark.employee

import fortyseven.howtotestspark.businesslogic.Transformations
import fortyseven.howtotestspark.manual.EmployeeSample
import org.apache.spark.sql.functions.{col, lit}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class TestingSparkWithScalaTest extends FunSuite with BeforeAndAfterAll {

  var sparkSession: SparkSession = _

  override protected def beforeAll(): Unit =
    sparkSession = SparkSession
      .builder()
      .appName("testing spark")
      .master("local[1]")
      .getOrCreate()

  override protected def afterAll(): Unit = sparkSession.stop()

  test("Testing Capitalized Names") {
    val dataframe: DataFrame        = sparkSession.createDataFrame(EmployeeSample.entryData)
    val capitalizedNames: DataFrame = Transformations.capitalizeNames(dataframe)

    assert(capitalizedNames.where(col("firstName").rlike("[A-Z]{1}[a-z]*")).count() === 4)
    assert(capitalizedNames.where(col("surName").rlike("[A-Z]{1}[a-z]*")).count() === 4)
  }

  test("Testing Assign Manager") {
    val dataframe: DataFrame      = sparkSession.createDataFrame(EmployeeSample.entryData)
    val updatedManager: DataFrame = Transformations.assignManager(dataframe)

    assert(updatedManager.where(col("manager") === lit("Mr. CEO")).count() === 2)
    assert(updatedManager.where(col("manager") === lit("Pierre Graz")).count() === 1)
    assert(updatedManager.where(col("manager") === lit("Luisa Garcia")).count() === 1)
  }

  test("Testing to ISO dates") {
    val dataframe: DataFrame = sparkSession.createDataFrame(EmployeeSample.entryData)
    val ISODates: DataFrame  = Transformations.to_ISO8601_Date(dataframe)

    assert(ISODates.where(col("enrollmentDate").rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}")).count() === 4)
  }

}
