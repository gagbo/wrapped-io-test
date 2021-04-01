package wrappedio

import cats.effect.IO
import cats.effect.unsafe.implicits.global

// After: Add a generic parameter in the IO
case class WrappedIO[T](action: Unit => IO[T], maxCalls: Int) {
  ////////////////////
  // To complete
  def run(): IO[T] =
    for {
      res <- action()
    } yield res

  // End of the section to complete
  ////////////////
}
