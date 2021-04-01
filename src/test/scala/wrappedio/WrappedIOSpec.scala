import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import cats.effect.IO
import cats.effect.Ref
import cats.effect.unsafe.implicits.global

import wrappedio._

class WrappedIOSpec extends AnyFlatSpec with Matchers {
  // TestAction bundles the action that should be WrappedIO(),
  // and the actual count of calls on the action
  case class TestAction(callsCounter: Ref[IO, Int], action: Unit => IO[Unit])

  // This function generates a TestAction with a callsCounter
  //
  // The generated action will raise an error if it's called more than
  // maxCalls times.
  def genTestAction(maxCalls: Int): IO[TestAction] = {
    Ref
      .of[IO, Int](0)
      .map(callsCounter =>
        TestAction(
          callsCounter,
          _ =>
            for {
              counter <- callsCounter.updateAndGet(_ + 1)
              res <-
                if (counter > maxCalls)
                  IO.raiseError(
                    new Exception("Action was called too many times!")
                  )
                else IO.unit
            } yield res
        )
      )
  }

  "Action" should "be called only twice" in {
    val maxCalls = 2
    val counter = for {
      tester <- genTestAction(maxCalls)
      wrappedAction = WrappedIO(tester.action, maxCalls)
      _ <- wrappedAction.run()
      _ <- wrappedAction.run()
      _ <- wrappedAction.run() // This will raise an error with the default stub
      _ <- wrappedAction.run()
      counter <- tester.callsCounter.get
    } yield counter

    counter.unsafeRunSync shouldBe (maxCalls) // This will be an error if the WrappedIO is just doing nothing with the action
  }

}
