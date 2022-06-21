package fortyseven.howtotestspark.part1

import fortyseven.howtotestspark.part1.model.EmployeeSchema
import org.apache.spark.SparkConf

/**
  * Use this to test the app locally, from sbt:
  * sbt "run $inputPath/inputFile.csv $outputPath/outputFile.csv local"
  *  (+ select EmployeeApp when prompted)
  */
object EmployeeApp extends App {
  val (inputCsvFile, outputCsvFile, runMode) = (args(0), args(1), args(2))
  val conf: SparkConf = if(runMode == "local") {
    new SparkConf()
      .setMaster("local[1]")
      .setAppName("my awesome app")
  } else {
    // spark-submit command should supply all necessary config elements
    new SparkConf()
  }

  Runner.run(
    conf,
    inputCsvFile,
    Map("header" -> "true", "separator" -> ",", "inferSchema" -> "false"),
    EmployeeSchema.schema,
    outputCsvFile
  )
}
