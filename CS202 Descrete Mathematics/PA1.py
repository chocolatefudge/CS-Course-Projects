'''
1st python assignment for CS202 Discrete mathematics.

This code gets number of rows and columns of theater seats as input,
and generates seats with random occupation.
Then, it finds and returns all longest consecutive seats in theater.

'''

import random

class seat:
    def __init__(self, location, occupied):
        self.location = location
        self.occupied = occupied

    def empty(self):
        if not self.occupied:
            return True

    def mark(self):
        if self.occupied:
            return 'O'
        return 'X'

    def position(self):
        pos_x=chr(self.location[0]+65)
        pos_y=self.location[1]
        return("%s%d"%(pos_x,pos_y))

seats = []
row = int(input("number of rows\n"))
col = int(input("number of columns\n"))

#Function that generates theater by given inputs row, col.
def making_seats():
    for i in range (0,row):
        seats.append([])
        for j in range (0,col):
            random_int = random.randint(0,1)
            if random_int==0:
                seats[i].append(seat((i,j), False))
            else:
                seats[i].append(seat((i,j), True))

#Function prints occupation and place of each seats in theater.
def printing():
    print("  ", end = '')
    for i in range(col):
        print(i, end=' ')
    print('\n')
    for i in range(row):
        print(chr(i + 65) + ' ',end='')
        for j in range(col):
            if j<10:
                print(seats[i][j].mark()+' ',end='')
            else:
                print(seats[i][j].mark() + ' ', end=' ')
        print('\n')

#Function that searches for longest consequtive seats
def search():
    result_num=[]
    result=[]
    max=0
    during=False
    for i in range(0,row):
        for j in range(0,col):
            if seats[i][j].empty():
                if not during:
                    start=(i,j)
                    during=True
                else:
                    if j==col-1:
                        end = (i, j)
                        length = end[1] - start[1] + 1
                        if length >= max:
                            max = length
                            result_num.append([start, end, length])
                        during=False
            elif not seats[i][j].empty() and during:
                end=(i,j-1)
                length=end[1]-start[1]+1
                if length >= max:
                    max=length
                    result_num.append([start,end,length])
                during=False

    while result_num[0][2]!=max:
        result_num.pop(0)
    for i in result_num:
        result.append("From %s%d to %s%d" %(chr(i[0][0]+65),i[0][1],chr(i[0][0]+65),i[1][1]))
    return result

def main():
    making_seats()
    print("---------------------------")
    printing()
    print("---------------------------")
    a=search()
    print("The longest consecutive seats at")
    for i in a:
        print("".join(i))

main()
