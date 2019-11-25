//
// Template for LED matrix animation
// 

import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import java.awt.image.BufferedImage
import org.otfried.cs109.DrawStyle


// --------------------------------------------------------------------

// Put the student ids of all members of your team in the following list.
// It is enough if one of you submits the file.

val authors = listOf(20169999, 20168888)

// --------------------------------------------------------------------

// global variables to control the animation
   var x:Int = 0
   var y:Double = 0.0
   var z:Int = 0
   var l:Double = 0.0
   var end:Int = 0
   var xx:Int = 0
   var a:Double =0.0
// put your global variables here

// --------------------------------------------------------------------

// setup() is called once to set up your animation:

fun setup() {
  // setup your global variables
  x=2
  y=9.0
  z=0
  l=0.0
  xx=25
  end=0
  a=1.0
}

// --------------------------------------------------------------------

// loop() is called  to compute the next frame of the animation.
// The argument leds is a bitmap of size 32 x 16.
// loop() needs to draw the next frame of the animation on this bitmap.
// Black means LED off, anything else means LED on.
// The bitmap is already cleared to black before loop() is called.
// If loop() returns 0, then the animation ends.

fun loop(leds: BufferedImage): Int {
  val g = ImageCanvas(leds)

  if (end==1) {
     g.translate(a,0.0)
     g.drawRectangle(xx.toDouble(), y, 2.0, 2.0)
     a+=1
     xx-=1
     y+=0.5
  }
  
  g.drawRectangle(0.0, 7.0, 3.0, 9.0)
  g.drawRectangle(3.0, 8.0, 1.0, 4.0)
  
  g.drawCircle(26.0, 4.0, 3.0, DrawStyle.STROKE)
  g.drawRectangle(25.0, 7.0, 3.0, 6.0)
  g.drawRectangle(24.0, 12.0, 1.0, 4.0)
  g.drawRectangle(28.0, 12.0, 1.0, 4.0)
  g.drawRectangle(23.0, 15.0, 1.0, 1.0)
  g.drawRectangle(29.0, 15.0 ,1.0, 1.0)

  g.setLineWidth(1.0)
 
  if (x<25){
     g.beginShape()
     g.moveTo(23.0,11.0)
     g.lineTo(22.0, 10.0)
     g.lineTo(31.0, 4.0)
     g.lineTo(32.0, 5.0)
     g.closePath()
     g.drawShape()
     x+=1
  } else {
     g.beginShape()
     g.moveTo(23.0,11.0)
     g.lineTo(22.0, 10.0)
     g.lineTo(28.0, 6.0)
     g.lineTo(29.0, 7.0)
     g.closePath()
     g.drawShape()
     z+=1
  }

  if ((z>=1)&&(l<=10.0)) {
     g.drawCircle(22.0, 9.0, l, DrawStyle.STROKE)
     l+=1.0
  }

  if ((z>=1)&&(l>10.0)&&(end==0)) {
      g.drawRectangle(xx.toDouble(), y, 2.0, 2.0)
      xx-=1
      y-=0.5
      if (y==-2.0) {
      	 end+=1
	 }
      
  } else if (end==0){
     g.drawRectangle(x.toDouble(), y, 2.0, 2.0)
    }

  
  g.done()
  return 100
}

// --------------------------------------------------------------------