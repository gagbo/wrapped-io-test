# Wrapped IO exercise

Small exercise in Scala around IO/inner state management.

## Problem statement

Let's say that we want to pass around an action that should be run _at most_ N
times (N is an integer). But we also want the action to not fail if it's run
more than N times, we just want the action to no-op.

Write a `WrappedIO` class that takes the `action` (as `Unit => IO[Unit]`), and the
maximum number of times it should run `N`. This class should have these
invariants:
- Calling `WrappedIO.run` actually does the `action` the first `N` times.
- Calling `WrappedIO.run` after the first `N` times is a noop

## Start

The code already compiles with a naive implementation : the WrappedIO.run() just
runs the action everytime. Change the implementation to make the test go green.

### Next thing

How would you change `WrappedIO` to take a type `T` and an action `Unit =>
IO[T]`, which memoizes the `N`th call and return that for all extra calls
instead ?

## Example application

Imagine you want to only print the first 3 identifiers that try to access a
specific resource for debugging.

Or that you'd want to Ack/Nack a message from a
MessageQueue only once, even if the `ack | nack` function is called multiple
times in the processing.
