Exercise 2
==========

In this exercise we continue with the end result of exercise 1

In the previous exercise we ended up with a Receptionist that creates a child actor to do the actual work (String reversal). The existing test for the Receptionist is more of an integration test than a unit test, both the Receptionist and the ReverseActor are tested at once.

In this exercise we are going to decouple the Receptionist from the ReverseActor. We're going to use a trait


Exercise
~~~~~~~~

- implement ReverseActor (reverse a string and send back to sender).
  - define ReverseActor
  - define Reverse case class and Reversed case class
  - Test Reverse Actor
- Receptionist
  - create the child actor
  - remove the direct implementation and delegate to reverse request to ReverseActor. use ask pattern, mapTo future, complete with Future.
  - run the tests
- Run the application
  - Test with httpie (or curl or your fav http command line tool)


What is already prepared:

- A Main App which starts up the ActorSystem.
  - Starts a Receptionist Actor that listens to an endpoint.
  - Receptionist defines reception of external message

Next: A better way to create child actors.


Ideas (For next exercises):
  - add a counter (how many times was reverse called, fire and forget (event Stream or tell) and request the number of counts)

  - define one string that crashes your dear ReverseActor. create a supervisorStrategy that restarts X times, after which the
    'reverse service' is taken offline or switches behavior. (using Stop after x times, death watch and create a new child which
    does something else than reverse, like sending ReverseError back which turns into a status code)

  - do a reverse, then uppercase 'flow'.
