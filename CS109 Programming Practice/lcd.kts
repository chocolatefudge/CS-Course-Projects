val digits = 
  listOf(listOf(true, true, true, true, true, true, false),     // 0
	listOf(true, true, false, false, false, false, false),  // 1
	listOf(true, false, true, true, false, true, true),     // 2
	listOf(true, true, true, false, false, true, true),     // 3
	listOf(true, true, false, false, true, false, true),    // 4
	listOf(false, true, true, false, true, true, true),     // 5
	listOf(false, true, true, true, true, true, true),      // 6
	listOf(true, true, false, false, false, true, false),   // 7
	listOf(true, true, true, true, true, true, true),       // 8
	listOf(true, true, true, false, true, true, true),      // 9
	listOf(false, false, false, false, false, false, false)) // Blank


fun lcdDigit(digit: Char, k: Int, c: Char): String {
  val d = if ('0' <= digit && digit <= '9') digit - '0' else 10
  var num_list = digits[d]
  var char_list = mutableListOf<String>()
  var return_list = mutableListOf<String>()
  for (i in 0..6) {
      if (num_list[i]) {
      	 char_list.add(c.toString())
      } else {
      	 char_list.add(' '.toString())
      }
   }
    char_list.add(' '.toString())
  return_list.add(" ${char_list[5].repeat(k)} ")
  for (i in 1..k) {
      return_list.add("${char_list[4]}${char_list[7].repeat(k)}${char_list[0]}")
      }
  return_list.add(" ${char_list[6].repeat(k)} ")
  for (i in 1..k) {
      return_list.add("${char_list[3]}${char_list[7].repeat(k)}${char_list[1]}")
      }
  return_list.add(" ${char_list[2].repeat(k)} ")
  return return_list.joinToString(separator="\n")
  
}

fun combine(left: String, sep: String, right: String): String {
  var leftstr = left.split("\n")
  var rightstr = right.split("\n")
  var result_list = mutableListOf<String>()
  for (i in 0..leftstr.size-1) {
      val total = listOf(leftstr[i], sep, rightstr[i])
      result_list.add(total.joinToString(""))
      }
  return result_list.joinToString(separator="\n")
}

fun lcd(s: String, k: Int, c: Char, sep: String): String {
  var result = lcdDigit(s[0], k, c)
  for (i in 1 until s.length)
    result = combine(result, sep, lcdDigit(s[i], k, c))
  return result
}

fun clearScreen() {
  println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
}

fun clock() {
  val form = java.text.SimpleDateFormat("HH mm ss")
  var current = form.format(java.util.Calendar.getInstance().getTime())
  clearScreen()
  println(lcd(current, 4, '#', " "))
  while (true) {
    Thread.sleep(100)
    val ntime = form.format(java.util.Calendar.getInstance().getTime())
    if (ntime != current) {
      current = ntime
      clearScreen()
      println(lcd(current, 4, '#', " "))
    }
  }
}