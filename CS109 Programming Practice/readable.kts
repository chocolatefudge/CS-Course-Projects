// check if ch is a letter
fun isLetter(ch: Char) = ch in 'a'..'z' || ch in 'A'..'Z'

// shuffle(s) returns a randomly shuffled copy of the string s
fun shuffle(s: String): String {
  val l = s.toMutableList()
  java.util.Collections.shuffle(l)
  return l.joinToString("")
}

fun reorderLetters(s: String) {
  var i = 0
  while (i < s.length) {
    while (isLetter(s[i])==false) {
       print(s[i])
       i+=1
       if (i==s.length) {
	     break
	  }
    }
    if (i==s.length) {
	     break
	  }
    var start = i
    while (isLetter(s[i])) {
    	  i+=1
	  if (i==s.length) {
	     break
	  }
	}
    var end = i-1
    if ((end-start==0)||(end-start==1)|| (end-start==2)|| (end-start==3)) {
       print(s.substring(start,end+1))
    } else {
       print(s[start])
       print(shuffle(s.substring(start+1,end)))
       print(s[end])
    }
    if (i==s.length) {
       print(s[-1])
    }
    
  }
  println()
}

if (args.size != 1)
  println("Usage: kts readable.kts <filename>")
else
  java.io.File(args[0]).forEachLine { reorderLetters(it) }