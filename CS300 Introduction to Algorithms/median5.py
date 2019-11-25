def orderStat(X, l, r, k):
    while(l<r):
        p = approxMed(X, l, r)
        q = partition(X, l, r, X[p])
        if (q-l>=k):
            r=q
        else:
            l = q+1
    return r

def approxMed(X, l, r):
    if(r<=l+5):
        return median5(X)
    i = l
    a = []
    while(i<r):
        m = median5(X[i:min(r, i+4)])
	a.append(m)
	i+=5
    return orderStat(a, 0, len(a)-1, len(a)/2)
	

def partition(X, l, r, y):
    a = l
    b = r
    while (a<b):
        while(a<b and X[a]<=y):
            a+=1
	while(a<b and X[b]>=y):
	    b+=1
        X[a], X[b] = X[b], X[a]
    return a

def median5(X):
    X = bubblesort(X)
    return X[len(X)//2]

def bubblesort(X):
    for i in range (len(X)):
        while (X[i]!=X[-1] and X[i]>X[i+1]):
            X[i], X[i+1] = X[i+1], X[i]
            i+=1
    return X
