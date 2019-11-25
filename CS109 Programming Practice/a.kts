fun addValues(s: List<String?>?, m: Map<String,Int>?): Int {
    var sum = 0
    if ((s==null)||(m==null)) {
       println(sum)
       return sum
       }
    for (key in s) {
    	if (key==null) {
	   sum+=0
	} else {
	   var b = m.getOrElse(key) {0}
	   sum+=b
	   }
    }
    println(sum)
    return sum
}

val m = mapOf("il" to 1, "i" to 2, "sam" to 3, "sa" to 4, "o" to 5, "yuk" to 6, "chil" to 7, "pal" to 8, "gu" to 9, "sip" to 10)
addValues(null, m)
addValues(listOf("sam", "o", "fun", null, "yuk", null, "weird", "sip"), m)
addValues(listOf<String>(), m)
addValues(listOf("no", "such", "string"), m)
addValues(listOf("some", "i", "string", "sam"), m)
addValues(listOf("il", "i"), null)