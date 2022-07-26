package fortyseven.howtotestspark.part1.model

import fortyseven.howtotestspark.{DEPARTMENT, ENROLLMENT_DATE, FIRST_NAME, MANAGER, SUR_NAME}
import org.apache.spark.sql.types._

object EmployeeSchema {
  val schema: StructType = StructType(List(
    StructField(FIRST_NAME,StringType,nullable = true),
    StructField(SUR_NAME,StringType,nullable = true),
    StructField(DEPARTMENT,StringType,nullable = true),
    StructField(MANAGER,StringType,nullable = true),
    StructField(ENROLLMENT_DATE,StringType,nullable = true)
  ))
}
