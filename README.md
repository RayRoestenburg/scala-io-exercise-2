Exercise 2
==========

In this exercise we continue with the end result of [exercise 1](http://github/RayRoestenburg/scala-io-exercise-1).

In the previous exercise we ended up with a **Receptionist** that creates a child **ReverseActor** to do the actual work (String reversal). The **ReceptionistSpec** is more of an integration test than a unit test right now. Both the **Receptionist** and the **ReverseActor** are tested at once.

In this exercise we are going to decouple the **Receptionist** from the **ReverseActor** so that we can use a mock to only test the **Receptionist**.

###Objective

###What is already prepared

The end result of exercise 1 is the starting point of this exercise. The project contains a solution for exercise 1. 

###The Exercise



Ideas (For next exercises):
  - add a counter (how many times was reverse called, fire and forget (event Stream or tell) and request the number of counts)

  - define one string that crashes your dear ReverseActor. create a supervisorStrategy that restarts X times, after which the
    'reverse service' is taken offline or switches behavior. (using Stop after x times, death watch and create a new child which
    does something else than reverse, like sending ReverseError back which turns into a status code)

  - do a reverse, then uppercase 'flow'.
