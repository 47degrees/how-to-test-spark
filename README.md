# how-to-test-spark
Different techniques to do unit testing in Spark without the need to rely on anything else than your IDE and SBT.

The focus of this project is to share ideas on how to test the small. 
Testing the small means testing the basic transformations that every spark application has in an isolated manner. 

## Run applications
The project also gives you the oportunity to run your applications locally if you wish to - drop local to run it in a cluster.

```bash
sbt "run $inputPath/inputFile.csv $outputPath/outputFile.csv local"
```
