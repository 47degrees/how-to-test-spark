package fortyseven.howtotestspark.model

import fortyseven.howtotestspark.businesslogic.{Combinator, Transformations}
import org.apache.spark.sql.DataFrame

case class Employee (
  firstName: String,
  sureName: String,
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