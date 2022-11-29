public class MyQueue {
    private int[] arr;
    private int offerI;//end
    private int pollI;//begin
    private int size;
    private final int limit;

    public MyQueue(int limit) {
        arr=new int[limit];
        offerI=0;
        pollI=0;
        size=0;
        this.limit = limit;
    }

    public void offer(int value) {
        if(size==limit) {
            throw new RuntimeException("队列满了，不能再加了");
        }
        size++;
        arr[offerI]=value;
        offerI=(offerI+1)%limit;
    }
    public int pop() {
        if(size==0) {
            throw new RuntimeException("队列为空，不能再取元素");
        }
        size--;
        int ans=arr[pollI];
        pollI=(pollI+1)%limit;
        return ans;
    }
    public boolean isEmpty() {
        return size==0;
    }
}
