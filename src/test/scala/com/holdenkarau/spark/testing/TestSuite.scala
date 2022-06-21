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

import org.scalatest.Suite

import scala.reflect.ClassTag

trait TestSuite extends TestSuiteLike { self: Suite =>

  override def assertEmpty[U](arr: Array[U])(implicit CT: ClassTag[U]): Unit =
    org.scalatest.Assertions.assert(arr.isEmpty)

  override def assert[U](expected: U, actual: U)(implicit CT: ClassTag[U]): Unit =
    org.scalatest.Assertions.assert(expected === actual)

  override def assertTrue(expected: Boolean): Unit =
    org.scalatest.Assertions.assert(expected === true)

  def assert[U](message: String, expected: U, actual: U)(implicit CT: ClassTag[U]): Unit =
    org.scalatest.Assertions.assert(expected === actual, message)
}

trait JavaTestSuite extends TestSuiteLike {
  override def assertEmpty[U](arr: Array[U])(implicit CT: ClassTag[U]): Unit =
    if (!arr.isEmpty)
      throw new AssertionError("Not Equal Sample: " + arr.mkString(", "))

  override def assert[U](expected: U, actual: U)(implicit CT: ClassTag[U]): Unit =
    org.junit.Assert.assertEquals(expected, actual)

  override def assertTrue(expected: Boolean): Unit =
    org.junit.Assert.assertTrue(expected)

  def assert[U](message: String, expected: U, actual: U)(implicit CT: ClassTag[U]): Unit =
    org.junit.Assert.assertEquals(message, expected, actual)
}

trait TestSuiteLike {
  def assertEmpty[U](arr: Array[U])(implicit CT: ClassTag[U]): Unit

  def assert[U](expected: U, actual: U)(implicit CT: ClassTag[U]): Unit

  def assertTrue(expected: Boolean): Unit

  def assert[U](message: String, expected: U, actual: U)(implicit CT: ClassTag[U]): Unit
}
