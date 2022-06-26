import java.util.Arrays;

public class MyArrayList {
    public int [] elem;
    public int usedSize;
    public static final int DEFAULT_CAPACITY=5;

    public MyArrayList() {
        this.elem = new int[DEFAULT_CAPACITY];
    }

    public void display() {
        for (int i = 0; i < usedSize; i++) {
            System.out.println(elem[i]+" ");
        }
        System.out.println();
    }

    public void add(int data) {
        if(isFull()) {
            elem= Arrays.copyOf(elem,2*elem.length);
        }
        elem[usedSize++]=data;
    }
    private boolean isFull() {
        return usedSize==elem.length;
    }
    private void checkAddPos(int pos) {
        if(pos<0||pos>usedSize) {
            throw new IndexIllegalException("pos位置不合法");
        }
    }
    public void add(int pos,int data) {
        checkAddPos(pos);
        if(isFull()) {
            elem=Arrays.copyOf(elem,2*elem.length);
        }
        for (int i = usedSize; i >pos ; i--) {
            elem[i]=elem[i-1];
        }
        elem[pos]=data;
        usedSize++;
    }
    public boolean contains(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if(elem[i]==toFind) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if(elem[i]==toFind) {
                return i;
            }
        }
        return -1;
    }
    private void checkGetPos(int pos) {
        if(pos<0||pos>= usedSize) {
            throw new IndexIllegalException("pos位置不合法");
        }
    }
    public int get(int pos) {
        checkGetPos(pos);
        return elem[pos];
    }

    public void set(int pos,int val) {
        checkGetPos(pos);
        elem[pos]=val;
    }
    public void remove(int key) {
        int index=indexOf(key);
        if(index==-1) {
            System.out.println("wu");
            return;
        }
        for (int i = index; i < usedSize-1; i++) {
            elem[i]=elem[i+1];
        }
        usedSize--;
    }
    public int size() {
        return usedSize;
    }
    public void clear() {
        usedSize=0;
    }
}
