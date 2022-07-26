package fortyseven.howtotestspark.part2.businesslogic

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import fortyseven.howtotestspark.Combinator
import fortyseven.howtotestspark.part2.businesslogic.DoricTransformations._
import org.apache.spark.sql.DataFrame
import org.scalatest.FunSuite


class CombinatorTest extends FunSuite with DataFrameSuiteBase {

  test("testCombineTransformations") {
    val transformations: List[DataFrame => DataFrame] = List(capitalizeNames, to_ISO8601_Date, assignManager)

    val df = spark.read.options(Map("header"-> "true", "delimiter"->",", "inferSchema"->"false"))
      .csv("src/test/resources/input/entryEmployee.csv")

    Combinator.combineTransformations(df, transformations)
  }

}
