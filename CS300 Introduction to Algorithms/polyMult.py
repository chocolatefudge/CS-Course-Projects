A = [1,1,1,1,1]
B = [1,1,1,1,1]

def pre(X):
    if (len(X)%2!=0):
        X.append(0)
    return X

def post(X):
    while (X[-1]==0):
        X.pop()
    return X

def long(A,B):
    n = len(A)
    C = []
    for i in range(2*n-1):
        C.append(0)
    for i in range (n):
        for j in range (n):
            C[i+j]+=A[i]*B[j]
    return C

def karatsuba(A,B):
    A = pre(A)
    B = pre(B)
    n = len(A)
    if (n==2):
        return long(A,B)
    else:
        a1 = A[:n//2]
        a2 = A[n//2:]
        b1 = B[:n//2]
        b2 = B[n//2:]
        c1 = karatsuba(a1, b1)
        c2 = karatsuba(a1, b2)
        c3 = karatsuba(a2, b1)
        for i in range (len(c2)):
            c2[i]+=c3[i]
        c4 = karatsuba(a2, b2)
        C = []
        m = len(c1)
        for i in range(2*m+1):
            C.append(0)
        for i in range (m):
            C[i]+=c1[i]
            C[i+n//2]+=c2[i]
            C[i+n]+=c4[i]
        return C


print (long(A,B))
print (post(karatsuba(A,B)))
