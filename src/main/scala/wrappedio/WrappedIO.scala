package wrappedio

import cats.effect.IO
import cats.effect.unsafe.implicits.global

// After: Add a generic parameter in the IO
case class WrappedIO(action: Unit => IO[Unit], maxCalls: Int) {
  ////////////////////
  // To complete
  def run(): IO[Unit] =
    for {
      res <- action()
    } yield res

  // End of the section to complete
  ////////////////
}
