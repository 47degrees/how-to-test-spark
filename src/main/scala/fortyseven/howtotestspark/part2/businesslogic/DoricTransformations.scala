package fortyseven.howtotestspark.part2.businesslogic

import doric._
import fortyseven.howtotestspark._
import org.apache.spark.sql.DataFrame

object DoricTransformations {

  lazy val assignManager: DataFrame => DataFrame = df => df.withColumn(MANAGER, departmentLogic)

  lazy val to_ISO8601_Date: DataFrame => DataFrame = df =>
    df.withColumn(ENROLLMENT_DATE, toISODate(ENROLLMENT_DATE))

  lazy val capitalizeNames: DataFrame => DataFrame = df =>
    df.withColumn(FIRST_NAME, capitalize(FIRST_NAME))
      .withColumn(SUR_NAME, capitalize(SUR_NAME))

  lazy val capitalize: String => StringColumn = col[String](_).lower.initCap

  lazy val departmentLogic: DoricColumn[String] = when[String]
    .caseW(col[String](DEPARTMENT) === lit[String](BACK_END),
      lit[String](PIERRE_GRAZ))
    .caseW(col[String](DEPARTMENT) === lit[String](FRONT_END),
      lit[String](PIERRE_GRAZ))
    .caseW(col[String](DEPARTMENT) === lit[String](DATA_SCIENCE),
      lit[String](LUISA_GARCIA))
    .caseW(col[String](DEPARTMENT) === lit[String](DATA_ENGINEERING),
      lit[String](LUISA_GARCIA))
    .otherwise(lit[String](MR_CEO))

  lazy val toISODate: String => LocalDateColumn = col[String](_).toDate(lit("MMddyyyy"))

}
