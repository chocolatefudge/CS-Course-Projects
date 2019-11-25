val monthLength = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val leapMonthLength = listOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

var current_date = 0
var daynum = mutableListOf<Int>()

fun dayNumbers(monthDays: List<Int>): List<Int> {
    for (i in monthDays) {
    	daynum.add(13+current_date)
	current_date+=i
    }
    return daynum
}

var wd = mutableListOf(0,0,0,0,0,0,0)

fun weekDays(days: List<Int>): List<Int> {
    for (i in days) {
    	wd[i%7]=wd[i%7]+1
    }
    return wd
}

println(weekDays(dayNumbers(monthLength)))
println(weekDays(dayNumbers(leapMonthLength)))

    