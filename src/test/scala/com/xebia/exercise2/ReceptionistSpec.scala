package com.xebia
package exercise2

import akka.actor.{Actor, Props, ActorRef, ActorRefFactory}

import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._

import org.specs2.mutable.Specification

class ReceptionistSpec extends Specification with Specs2RouteTest {

  trait TestCreationSupport extends CreationSupport {
    //TODO implement the TestCreationSupport that creates a FakeReverseActor
  }

  //TODO extend with TestCreationSupport and remove createChild implementation here
  val subject = new ReverseRoute {
    implicit def actorRefFactory: ActorRefFactory = system
    implicit def executionContext = system.dispatcher
  }

  "The Receptionist" should {
    "Respond with a JSON response that contains a reversed string value" in {

      Post("/reverse", ReverseRequest("some text to reverse")) ~> subject.reverseRoute ~> check {
        status === StatusCodes.OK
        val response = entityAs[ReverseResponse]
        response.value must beEqualTo("esrever ot txet emos")
        response.isPalindrome must beFalse
      }

      Post("/reverse", ReverseRequest("akka")) ~> subject.reverseRoute ~> check {
        status === StatusCodes.OK
        val response = entityAs[ReverseResponse]
        response.value must beEqualTo("akka")
        response.isPalindrome must beTrue
      }

    }
  }
}

//TODO create a FakeReverseActor that only responds to
// Reverse("akka") and Reverse("some text to reverse") and sends back the expected result for the test
class FakeReverseActor extends Actor {
  import ReverseActor._

}
