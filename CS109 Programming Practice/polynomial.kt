@Suppress("UNUSED_PARAMETER")
class Polynomial(coeffs: Array<Int>) {

  constructor(vararg coeffs: Int) : this(coeffs.toTypedArray()) { }

  private val c = createCoeffs(coeffs)
  
  private fun createCoeffs(a: Array<Int>): List<Int> {
    var s = a.lastIndex
    while (s >= 0 && a[s] == 0)
      s -= 1
    return a.take(s+1)
  }

  fun degree(): Int = c.lastIndex

  fun coeff(i: Int): Int = if (i < c.size) c[i] else 0
  
  override fun toString(): String {
    var s = StringBuilder()
    var plus = ""
    var minus = "-"
    for (i in degree() downTo 0) {
      if (c[i] != 0) {
	var e = c[i]
	s.append(if (e > 0) plus else minus)
	plus = " + "; minus = " - "
	e = Math.abs(e)
	if (i == 0)
	  s.append(e)
	else {
	  if (e != 1) {
	    s.append(e)
	    s.append(" * ")
	  }
	  if (i > 1) {
	    s.append("X^")
	    s.append(i)
	  } else 
	    s.append("X")
	}
      }
    }
    return s.toString()
  }
  
  operator fun plus (rhs: Polynomial): Polynomial {
    val deg = Math.max(degree(), rhs.degree())
    val r = Array(deg + 1) { 0 }
    for (i in 0 .. deg)
      r[i] = coeff(i) + rhs.coeff(i)
    return Polynomial(r)
  }
  
  operator fun plus(rhs: Int) = this + Polynomial(rhs)
  
  operator fun minus (rhs: Polynomial): Polynomial {
    val deg = Math.max(degree(), rhs.degree())
    val r = Array(deg + 1) { 0 }
    for (i in 0 .. deg)
      r[i] = coeff(i) - rhs.coeff(i)
    return Polynomial(r)
  }
  
  operator fun minus(rhs: Int) = this - Polynomial(rhs)
  
  operator fun times (rhs: Polynomial): Polynomial {
    // first handle case if one factor is the zero polynomial
    if (degree() < 0)
      return this
    else if (rhs.degree() < 0)
      return rhs
    else {
      val deg = degree() + rhs.degree()
      val r = Array(deg + 1) { 0 }
      for (i in 0 .. deg) {
	var sum = 0
	for (j in 0 .. i)
	  sum += coeff(j) * rhs.coeff(i - j)
	r[i] = sum
      }
      return Polynomial(r)
    }
  }

  infix fun pow (ex: Int): Polynomial = when(ex) {
      0 -> Polynomial(1)
      1 -> this
      2 -> this * this
      3 -> this * this * this
      else -> {
	val p = this pow (ex / 2)
	if (ex % 2 == 1) p * p * this else p * p
      }
    }

  operator fun invoke(x: Double): Double {
    var i = degree()
    if (i==0) {
       return coeff(0).toDouble()
    }
    var result = coeff(i)*x+coeff(i-1)
    i-=1
    while (i>=1) {
    	  result=result*x+coeff(i-1)
	  i-=1
    }
    return result
  }
  
  fun derivative(): Polynomial {
      var ar = Array(c.lastIndex) {0}
      var i = 1
      while (i<=c.lastIndex) {
      	  ar[i-1]=i*c[i]
	  i+=1
      }
      return Polynomial(ar)  
   }

  operator fun div(rhs:Polynomial):Polynomial {
      var lc_list = mutableListOf<Int>()
      for (i in 0..degree()) {
      	  lc_list.add(coeff(i))
      }
      var lc = lc_list.joinToString("").reversed().toInt()
      var rc_list = mutableListOf<Int>()
      for (i in 0..rhs.degree()) {
      	  rc_list.add(rhs.coeff(i))
      }
      var rc = rc_list.joinToString("").reversed().toInt()
      if (rc%lc != 0) {
      	 throw ArithmeticException()
      }
      var answer = (rc/lc).toString().reversed()
      var ar = Array(answer.length) {0}
      for (i in 0 until answer.length) {
      	  ar[i]=answer[i].toInt()
      }
      return Polynomial(ar)
      }
   }
      
      

operator fun Int.times(rhs: Polynomial) = Polynomial(this) * rhs
	 
val X = Polynomial(0, 1)