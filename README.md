# clock-fullscreen-scalajs

* A simple fullscreen clock that runs in your browser.
* Nice when you are lecturing and want to keep track of the time.
* You can watch it live here: [bjornregnell.se/clock](https://bjornregnell.se/clock)
* Click to make it fullscreen. 
* It has a wake lock so that screen savers don't prevent the clock from being visible. So don't leave your phone unattended when the clock is in fullscreen mode...
* Made with awesome [ScalaJS](https://www.scala-js.org/).
* This app is feature complete.

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
