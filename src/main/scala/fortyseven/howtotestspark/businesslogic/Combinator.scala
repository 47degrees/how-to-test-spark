package fortyseven.howtotestspark.businesslogic

import org.apache.spark.sql.DataFrame

import scala.annotation.tailrec

object Combinator {

  /**
   * This method applies the dataframe transformations following the order they have in the list.
   * @param dataFrame
   *   a dataframe that will be subject of the transformations.
   * @param transformation
   *   a list containing the transformations to be applied on the dataframe.
   * @return
   *   a dataframe that is the result of applying the transformations to the input dataframe.
   */
  @tailrec
  def combineTransformations(dataFrame: DataFrame, transformation: List[DataFrame => DataFrame]): DataFrame =
    transformation match {
      case Nil          => dataFrame
      case head :: Nil  => head(dataFrame)
      case head :: tail => combineTransformations(head(dataFrame), tail)
    }

}
