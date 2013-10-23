package com.xebia.exercise2

import akka.actor.{Actor, Props}

object ReverseActor {
  def props = Props[ReverseActor]
  def name = "reverser"

  case class Reverse(value:String)
  case class ReverseResult(value:String)
}

class ReverseActor extends Actor {
  import ReverseActor._

  def receive = {
    case Reverse(value) => sender ! ReverseResult(value.reverse)
  }

}
