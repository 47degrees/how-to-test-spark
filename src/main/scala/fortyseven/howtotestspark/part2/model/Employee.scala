package fortyseven.howtotestspark.part2.model

import java.time.LocalDate

case class Employee(
  firstName: FirstName,
  surName: SurName,
  secondSurName: Option[SurName],
  department: Department,
  manager: Manager,
  enrollmentDate: LocalDate
)