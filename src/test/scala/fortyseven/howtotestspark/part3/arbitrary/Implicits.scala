package fortyseven.howtotestspark.part3.arbitrary


import fortyseven.howtotestspark.part3.model._
import org.scalacheck.{Arbitrary, Gen}

import java.time.Month._
import java.time.format.DateTimeFormatter
import java.time.{LocalDate, Year}


object Implicits {



  def localDateGen(year: Int): Gen[LocalDate] = for {
    month <- Gen.oneOf(
      JANUARY,
      FEBRUARY,
      MARCH,
      APRIL,
      MAY,
      JUNE,
      JULY,
      AUGUST,
      SEPTEMBER,
      OCTOBER,
      NOVEMBER,
      DECEMBER
    )
    dayOfMonth <- Gen.chooseNum(1, month.length(Year.isLeap(year)))
  } yield LocalDate.of(year, month, dayOfMonth)

  implicit val arbitraryName: Arbitrary[Name] = Arbitrary[Name]{
    for {
      length <- Gen.chooseNum(5, 10)
      charList <- Gen.listOfN(length, Gen.alphaChar)
    } yield Name(charList.mkString)
  }

  implicit val arbitraryDepartment: Arbitrary[Department] = Arbitrary[Department] {
    Gen.oneOf(Vector(BackEnd, DataScience, Sales, Management, FrontEnd, DataEngineering))
  }

  implicit val arbitraryManager: Arbitrary[Manager] = Arbitrary[Manager]{
    Gen.const(OtherDepartments)
  }
  // TODO  : Ojo ! Va a sobre escribir el arbitrario para strings.
  implicit val arbitraryEnrollmentDate: Arbitrary[String] = Arbitrary[String]{
    val americanFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMddyyyy")
    for {
      year <- Gen.chooseNum(1960, 2030)
      date <- localDateGen(year)
      americanDate = date.format(americanFormat)
    } yield americanDate
  }

}
