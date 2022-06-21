/*
 *
 * Copyright (c) 2015-2022  Holden Karau
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.holdenkarau.spark.testing

import org.apache.spark.sql.types.DataType
import org.apache.spark.ml.linalg.SQLDataTypes.{MatrixType, VectorType}
import org.apache.spark.ml.linalg.{DenseMatrix, Vectors}
import org.scalacheck.{Arbitrary, Gen}

/**
 * Extractor that matches the UDTs exposed by Spark ML.
 */
object MLUserDefinedType {
  def unapply(dataType: DataType): Option[Gen[Any]] =
    dataType match {
      case MatrixType =>
        val dense  = for {
          rows   <- Gen.choose(0, 20)
          cols   <- Gen.choose(0, 20)
          values <- Gen.containerOfN[Array, Double](rows * cols, Arbitrary.arbitrary[Double])
        } yield new DenseMatrix(rows, cols, values)
        val sparse = dense.map(_.toSparse)
        Some(Gen.oneOf(dense, sparse))
      case VectorType =>
        val dense  = Arbitrary.arbitrary[Array[Double]].map(Vectors.dense)
        val sparse = for {
          indices <- Gen.nonEmptyContainerOf[Set, Int](Gen.choose(0, Int.MaxValue - 1))
          values  <- Gen.listOfN(indices.size, Arbitrary.arbitrary[Double])
        } yield Vectors.sparse(indices.max + 1, indices.toSeq.zip(values))
        Some(Gen.oneOf(dense, sparse))
      case _          => None
    }
}
