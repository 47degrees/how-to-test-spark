package fortyseven.howtotestspark.part2.model

trait Name {
  val value: String
}

case class FirstName(value: String) extends Name
case class SurName(value: String) extends Name