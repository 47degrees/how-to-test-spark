package fortyseven.howtotestspark.employee

import com.holdenkarau.spark.testing._
import fortyseven.howtotestspark.businesslogic.Transformations
import fortyseven.howtotestspark.generators.EmployeeColGen
import fortyseven.howtotestspark.model.{Employee, EmployeeSchema}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StructType
import org.scalacheck._
import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers

class TestingWithGenerators extends FunSuite with DataFrameSuiteBase with Checkers {

  def generateCustomDF(schema: StructType, customGenerators: Vector[Column]): Arbitrary[DataFrame] =
    DataframeGenerator.arbitraryDataFrameWithCustomFields(
      spark.sqlContext,
      schema,
      1
    )(customGenerators: _*)
  def employeeDfGenerator: Gen[DataFrame]                                                          = generateCustomDF(EmployeeSchema.schema, EmployeeColGen.employeeColGens).arbitrary

  test("Testing capitalized names") {
    val capitalizedNames: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Transformations
        .capitalizeNames(entryDataFrame).where(
          col("firstName").rlike("[A-Z]{1}[a-z]*") &&
            col("surName").rlike("[A-Z]{1}[a-z]*")
        ).count() == entryDataFrame.count()
    }
    check(capitalizedNames)
  }

  test("Testing assigning manager") {

    val assigningManager: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Transformations
        .assignManager(entryDataFrame).where(
          col("manager").isin("Pierre Graz", "Luisa Garcia")
        ).count() === entryDataFrame
        .where(
          col("department").isin("Back End", "Data Science")
        ).count()
    }

    check(assigningManager)
  }

  test("Testing to ISO dates") {

    val isoDates: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Transformations
        .to_ISO8601_Date(entryDataFrame).where(
          col("enrollmentDate").rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}")
        ).count() == entryDataFrame.count()
    }
    check(isoDates)
  }

  test("Testing Employee Logic") {

    val employeeLogic: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Employee
        .applyTransformations(entryDataFrame).where(
          col("manager").isin("Pierre Graz", "Luisa Garcia") &&
            col("enrollmentDate").rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}") &&
            col("firstName").rlike("[A-Z]{1}[a-z]*") &&
            col("surName").rlike("[A-Z]{1}[a-z]*")
        ).count() === entryDataFrame
        .where(
          col("department").isin("Back End", "Data Science")
        ).count()
    }

    check(employeeLogic)
  }

}
