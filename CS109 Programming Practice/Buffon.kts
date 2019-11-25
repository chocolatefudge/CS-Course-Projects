val random = java.util.Random()
var needle = 0

fun Buffon(n:Int) {
    for (i in 1..n) {
    	val y0 = random.nextDouble()
	val angle = random.nextDouble() * Math.PI / 2.0
	val y1 = y0 + Math.sin(angle)
	if (y1>1) {
	   needle+=1
	}
    }
}

println("Among $n needle throws, $needle crossed a line")
val d = 2*n/needle.toDouble()
println("2*($n/$needle) = $d")
