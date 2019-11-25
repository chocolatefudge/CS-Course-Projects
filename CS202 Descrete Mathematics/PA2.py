'''
2nd Python assignment for CS202 Discrete Mathematics

This code gets adjacency matrix for certain weighted graph as an input,
prints the adjacency matrix, and finds the fastest way form junction 0 to last junction.
If there is no path exists, code simply returns "There is no path". 

'''

from random import *

# CS204B Homework 6, Problem 4

# default one
James_city = [[0, 3, 10, 0, 0, 0, 0], [3, 0, 5, 0, 0, 0, 0], [10, 5, 0, 4, 0, 0, 0], [0, 0, 4, 0, 1, 5, 9],
              [0, 0, 0, 1, 0, 6, 7], [0, 0, 0, 5, 6, 0, 3], [0, 0, 0, 9, 7, 3, 0]]


# Generate random city similar as James' city(Adjacency Matrix)
# If you want test more cases, use this function or modify it yourself.
def Generate_city():
    junction_num = 7
    # Gernerate empty adjacency matrix
    matrix = []
    for i in range(0, int(junction_num)):
        row = []
        for j in range(0, int(junction_num)):
            row.append(0);
        matrix.append(row)

    # Generate random matrix
    for i in range(0, int(junction_num) - 1):
        for j in range(i + 1, int(junction_num)):
            if (James_city[i][j] != 0):
                matrix[i][j] = randint(0, 10)
                matrix[j][i] = matrix[i][j]

    return matrix


def Display_matrix(matrix):
    print("")
    print("----------------------------------------------------")
    print("Adjacency Matrix of City")
    print("----------------------------------------------------")
    print("")
    for i in range(0, len(matrix)):
        for j in range(0, len(matrix)):
            print("%3d" % (matrix[i][j]), end=' ')
        print("")

    print("")
    print("----------------------------------------------------")
    print("0 means unconnected, natural number means elasped time to pass")
    print("----------------------------------------------------")
    print("")


def Find_fastest_path(city):

    # 1. Find the fastest path
    # James' home is loacted in junction 0(first row/column) and theater is located in last junction(last row/column)
    # Your algorithm should work for any conditions (#junctions, #roads, connectivity)

    # Here you implement your algorithm

    # 2. Print all fastest paths. (If there is no path, then you have to print 'There is no path')
    # You don't need to return the result, print all paths here.
    # Print sentence have to contain path(ex. 0-1-2-3-4-5-6) and elasped time(ex. 22)

    print("")
    print("Find the minimum path from " + str(0) + " to " + str(len(city) - 1))
    print("")
    dest = len(city)
    path_list = [(0,[0],0)]
    paths = []
    while path_list:
        (vertex, path, time) = path_list.pop()
        for i in range(0, dest):
            if city[vertex][i] !=0 and i not in path:
                if i==dest-1:
                    paths.append((path+[i], time+city[vertex][i]))
                else:
                    path_list.append((i, path+[i], time+city[vertex][i]))
    if len(paths)==0:
        print('There is no path')
    else:
        a=[]
        b=[]
        for i in paths:
            a.append(i[0])
            b.append(i[1])
        min_idx = [b.index(min(b))]
        for i in range(len(b)):
            if b[i]==min(b) and i!=min_idx[0]:
                min_idx.append(i)
        for i in min_idx:
            print('-'.join(str(x) for x in a[i]))
        print(min(b))
    return


city = Generate_city()
Display_matrix(city)
Find_fastest_path(city)
