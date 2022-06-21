package fortyseven.howtotestspark.model

import org.apache.spark.sql.types._

object EmployeeSchema {
  val schema: StructType = StructType(
    List(
      StructField("firstName", StringType, nullable = true),
      StructField("surName", StringType, nullable = true),
      StructField("department", StringType, nullable = true),
      StructField("manager", StringType, nullable = true),
      StructField("enrollmentDate", StringType, nullable = true)
    )
  )
}
