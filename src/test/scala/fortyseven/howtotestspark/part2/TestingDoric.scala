package fortyseven.howtotestspark.part2

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import doric._

class TestingDoric extends FunSuite with DataFrameSuiteBase {

  test("doric") {
    import spark.implicits._
    List(1,2,3).toDF.select(col[Int]("value") > lit(1)).show()
  }

}
