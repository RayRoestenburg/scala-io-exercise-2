package com.xebia
package exercise2

import scala.concurrent.ExecutionContext

import akka.actor.{Props, ActorRef}
import akka.util.Timeout

import spray.routing._
import spray.httpx.SprayJsonSupport._


//TODO change class to trait
//TODO mixin the CreationSupport trait
class Receptionist extends HttpServiceActor
                      with ReverseRoute {

  implicit def executionContext:ExecutionContext = context.dispatcher

  import ReverseActor._

  def receive = runRoute(reverseRoute)
}

trait ReverseRoute extends HttpService {

  implicit def executionContext:ExecutionContext

  def createChild(props:Props, name:String):ActorRef

  import ReverseActor._
  private val reverseActor = createChild(props, name)

  def reverseRoute:Route = path("reverse") {
    post {
      entity(as[ReverseRequest]) { request =>
        import scala.concurrent.duration._
        implicit val timeout = Timeout(20 seconds)
        import akka.pattern.ask

        import ReverseActor._

        //TODO based on the result (ReverseResult or PalindromeResult)
        // complete with a ReverseResponse that indicates isPalindrome
        val futureResponse = reverseActor.ask(Reverse(request.value))
                                         .mapTo[ReverseResult].map(r=>ReverseResponse(r.value))
        complete(futureResponse)
      }
    }
  }
}

