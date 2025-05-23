# clock-fullscreen-scalajs
A simple fullscreen wake lock clock that runs in your browser made with ScalaJS.

## How to build on Linux/Ubuntu

* Install [Scala](https://scala-lang.org/download/) and [sbt](https://www.scala-sbt.org/)
* Install node and npm: `sudo apt install node npm`
* During development and testing:
  * run inside sbt `fastLinkJS`
  * open `index-dev.html` in your browser

* When ready to publish:
  * run inside sbt `fullLinkJS`
  * open `index.html` in your browser
  * copy the `index.html` and `main.js` to the server
