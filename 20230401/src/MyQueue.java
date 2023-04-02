public class MyQueue {
    private int[] arr;
    private int offerI;
    private int pollI;
    private int size;
    private final int limit;

    public MyQueue(int limit) {
        this.limit=limit;
        arr=new int[limit];
        offerI=0;
        pollI=0;
        size=0;
    }

    public void offer(int value) {
        if(size==limit) {
            throw new RuntimeException("队列满了，不能再加了！");
        }
        size++;
        arr[offerI]=value;
        offerI=(offerI+1)%limit;
    }

    public int poll() {
        if(size==0) {
            throw new RuntimeException("队列空了，不能再取了！");
        }
        size--;
        int ret=arr[pollI];
        pollI=(pollI+1)%limit;
        return ret;
    }

    public boolean isEmpty() {
        return size==0;
    }
}
