package com.xebia
package exercise2

import scala.concurrent.ExecutionContext

import akka.actor.{Props, ActorRef}
import akka.util.Timeout

import spray.routing._
import spray.httpx.SprayJsonSupport._

trait Receptionist extends HttpServiceActor
                      with ReverseRoute
                      with CreationSupport {
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

