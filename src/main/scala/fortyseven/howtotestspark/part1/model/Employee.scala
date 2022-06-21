package fortyseven.howtotestspark.part1.model

import fortyseven.howtotestspark.part1.businesslogic.{Combinator, Transformations}
import org.apache.spark.sql.DataFrame

case class Employee (
  firstName: String,
  surName: String,
  department: String,
  manager: String,
  enrollmentDate: String
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