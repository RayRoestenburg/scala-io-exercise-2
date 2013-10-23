package com.xebia.exercise2

import spray.testkit.Specs2RouteTest
import org.specs2.mutable.Specification
import spray.http.StatusCodes

import spray.httpx.SprayJsonSupport._
import akka.actor.{Props, ActorRef, ActorRefFactory}

class ReceptionistSpec extends Specification with Specs2RouteTest {

  val subject = new ReverseRoute {
    implicit def actorRefFactory: ActorRefFactory = system

    // TODO this creates the ReverseActor as a sibling.
    // The Receptionist communicates with the ReverseActor and this is not what we want
    // for a unit test
    def createChild(props:Props, name:String) : ActorRef = system.actorOf(props, name)
  }

  "The Receptionist" should {
    "Respond with a JSON response that contains a reversed string value" in {

      Post("/reverse", ReverseRequest("some text to reverse")) ~> subject.reverseRoute ~> check {
        status === StatusCodes.OK
        val response = entityAs[ReverseResponse]
        response.value must beEqualTo("esrever ot txet emos")
      }

    }
  }
}
