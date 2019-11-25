import org.otfried.cs109.readString

var rows = 8
var cols = 8
var bomb = 6

if (args.size==3) {
rows = args[0].toInt()
cols = args[1].toInt()
bomb = args[2].toInt()
}

data class Pos(val row:Int, val col:Int)
val bombs = mutableSetOf<Pos>()
val field = Array<Array<Char>>(rows) { Array<Char>(cols) { '.' } }
val random = java.util.Random()

val alph = listOf("A","B","C","D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

fun generatebomb(rows:Int, cols:Int, bomb:Int) {
    while (bombs.size<bomb){
    	bombs.add(Pos(random.nextInt(cols), random.nextInt(rows)))
    }
}

fun printfield(rows:Int, cols:Int) {
   print("  ")
   for (i in 1..cols) {
       print(i/10)
       print(" ")
       if (i==cols) {
       	  println()
       }
   }
   print("  ")
   for (i in 1..cols) {
       print(i-((i/10)*10))
       print(" ")
       if (i==cols) {
       	  println()
       }
   }
   for (i in 1..rows) {
       print(alph[i-1])
       print(" ")
       for (j in 1..cols) {
       	   print(field[i-1][j-1])
	   print(' ')
	   }
       println()
   }
   
}

fun addbomb(position:Pos) {
    field[position.row][position.col] = '*'
}

fun addmark(position:Pos) {
    field[position.row][position.col] = '#'
}

fun reveal(position:Pos) {
    field[position.row][position.col] = ' '
}

fun addnum(position:Pos) {
    var num=0
    for (i in position.row-1..position.row+1) {
    	for (j in position.col-1..position.col+1) {
	    if ((i!=position.row)||(j!=position.col)){
	       if (Pos(i,j) in bombs) {
	       	  num+=1
		  }
	}
	}
	}
    if (num==0) {
       reveal(position)
    } else {
      field[position.row][position.col] = '0'+num
    }
}

fun checkwinning(rows:Int, cols:Int):Boolean {
    for (i in 0..rows-1){
    	for (j in 0..cols-1){
	    if (Pos(i,j) in bombs) {
	    } else{
	    if ((field[i][j]=='.')||(field[i][j]=='#')) {
	       	return false
		 }
	}
	}
	}
    return true
}
	    

fun minesweep(rows:Int, cols:Int):Boolean {
    var question = readString("What cell do you want to check? ").trim()
    if (question[0]=='#') {
       var position = Pair(question[1].toUpperCase()-'A', question.substring(2).toInt()-1)
       var givenpos = Pos(position.first, position.second)
       if ((position.first>rows)||(position.second>cols)) {
       	  	 println("I don't understand.")
       		 minesweep(rows, cols)
	 }
       addmark(givenpos)
       printfield(rows,cols)
       minesweep(rows,cols)

    } else {
      var position = Pair(question[0].toUpperCase()-'A', question.substring(1).toInt()-1)
      var givenpos = Pos(position.first, position.second)
      if ((position.first>rows)||(position.second>cols)) {
       	 println("I don't understand.")
       	 minesweep(rows, cols)
      }
     
       if (givenpos in bombs) {
       	  println()
       	  println("BOOM BOOM BOOM BOOM BOOM")
       	  println()
	  for (i in bombs) {
	      addbomb(i)
	  }
	  printfield(rows, cols)
	  return false
       } else {
       	 addnum(givenpos)
	 }
       if (checkwinning(rows, cols)) {
	    printfield(rows, cols)
	    println()
	    println("YOU WIN!")
	    return true
       } else {
       	 printfield(rows, cols)
	 minesweep(rows, cols)
       }
    }
return true
}



generatebomb(rows, cols, bomb)
minesweep(rows, cols)
      

          
    
