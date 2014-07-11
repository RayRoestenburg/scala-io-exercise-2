package com.xebia
package exercise2

import akka.actor.{Actor, Props}

object ReverseActor {
  def props = Props(new ReverseActor)
  def name = "reverser"

  case class Reverse(value:String)
  case class ReverseResult(value:String)
  case object PalindromeResult
}

class ReverseActor extends Actor {
  import ReverseActor._

  def receive = {
    case Reverse(value) =>
      val reversed = value.reverse

      if(reversed == value) sender ! PalindromeResult
      else sender ! ReverseResult(reversed)
  }
}
