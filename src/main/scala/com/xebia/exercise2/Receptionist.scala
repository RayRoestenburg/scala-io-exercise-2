package com.xebia.exercise2

import spray.routing._

import spray.httpx.SprayJsonSupport._
import scala.concurrent.ExecutionContext
import akka.actor.{Props, ActorRef}
import akka.util.Timeout

//TODO change class to trait
//TODO mixin the CreationSupport trait
trait Receptionist extends HttpServiceActor
                      with ReverseRoute
                      with CreationSupport {

  import ReverseActor._

  def receive = runRoute(reverseRoute)

}

trait ReverseRoute extends HttpService {

  def createChild(props:Props, name:String):ActorRef

  import ReverseActor._
  private val reverseActor = createChild(props, name)

  def reverseRoute:Route = path("reverse") {
    post {
      entity(as[ReverseRequest]) { request =>
        // We will fix this import and the timeout definition in a next exercise
        import ExecutionContext.Implicits.global
        import scala.concurrent.duration._
        implicit val timeout = Timeout(20 seconds)
        import akka.pattern.ask

        import ReverseActor._

        //TODO based on the result (ReverseResult or PalindromeResult)
        // complete with a ReverseResponse that indicates isPalindrome
        val futureResponse = reverseActor.ask(Reverse(request.value))
                                         .map {
                                           case PalindromeResult => ReverseResponse(request.value, true)
                                           case ReverseResult(value) => ReverseResponse(value, false)
                                         }

        complete(futureResponse)
      }
    }
  }
}

