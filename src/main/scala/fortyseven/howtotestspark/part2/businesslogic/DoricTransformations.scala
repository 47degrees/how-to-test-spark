package fortyseven.howtotestspark.part2.businesslogic

import doric._
import fortyseven.howtotestspark.MR_CEO
import fortyseven.howtotestspark.part2.model._
import org.apache.spark.sql.DataFrame

object DoricTransformations {

  private lazy val capitalize: String => StringColumn = columnName => col[String](columnName).lower.initCap

  lazy val assignManager: DataFrame => DataFrame = df => df.withColumn("manager", departmentLogic)

  private lazy val departmentLogic: DoricColumn[String] = when[String]
    .caseW(col[String]("department") === lit[String](BackEnd.value),
      lit[String](BackEndManager.value))
    .caseW(col[String]("department") === lit[String](FrontEnd.value),
      lit[String](FrontEndManager.value))
    .caseW(col[String]("department") === lit[String](DataScience.value),
      lit[String](DataScienceManager.value))
    .caseW(col[String]("department") === lit[String](DataEngineering.value),
      lit[String](DataEngineeringManager.value))
    .otherwise(lit[String](MR_CEO))

  lazy val to_ISO8601_Date: DataFrame => DataFrame = df =>
    df.withColumn("enrollmentDate",
    col[String]("enrollmentDate").toDate(lit("MMddyyyy")))

  lazy val capitalizeNames: DataFrame => DataFrame = df =>
    df.withColumn("firstName", capitalize("firstName"))
      .withColumn("surName", capitalize("surName"))

}
