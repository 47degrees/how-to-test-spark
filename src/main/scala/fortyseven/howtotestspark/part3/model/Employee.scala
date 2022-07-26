package fortyseven.howtotestspark.part3.model

import java.time.LocalDate

case class Employee(
  firstName: Name,
  surName: Name,
  secondSurName: Option[Name],
  department: Department,
  manager: Manager,
  enrollmentDate: LocalDate
)