Exercise 2
==========

In this exercise we continue with the end result of [exercise 1](http://github/RayRoestenburg/scala-io-exercise-1).

In the previous exercise we ended up with a **Receptionist** that creates a child **ReverseActor** to do the actual work (String reversal). The **ReceptionistSpec** is more of an integration test than a unit test right now. Both the **Receptionist** and the **ReverseActor** are tested at once.

In this exercise we are going to decouple the **Receptionist** from the **ReverseActor** so that we can use a mock to only test the **Receptionist**.
A **CreationSupport** trait has been added for creating and getting references to child actors.
The ReverseResponse has an added property, *isPalindrome*. The ReverseActor should now return a **ReverseResult** if it could reverse the string or a **PalindromeResult** case object if the received string value is a palindrome and reversal has no effect.

###Objective

The objective of this exercise is to create a unit test for the **Receptionist** that simply checks that it returns the correct response when a mock actor returns a **PalindromeResult** or a **ReverseResult**. This should be achieved by creating a **TestCreationSupport** trait in the **ReceptionistSpec** that creates a FakeReverseActor which only responds to the cases defined in the test.


###What is already prepared

The end result of exercise 1 is the starting point of this exercise. Added to this a CreationSupport trait is created and the ReverseResponse now includes an isPalindrome boolean.

###The Exercise

Create an **ActorContextCreationSupport** that extends the existing **CreationSupport** and uses an **ActorContext** to implement the methods.

Change **Receptionist** class to mix in the **ActorContextCreationSupport** trait.

Change the ReverseActor so it returns a **PalindromeResult** or a **ReverseResult**.

In **Receptionist**, based on the result of the ReverseActor (ReverseResult or PalindromeResult) complete with a ReverseResponse that indicates isPalindrome true or false.

Make the **ReceptionistSpec** work as a unit test with a **FakeReverseActor**. Also make the **ReverseActorSpec** work.

###Next Exercise

In the next exercise we're going to communicate with an asynchronous library from an Actor. You will learn how to initialize an Actor with *become*.
Go to [Exercise 3](https://github.com/RayRoestenburg/scala-io-exercise-3)
