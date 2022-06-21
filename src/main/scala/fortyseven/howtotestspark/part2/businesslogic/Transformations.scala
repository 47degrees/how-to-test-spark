package fortyseven.howtotestspark.part2.businesslogic

import fortyseven.howtotestspark.part2.model.{BackEnd, BackEndManager, DataScience, DataScienceManager, Department, Manager, OtherDepartments}
import org.apache.spark.sql.{Column, DataFrame}
import org.apache.spark.sql.functions.{col, initcap, lit, lower, to_date, typedlit, when}
import org.apache.spark.sql.types.StringType
import doric._
import doric.syntax._

object Transformations {

  lazy val capitalize: String => Column = columnName => initcap(lower(col(columnName)))

  lazy val assignManager: DataFrame => DataFrame = df => df.withColumn("manager", departmentLogic)

val departmentLogic: Column = when(col("department") === lit(BackEnd.value), lit(BackEndManager.value))
  .otherwise(
    when(col("department") === lit(DataScience.value), lit(DataScienceManager.value))
      .otherwise(
        lit(OtherDepartments.value)
      )
  )




  lazy val to_ISO8601_Date: DataFrame => DataFrame = df =>
    df.withColumn("enrollmentDate",
      when(col("enrollmentDate") === lit(""), lit("")
      ).otherwise(to_date(col("enrollmentDate"), "MMddyyyy").cast(StringType)))

   lazy val capitalizeNames: DataFrame => DataFrame = df =>
    df.withColumn("firstName", capitalize("firstName"))
      .withColumn("surName", capitalize("surName"))

}
