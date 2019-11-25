fun next(n:Int):Int =
    if (n%2 == 0)
       n/2
    else
	3*n+1

fun collatz(n0:Int) {
    var n = n0
    while (n!=1) {
    	  print(n)
	  print(" ")
	  n = next(n)
    }
    println(1)
}

fun collatzcount(n0:Int):Int {
    var n = n0
    var count = 0
    while (n!=1) {
    	  count+=1
	  n=next(n)
    }
    return count
}

fun findmax(n: Int) {
    var maxCount = 0
    var maxStart = 1
    for (i in 2..n) {
    	val count = collatzcount(i)
	