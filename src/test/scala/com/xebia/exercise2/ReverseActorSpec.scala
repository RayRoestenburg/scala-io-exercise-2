package com.xebia
package exercise2

import org.specs2.mutable.Specification

import spray.testkit.Specs2RouteTest

import TestSupport._

class ReverseActorSpec extends Specification
                          with Specs2RouteTest {

  "The ReverseActor" should {
    "Reverse a string that it receives if it is not a Palindrome" in new AkkaTestkitContext() {
      // You can use the TestKit here, it is provided by the AkkaTestkitContext
      // The ImplicitSender is also available so you can expect responses to be sent to the testActor
      // which can be asserted with the TestKit expect... methods.
      //
      // TODO make this test work

      import ReverseActor._

      val reverseActor = system.actorOf(props, name)

      reverseActor ! Reverse("reverse this!")

      expectMsg(ReverseResult("!siht esrever"))

      expectNoMsg()
    }

    "Not reverse a string but return a PalindromeResult if the reversal has no effect" in new AkkaTestkitContext() {
      // You can use the TestKit here, it is provided by the AkkaTestkitContext
      // The ImplicitSender is also available so you can expect responses to be sent to the testActor
      // which can be asserted with the TestKit expect... methods.
      //
      // TODO make this test work

      import ReverseActor._

      val reverseActor = system.actorOf(props, name)

      reverseActor ! Reverse("akka")

      expectMsg(PalindromeResult)

      expectNoMsg()

    }
  }
}
