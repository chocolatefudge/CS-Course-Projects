import org.otfried.cs109.readString
var total = 0

fun asking(): Boolean {
    var item : String = readString("What did you buy?").trim()
    if (item.isEmpty()) {
       println("In total, you spent %d KRW".format(total))
       return false
    } else {
      var number  = readString("How many %s did you buy?".format(item)).trim().toInt()
      var price   = readString("What is the price of one %s?".format(item)).trim().toInt()
      var total_price = number*price
      total+=total_price
      println("You bought %d %s for %d KRW.".format(number, item, total_price))
      asking()
      return true
    }
}

asking()