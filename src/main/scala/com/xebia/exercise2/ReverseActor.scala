package com.xebia
package exercise2

import akka.actor.{Actor, Props}

object ReverseActor {
  def props = Props[ReverseActor]
  def name = "reverser"

  case class Reverse(value:String)
  case class ReverseResult(value:String)
  //TODO add a PalindromeResult
}

class ReverseActor extends Actor {
  import ReverseActor._

  def receive = {
    case Reverse(value) =>
      //TODO verify if the value is a palindrome, return a PalindromeResult if this is the case,
      sender ! ReverseResult(value.reverse)
  }
}
