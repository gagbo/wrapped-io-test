# Wrapped IO exercise

Small exercise in Scala around IO/inner state management.

## Problem statement

Let's say that we want to pass around an action that should be run _at most_ N
times (N is an integer). But we also want the action to not fail if it's run
more than N times, we just want the action to no-op.

Write a `WrappedIO` class that takes the action (as `Unit => IO[T]`), and the maximum
number of times it should run `N`

## Start

The code already compiles with a naive implementation : the WrappedIO.run() just
runs the action everytime. Change the implementation to make the test go green.

## Example application

Imagine you want to only print the first 3 identifiers that try to access a
specific resource for debugging.

Or that you'd want to Ack/Nack a message from a
MessageQueue only once, even if the `ack | nack` function is called multiple
times in the processing.
