val words = java.io.File("words.txt").useLines { it.toSet() }

fun sortWord(s: String): String =
    s.toCharArray().sorted().joinToString(separator="")

fun findAnagrams(word:String):MutableList<String> {
    var anagrams = mutableListOf<String>()
    for (i in words) {
    var wordlower = word.toLowerCase().trim()
    	if (sortWord(i)==sortWord(wordlower)) {
	   anagrams.add(i)
	   }
	}
    return anagrams
}

println("<<Anagrams>>")
println("Anagrams for 'lovely'")
println(findAnagrams("lovely"))
println("Anagrams for 'sprouts'")
println(findAnagrams("sprouts"))
println("Anagrams for 'sorting'")
println(findAnagrams("sorting"))
println("Anagrams for 'protest'")
println(findAnagrams("protest"))
println("Anagrams for 'persist'")
println(findAnagrams("persist"))
println()

fun ePluribusUnum(phrase: String) {
  println("E pluribus unum: '$phrase' --> '${findAnagrams(phrase)}'")
}

println("<<E pluribus unum>>")
ePluribusUnum("SEA SALT")
ePluribusUnum("PANIC ROOMS")
ePluribusUnum("COTERIES")
ePluribusUnum("TREETOP ACTOR")
println()

fun countwords(word:String):MutableList<String>{
    var num = 0
    var wordlist = mutableListOf<String>()
    for (i in words) {
    	if (word in i) {
	   num+=1
	   wordlist.add(i)
	   }
	   }
    println(num)
    return wordlist
}

fun counting(a:MutableList<String>, b:MutableList<String>) : Int {
    for (i in b) {
    	if (i in a) {
	   a.remove(i)
    }
    }
    return a.size
}

println("<<Count words>>")
println("contain 'cie'")
countwords("cie")
println("contain 'cei'")
countwords("cei")
println("contain'ei'// contain'cei'// contain 'ei', but not 'cei'")
println(counting(countwords("ei"), countwords("cei")))
println("contain'ie'// contain'cie'// contain 'ie', but not 'cie'")
println(counting(countwords("ie"), countwords("cie")))
println()


println("<<Similar words>>")
fun similarwords1(a:String, b:String):MutableList<List<String>> {
    var length = a.length-1
    var listpair = mutableListOf<List<String>>()
    for (i in words) {
    	if (i.length==length) {
	   var otherword = i.substring(0,1)+b.substring(1,2)+i.substring(1)
	   if (otherword.toLowerCase() in words) {
	      listpair.add(listOf(i, otherword.toLowerCase()))
	      }
	     }
	    }
    return listpair
}

println(similarwords1("# ######", "#T######"))
println("A doctor: surgeon")
println("A fish:   sTurgeon")
println()

fun similarwords2(a:String, b:String):MutableList<List<String>> {
    var length = a.length-1
    var listpair = mutableListOf<List<String>>()
    for (i in words) {
    	if (i.length==length) {
	   var otherword = b.substring(0,1)+i
	   if (otherword.toLowerCase() in words) {
	      listpair.add(listOf(i, otherword.toLowerCase()))
	      }
	     }
	    }
    return listpair
}

println(similarwords2(" ######", "Z######"))
println("Eggs:     oology")
println("Animals: Zoology")
println()

fun similarwords3(a:String, b:String):MutableList<List<String>> {
    var length = b.length-1
    var listpair = mutableListOf<List<String>>()
    for (i in words) {
    	if (i.length==length) {
	   var otherword = i.substring(0,5)+a.substring(5,6)+i.substring(5)
	   if (otherword.toLowerCase() in words) {
	      listpair.add(listOf(otherword.toLowerCase(),i))
	      }
	     }
	    }
    return listpair
}

println(similarwords3("#####T#####", "##### #####"))
println("Makes you famous:   immorTality")
println("Makes you infamous: immorality")
println()

fun similarwords4(a:String, b:String):MutableList<List<String>> {
    var length = a.length
    var listpair = mutableListOf<List<String>>()
    for (i in words) {
    	if (i.length==length) {
	   var otherword = i.substring(0,4)+b.substring(4,5)+i.substring(5)
	   if ((i.substring(4,5)=="c")&&(otherword.toLowerCase() in words)){
	      listpair.add(listOf(i, otherword.toLowerCase()))
	      }
	     }
	    }
    return listpair
}

println(similarwords4("####C##", "####D##"))
println("sweet and sticky:   treaCle")
println("It's foot operated: treaDle")
println()

fun similarwords5(a:String, b:String, c:String, d:String):MutableList<List<String>> {
    var length = a.length
    var listpair = mutableListOf<List<String>>()
    for (i in words) {
    	if (i.length==length) {
	   var ow = i.substring(1)
	   var otherword1 = b.substring(0,2).toLowerCase()+ow
	   var otherword2 = c.substring(0,3).toLowerCase()+ow
	   var otherword3 = d.substring(0,4).toLowerCase()+ow
	   if ((i.substring(0,1)=="e")&&(otherword1 in words)&&(otherword2 in words)&&(otherword3 in words))  {
	      listpair.add(listOf(i, otherword1, otherword2, otherword3))
	      }
	      }
	      }
    return listpair
}

println(similarwords5("E#####", "AU#####", "CRE#####", "EXPE#####"))
println("   Edited")
println("  AUdited")
println(" CREdited")
println("EXPEdited")
println()

fun similarwords6(a:String, b:String, c:String, d:String):MutableList<List<String>> {
    var length = b.length
    var listpair = mutableListOf<List<String>>()
    for (i in words) {
    	if (i.length == length) {
	   var ow = i.substring(1)
	   var otherword1 = a.substring(0,2).toLowerCase()+ow
	   var otherword2 = c.substring(0,1).toLowerCase()+ow
	   var otherword3 = d.substring(0,1).toLowerCase()+ow
	   if ((i.substring(0,1)=="b")&&(otherword1 in words)&&(otherword2 in words)&&(otherword3 in words))  {
	   listpair.add(listOf(otherword1, i, otherword2, otherword3))
	      }
	      }
	      }
    return listpair
}

println(similarwords6("RH###", "B###", "C###", "T###"))
println("RHomb")
println(" Bomb")
println(" Comb")
println(" Tomb")
println()