package fortyseven.howtotestspark.part1.businesslogic

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.{Column, DataFrame}

object Transformations {

  lazy val capitalize: String => Column = columnName => initcap(lower(col(columnName)))

  lazy val assignManager: DataFrame => DataFrame = df =>
    df.withColumn("manager",
      when(col("department") === lit("Back End"), lit("Pierre Graz"))
        .otherwise(
          when(col("department") === lit("Data Science"), lit("Luisa Garcia"))
            .otherwise(
              lit("Mr. CEO")
            )
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
