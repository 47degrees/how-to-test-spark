package fortyseven.howtotestspark.part1.businesslogic

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.{Column, DataFrame}
import fortyseven.howtotestspark._

object Transformations {

  lazy val capitalize: String => Column = columnName => initcap(lower(col(columnName)))

  lazy val assignManager: DataFrame => DataFrame = df =>
    df.withColumn(MANAGER,
      when(col(DEPARTMENT) === lit(BACK_END), lit(PIERRE_GRAZ))
        .otherwise(
          when(col(DEPARTMENT) === lit(DATA_SCIENCE), lit(LUISA_GARCIA))
            .otherwise(
              lit(MR_CEO)
            )
        )
    )
  lazy val to_ISO8601_Date: DataFrame => DataFrame = df =>
    df.withColumn(ENROLLMENT_DATE,
      when(col(ENROLLMENT_DATE) === lit(""), lit("")
      ).otherwise(to_date(col(ENROLLMENT_DATE), "MMddyyyy").cast(StringType)))

   lazy val capitalizeNames: DataFrame => DataFrame = df =>
    df.withColumn(FIRST_NAME, capitalize(FIRST_NAME))
      .withColumn(SUR_NAME, capitalize(SUR_NAME))

}
