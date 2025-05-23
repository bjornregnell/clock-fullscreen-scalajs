import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.console
import scala.scalajs.js
import scala.scalajs.js.timers._
import scala.scalajs.js.annotation.JSGlobal

@main def main(): Unit = document.addEventListener("DOMContentLoaded", (e: dom.Event) => setupUI())
  
def setupUI(): Unit = 
  val clockDiv = document.createElement("div")
  clockDiv.id = "clock"
  document.body.appendChild(clockDiv)
  applyStyles()
  updateTime()
  setInterval(1000):
    updateTime()

  document.body.addEventListener("click", (e: dom.Event) => {
    if dom.document.fullscreenElement == null 
    then dom.document.documentElement.requestFullscreen()
    else dom.document.exitFullscreen()
  })

  dom.window.addEventListener("resize", (e: dom.Event) => {
    setFontSize()
  })

  setFontSize()

  document.addEventListener("visibilitychange", (e: dom.Event) => {
    if document.visibilityState.equals("visible") then
      WakeLock.request()
  })
  WakeLock.request()
end setupUI

def updateTime(): Unit = 
  val now = new js.Date()
  val hours   = f"${now.getHours().toInt}%02d"
  val minutes = f"${now.getMinutes().toInt}%02d"
  val seconds = f"${now.getSeconds().toInt}%02d"
  val timeStr = s"$hours:$minutes:$seconds"
  document.getElementById("clock").textContent = timeStr


def setFontSize(): Unit = 
  // val minDim = math.min(dom.window.innerWidth, dom.window.innerHeight)
  // val fontSize = (minDim * 0.2).toInt
  //document.getElementById("clock").setAttribute("style", s"font-size: ${fontSize}px;")
  document.getElementById("clock").setAttribute("style", s"font-size: 15vw ;")

def applyStyles(): Unit = 
  val style = document.createElement("style")
  style.innerHTML =
    """
      html, body {
        margin: 0;
        padding: 0;
        height: 100%;
        width: 100%;
        background-color: black;
        color: red;
        font-family: monospace;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      
      #clock {
        line-height: 1;
        text-align: center;
        width: 100%;
      }
    """
  document.head.appendChild(style)


object WakeLock:

  def request(): Unit =
    val wakeLock = DOMGlobals.window.navigator.wakeLock
    if wakeLock.isDefined then
      wakeLock.get.request("screen").`then`((wakeLock: WakeLockSentinel) => {
        console.log("Wake lock active")
        wakeLock.addEventListener("release", (e: dom.Event) => console.log("Wake lock was released"))
      }, (reason: Any) => {
        console.error("Failed to acquire wake lock")
      })

  object DOMGlobals:
    @js.native
    @JSGlobal("window")
    val window: Window = js.native

  @js.native
  trait Window extends js.Object:
    val navigator: Navigator = js.native

  @js.native
  trait Navigator extends js.Object:
    val wakeLock: js.UndefOr[WakeLock] = js.native

  @js.native
  trait WakeLock extends js.Object:
    def request(`type`: String): js.Promise[WakeLockSentinel] = js.native

  @js.native
  trait WakeLockSentinel extends dom.EventTarget
