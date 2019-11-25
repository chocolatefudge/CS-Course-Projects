import org.otfried.cs109.readString
val total = mutableListOf<Triple<String, Int, Int>>()
var final_price=0

fun asking(): Boolean {
    var item : String = readString("What did you buy?").trim()
    if (item.isEmpty()) {
       println("Your purchases:/n")
       println("-------------------------------------------------------------")
       for (e in total) {
       	   val a=e.first
       	   val b=e.second
       	   val c=e.third
       	   val d = c/b
       	   println("$a  $b x $d  KRW $c KRW.")
	    }
       println("-------------------------------------------------------------")
       println("Total price:       %d KRW.".format(final_price))
       return false
    } else {
      var number  = readString("How many %s did you buy?".format(item)).trim().toInt()
      var price   = readString("What is the price of one %s?".format(item)).trim().toInt()
      var total_price = number*price
      total.add(Triple(item, number, total_price))
      println("You bought %d %s for %d KRW.".format(number, item, total_price))
      final_price+=total_price
      asking()
      return true
    }
}

asking()
