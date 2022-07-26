package fortyseven.howtotestspark.part1.model

import fortyseven.howtotestspark.Combinator
import fortyseven.howtotestspark.part1.businesslogic.Transformations
import org.apache.spark.sql.DataFrame

case class Employee (
  first_name: String,
  sur_name: String,
  department: String,
  manager: String,
  enrollment_date: String
)

object Employee {

  def applyTransformations(dataFrame: DataFrame): DataFrame = {
    val listOfTransformations: List[DataFrame => DataFrame] = List(
      Transformations.capitalizeNames,
      Transformations.assignManager,
      Transformations.to_ISO8601_Date
    )
    Combinator.combineTransformations(dataFrame, listOfTransformations)
  }
}