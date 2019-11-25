def main():

    exampleGraph = [[0.0, 7.0, 0.0, 0.0, 1.0],
                    [7.0, 0.0, 6.0, 0.0, 5.0],
                    [0.0, 6.0, 0.0, 2.0, 3.0],
                    [0.0, 0.0, 2.0, 0.0, 4.0],
                    [1.0, 5.0, 3.0, 4.0, 0.0]]
                    
    MST = primAlgorithm(5, exampleGraph)
    
    for i in range(5):
        s = ""
        for j in range(5):
            s += (str(MST[i][j]) + " ")
        print(s)
        


# PRIM'S ALGORITHM: Given a weighted connected undirected graph, compute a MST.
#      INPUT:   n: number of vertices 
#               adj: adjacency matrix (2-dimensional array) with weights             
#                   adj[0][3] (= adj[3][0]) is the weight of the edge between vertex 0 and vertex 3
#                   weight of an edge is a floating point number between [0.5, 1000]
#                   if there is no edge between i and j, adj[i][j] = 0
#      OUTPUT:  minTree: adjacency matrix (2-dimensional array) with boolean entries of the MST
#                   minTree[i][j] = True if the MST has an edge between the vertex i and j
#                   minTree[i][j] = False if the MST has no edge between the vertex i and j
def primAlgorithm(n, adj):
    minTree = [x[:] for x in [[False] * n] * n]   
    n = len(adj)
    d = [1001.0 for i in range(n)]
    d[0] = 0.0
    Q = list(i for i in range(n))
    answer_v = []
    answer_e = []
    while(len(answer_v)<n):
        list1 = []
        for i in range(len(Q)):
            list1.append(d[Q[i]])
        a1 = list1.index(min(list1))
        for i in answer_v:
            list1.insert(i,1002.0)
        a = list1.index(min(list1))
        if (len(answer_v)>=1):
            for i in answer_v:
                if (adj[i][a] == d[a]):
                    t = (a,i)
            answer_e.append(t)
        Q.remove(Q[a1])
        answer_v.append(a)
        for i in range(n):
            if (adj[a][i]!=0):
                if (d[i]==1001.0 or adj[a][i]<d[i]):
                    d[i] = adj[a][i]
    for (i,j) in answer_e:
        minTree[i][j] = True
        minTree[j][i] = True
    return minTree



if __name__ == "__main__":
    main()
