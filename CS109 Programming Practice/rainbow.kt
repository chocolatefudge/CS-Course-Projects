import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

import java.awt.image.BufferedImage

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

fun change(v:Int, g:BufferedImage): BufferedImage {
    for (i in 0..359) {
      for (j in 0..255) {
        	var givenrgb = hsvtorgb(i,j,v)
		val color = (givenrgb.first*65536)+(givenrgb.second*256)+givenrgb.third
		g.setRGB(i,j,color)
   }
   }
   return g
}

fun main(args:Array<String>) {
    
    val g = BufferedImage(360, 256, BufferedImage.TYPE_INT_RGB)
    
    var v = 255
    if (args.size != 0) {
       v=args[0].toInt()
       }
    
    setTitle("Rainbow for v= $v")
    show(change(v,g))
}
