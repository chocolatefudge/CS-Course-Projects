val random = java.util.Random()

var hits=0

fun pi(n: Int) {
  for (i in 1..n) {
    val x = random.nextDouble()
    val y = random.nextDouble()
    if (x*x+y*y<=1) {
       hits+=1
    }
  }
  println("Among $n samples there was $hits hits")
  val pival = 4*hits.toDouble()/n
  println("4*(%d/%d) = %f".format(hits, n, pival))
}

val n = if (args.size == 1) args[0].toInt() else 100
pi(n)