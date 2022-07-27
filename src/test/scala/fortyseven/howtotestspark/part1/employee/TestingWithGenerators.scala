package fortyseven.howtotestspark.part1.employee

import com.holdenkarau.spark.testing._
import fortyseven.howtotestspark._
import fortyseven.howtotestspark.generators.EmployeeColGen
import fortyseven.howtotestspark.part1.businesslogic.Transformations
import fortyseven.howtotestspark.part1.model.{Employee, EmployeeSchema}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StructType
import org.scalacheck._
import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers

class TestingWithGenerators extends FunSuite with DataFrameSuiteBase with Checkers {

  def generateCustomDF(schema: StructType, customGenerators: Vector[Column]): Arbitrary[DataFrame] =
    DataframeGenerator.arbitraryDataFrameWithCustomFields(
      spark.sqlContext, schema, 1
    )(customGenerators:_*)
  def employeeDfGenerator: Gen[DataFrame] = generateCustomDF(EmployeeSchema.schema,
    EmployeeColGen.employeeColGens).arbitrary


  test("Testing capitalized names") {
    val capitalizedNames: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Transformations.capitalizeNames(entryDataFrame).where(
        col(FIRST_NAME).rlike("[A-Z]{1}[a-z]*") &&
          col(SUR_NAME).rlike("[A-Z]{1}[a-z]*")
      ).count() == entryDataFrame.count()
    }
    check(capitalizedNames)
  }

  test("Testing assigning manager") {

    val assigningManager: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>

      Transformations.assignManager(entryDataFrame).where(
        col(MANAGER).isin(PIERRE_GRAZ, LUISA_GARCIA)
        ).count() === entryDataFrame.where(
        col(DEPARTMENT).isin(BACK_END, DATA_SCIENCE)
      ).count()
    }

    check(assigningManager)
  }

  test("Testing to ISO dates") {

    val isoDates: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Transformations.to_ISO8601_Date(entryDataFrame).where(
        col(ENROLLMENT_DATE).rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}")
      ).count() == entryDataFrame.count()
    }
    check(isoDates)
  }

  test("Testing Employee Logic") {

    val employeeLogic: Prop = Prop.forAll(employeeDfGenerator) { entryDataFrame =>
      Employee.applyTransformations(entryDataFrame).where(
        col(MANAGER).isin(PIERRE_GRAZ, LUISA_GARCIA) &&
          col(ENROLLMENT_DATE).rlike("[0-9]{4}-[0-9]{2}-[0-9]{2}") &&
          col(FIRST_NAME).rlike("[A-Z]{1}[a-z]*") &&
          col(SUR_NAME).rlike("[A-Z]{1}[a-z]*")
      ).count() === entryDataFrame.where(
        col(DEPARTMENT).isin(BACK_END, DATA_SCIENCE)
      ).count()
    }

    check(employeeLogic)
  }

}
