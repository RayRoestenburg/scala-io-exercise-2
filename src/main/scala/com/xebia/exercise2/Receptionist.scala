package com.xebia.exercise2

import spray.routing._

import spray.httpx.SprayJsonSupport._
import scala.concurrent.ExecutionContext
import akka.actor.{Props, ActorRef}
import akka.util.Timeout
import com.xebia.exercise2.ReverseActor._

class Receptionist extends HttpServiceActor with ReverseRoute {

  import ReverseActor._

  def createChild(props:Props, name:String) = context.actorOf(props, name)

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

        val futureResponse = reverseActor.ask(Reverse(request.value))
                                         .mapTo[ReverseResult]
                                         .map(result=> ReverseResponse(result.value))

        complete(futureResponse)
      }
    }
  }
}
