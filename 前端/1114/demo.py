​# 1、画棋盘，初始化棋盘
from turtle import *
screensize()
tracer(0)
 
for i in range(15):#画横向
    penup()
    goto(-280,-280+i*40)
    pendown()
    fd(560)
left(90)
for i in range(15):#画竖向
    penup()
    goto(-280+i*40,-280)
    pendown()
    fd(560)
 
penup() #画标点
goto(0,0)
dot()
goto(160,160)
dot()
goto(-160,160)
dot()
goto(160,-160)
dot()
goto(-160,-160)
dot()
 
​# 2、鼠标事件，点击落子
turn=0 #记录该哪方落子
def funclick(x,y):
    global turn
    global arr
    if turn==0: #黑子先下1
        for i in range(15):
            Y=280-i*40
            for j in range(15):
                X=-280+j*40  #棋盘上的整点
                if abs(X-x)<20 and abs(Y-y)<20 and arr[i][j]==0:
                    arr[i][j]=1
                    goto(X,Y)
                    dot(20) 
                    turn=1  #交替下子
                    black_is_end(i,j)
    if turn==1: #白子-1
        for i in range(15):
            Y=280-i*40
            for j in range(15):
                X=-280+j*40  #棋盘上的整点
                if abs(X-x)<20 and abs(Y-y)<20 and arr[i][j]==0:
                    arr[i][j]=-1
                    goto(X+10,Y)
                    pendown()
                    circle(10)
                    penup()
                    turn=0  #交替下子
                    white_is_end(i,j)
    
onscreenclick(funclick)


​
 
 
 
# 3、判断4个方向是否连成五个子
 
def fun_null(x,y):
    return ;
 
def black_is_end(i,j):
    global arr
    for m in range(11): #竖向
        if arr[m][j]==1 and arr[m+1][j]==1 and arr[m+2][j]==1 and \
        arr[m+3][j]==1 and arr[m+4][j]==1:
            print("游戏结束，黑子胜利！")
            onscreenclick(fun_null)
    for m in range(11): #横向
        if arr[i][m]==1 and arr[i][m+1]==1 and arr[i][m+2]==1 and \
        arr[i][m+3]==1 and arr[i][m+4]==1:
            print("游戏结束，黑子胜利！")
            onscreenclick(fun_null)
    if i<=j:            #\向
        m=j-i     
        for k in range(m,11):
            if arr[k-m][k]==1 and arr[k-m+1][k+1]==1 and arr[k-m+2][k+2]==1 and \
               arr[k-m+3][k+3]==1 and arr[k-m+4][k+4]==1:
                print("游戏结束，黑子胜利！")
                onscreenclick(fun_null)
    if i>j:             #\向
        m=i-j    
        for k in range(m,11):
            if arr[k][k-m]==1 and arr[k+1][k-m+1]==1 and arr[k+2][k-m+2]==1 and \
               arr[k+3][k-m+3]==1 and arr[k+4][k-m+4]==1:
                print("游戏结束，黑子胜利！")
                onscreenclick(fun_null)
    n=i+j               #/向
    if n<=14:
        for k in range(0,n-4):
            if arr[k][n-k]==1 and arr[k+1][n-k-1]==1 and arr[k+2][n-k-2]==1 and \
               arr[k+3][n-k-3]==1 and arr[k+4][n-k-4]==1:
                print("游戏结束，黑子胜利！")
                onscreenclick(fun_null)
    if n>14:
        for v in range(n-14,11):
            if arr[v][n-v]==1 and arr[v+1][n-v-1]==1 and arr[v+2][n-v-2]==1 and \
               arr[v+3][n-v-3]==1 and arr[v+4][n-v-4]==1:
                print("游戏结束，黑子胜利！")
                onscreenclick(fun_null)
                
 
 
​