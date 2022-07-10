public class Test {
}
class MyCircularQueue {

    public int[] elem;
    public int usedSize;
    public int front;//队头下标
    public int rear;//队尾下标

    public MyCircularQueue(int k) {
        this.elem = new int[k];
    }

    //入队
    public boolean enQueue(int value) {
        if(isFull()) {
            //扩容 需要注意
            return false;
        }
        this.elem[rear] = value;
        //千万不可以加加 ：   rear++;
        rear = (rear+1) % elem.length;
        this.usedSize++;
        return true;
    }
    //出队
    public boolean deQueue() {
        if(isEmpty()) {
            return false;
        }
        front = (front+1)% elem.length;
        this.usedSize--;

        return true;
    }

    //获取队头元素
    public int Front() {
        if(isEmpty()) {
            return -1;
        }
        return elem[front];
    }

    //获取队尾元素
    public int Rear() {
        if(isEmpty()) {
            return -1;
        }
        int index = (rear == 0) ? elem.length-1 : rear-1;
        return elem[index];
    }

    public boolean isEmpty1() {
        return rear == front;
    }

    public boolean isFull2() {
        return (rear+1)% elem.length == front;
    }

    public boolean isEmpty() {
        return usedSize==0;
    }

    public boolean isFull() {
        return usedSize ==  elem.length;
    }
}