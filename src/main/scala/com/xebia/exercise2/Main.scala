package com.xebia
package exercise2

import akka.actor.{Props, ActorSystem}
import akka.io.IO

import spray.can.Http
import spray.can.Http.Bind

object Main extends App {

  implicit val system = ActorSystem("exercise-2")

  val receptionist = system.actorOf(Props(new Receptionist), "receptionist")

  IO(Http) ! Bind(listener= receptionist, interface = "0.0.0.0", port=8000)
}

