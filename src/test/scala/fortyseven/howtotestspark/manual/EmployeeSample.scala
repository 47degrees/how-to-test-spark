package fortyseven.howtotestspark.manual

import fortyseven.howtotestspark.model.Employee

object EmployeeSample {

  lazy val entryData: Seq[Employee] = Vector(
    Employee("Ryan", "Wilson", "Back End", "", "01312019"),
    Employee("Lexy", "Smith", "Data Science", "", "02282018"),
    Employee("Marlow", "Perez", "Sales", "", "05132020"),
    Employee("Angela", "Costa", "Management", "", "12062021")
  )

  lazy val expectedExit: Seq[Employee] = Vector(
    Employee("Ryan", "Wilson", "Back End", "Pierre Graz", "2019-01-31"),
    Employee("Lexy", "Smith", "Data Science", "Luisa Garcia", "2018-02-28"),
    Employee("Marlow", "Perez", "Sales", "Mr. CEO", "2020-05-13"),
    Employee("Angela", "Costa", "Management", "Mr. CEO", "2021-12-06")
  )


}
