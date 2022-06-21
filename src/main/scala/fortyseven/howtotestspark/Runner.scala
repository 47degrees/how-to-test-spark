package fortyseven.howtotestspark

import fortyseven.howtotestspark.model.{Employee, EmployeeSchema}
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object Runner {
  def run(conf: SparkConf, inputCsvFile: String, options: Map[String, String], schema: StructType, outputCsvFile: String): Unit = {
    val ss: SparkSession   = SparkSession.builder().config(conf).getOrCreate()
    val entryDF: DataFrame = ss.read
      .options(options)
      .schema(schema)
      .csv(inputCsvFile)
    val exitDF: DataFrame  = Employee.applyTransformations(entryDF)
    exitDF.write.csv(outputCsvFile)
  }
}
