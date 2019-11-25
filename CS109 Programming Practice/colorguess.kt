import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.readString
import java.awt.image.BufferedImage
val random = java.util.Random()
val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
var listalph = listOf("A", "B", "C", "D")

fun hsvtorgb(h: Int, s: Int, v: Int): Triple<Int, Int, Int> {
  if (s == 0) {
    // no color, just grey
    return Triple(v, v, v)
  } else {
    val sector = h / 60
    val f = (h % 60) 
    val p = v * ( 255 - s ) / 255
    val q = v * ( 15300 - s * f ) / 15300
    val t = v * ( 15300 - s * ( 60 - f )) / 15300
    return when(sector) {
      0 -> Triple(v, t, p)
      1 -> Triple(q, v, p)
      2 -> Triple(p, v, t)
      3 -> Triple(p, q, v)
      4 -> Triple(t, p, v)
      else -> Triple(v, p, q)
    }
  }
}


fun rcol(color:Triple<Int, Int, Int>):Int {
    var givenrgb = hsvtorgb(color.first, color.second, color.third)
    val col = (givenrgb.first*65536)+(givenrgb.second*256)+givenrgb.third
    return col
}

fun randomHSV(): Triple<Int, Int, Int> {
  return Triple(random.nextInt(360),
  	        128 + random.nextInt(128),
		128 + random.nextInt(128))
}

fun difcolor(color:Triple<Int, Int, Int>, d:Int):Triple<Int, Int, Int> {
    var hue = color.first
    var a:Int = random.nextInt(2)
    var newhue = 0
    if (a==0) {
       newhue = (hue-d)%360
    } else {
       newhue = (hue+d)%360
    }
    return Triple(newhue, color.second, color.third)

}

fun display(color:Triple<Int, Int, Int>, difcolor:Triple<Int, Int, Int>, image: BufferedImage):Pair<Int,Int>{
    val g = ImageCanvas(image)
    g.clear(Color.WHITE)
    var t:Int = random.nextInt(16)
    var color1=hsvtorgb(color.first, color.second, color.third)
    var difcolor1=hsvtorgb(difcolor.first, difcolor.second, difcolor.third)
    g.setColor(Color(color1.first, color1.second, color1.third))
    for (i in 1..4) {
    	for (j in 1..4) {
	    g.drawRectangle(100.0*i, 100.0*j, 80.0, 80.0, DrawStyle.FILL)
   }
   }
   g.setColor(Color(difcolor1.first, difcolor1.second, difcolor1.third))
   var y = t/4
   var x = t%4
   g.drawRectangle(100.0+100.0*x,100.0+100.0*y, 80.0, 80.0, DrawStyle.FILL)

   g.setColor(Color.BLACK)
   g.setFont(20.0, "Batang")
   g.drawText("A", 130.0, 50.0)
   g.drawText("B", 230.0, 50.0)
   g.drawText("C", 330.0, 50.0)
   g.drawText("D", 430.0, 50.0)
   g.drawText("1", 50.0, 150.0)
   g.drawText("2", 50.0, 250.0)
   g.drawText("3", 50.0, 350.0)
   g.drawText("4", 50.0, 450.0)
   g.done()
   show(image)
   return Pair(x,y)
}


fun main(args : Array<String>){
    var delta = 20
     if (args.size != 0) {
       delta=args[0].toInt()
       }
    var correct = 0
    var total = 0
    
    while (true) {
    var color = randomHSV()
    var ans = display(color, difcolor(color,delta), image)
    var anspos = Pair(ans.second+1, listalph[ans.first])
    

    var a = readString("Which square has a different color? (x to exit)")
    if (a=="x") {
       close()
       }
    else {
       total+=1
       if ((a.substring(1).toUpperCase()==anspos.second.toString())&&(a.substring(0,1).toInt()==anspos.first)) {
       	  correct+=1
	  println("That is correct")
	  println("You answered $correct of $total tests correcrly.")
       } else {
       	 println("That is not correct. Square %s%s has a different color.".format(anspos.first, anspos.second))
	 println("You answered $correct of $total tests correcrly.")
       }
       }
}
}