def fibRec(n):
    if (n==0 or n==1):
        return n
    else:
        return fibRec(n-1)+fibRec(n-2)

def fibIter(n):
    fibcur = 1
    fibprev = 0
    if (n==0 or n==1):
        return n
    while(n>1):
        temp = fibprev
        fibprev = fibcur
        fibcur = temp+fibprev
        n-=1
    return fibcur

def fibRS(n):
    if (n==0):
        return 0
    elif (n<15):
        return fibIter(n)
    else:
        return RS(n)[1]

def RS(n):
    if (n==1):
        return (1,1,1,0)
    elif (n%2==0):
        a,b,c,d = RS(n/2)
        return (a*a+b*c, a*b+b*d, a*c+b*d, b*c+d*d)
    else:
        a,b,c,d = RS((n-1)/2)
        return (a*a+b*c+a*b+b*d, a*a+b*c, a*c+b*d+b*c+d*d, a*c+b*d)
        
print(fibRec(30))
print(fibIter(30))
print(fibRS(30))
