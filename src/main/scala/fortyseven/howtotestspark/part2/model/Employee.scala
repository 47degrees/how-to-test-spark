package fortyseven.howtotestspark.part2.model

import java.time.LocalDate

case class Employee(
  firstName: Name,
  surName: Name,
  department: Department,
  manager: Manager,
  enrollmentDate: LocalDate
)