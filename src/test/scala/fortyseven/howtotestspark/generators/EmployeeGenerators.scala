package fortyseven.howtotestspark.generators

import org.scalacheck.Gen

import java.time.Month._
import java.time.format.DateTimeFormatter
import java.time.{LocalDate, Year}
import fortyseven.howtotestspark._

object EmployeeGenerators {

  def anyNameGen: Gen[String] = for {
      length <- Gen.chooseNum(5,10)
      charList <- Gen.listOfN(length, Gen.alphaChar)
    } yield charList.mkString

  def departmentGen: Gen[String] = Gen.oneOf(Vector(
    BACK_END,
    FRONT_END,
    DATA_SCIENCE,
    DATA_ENGINEERING,
    SALES,
    MANAGEMENT
  ))

  def managerGen: Gen[String] = Gen.const(MR_CEO)

  def enrollmentDateGen: Gen[String] = {
    val americanFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMddyyyy")
    for {
      year <- Gen.chooseNum(1960, 2030)
      date <- localDateGen(year)
      americanDate = date.format(americanFormat)
    } yield americanDate
  }


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

}
