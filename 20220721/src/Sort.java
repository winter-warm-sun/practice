import jdk.nashorn.internal.ir.SplitReturn;

import java.util.Stack;

public class Sort {
    public static void heapSort(int[] array) {
        createBigHeap(array);
        int end=array.length-1;
        while (end>=0) {
            swap(array,0,end);
            shiftDown(array,0,end);
            end--;
        }
    }

    private static void createBigHeap(int[] array) {
        for (int parent = (array.length-1-1)/2; parent >= 0 ; parent--) {
            shiftDown(array,parent,array.length);
        }
    }

    private static void shiftDown(int[] array,int parent, int len) {
        //保证有左孩子
        int child=2*parent+1;
        while (child<len) {
            //如果有右孩子，左右孩子比较，child记录较大值的下标
            if(child+1<len&&array[child]<array[child+1]) {
                child++;
            }
            if(array[child]>array[parent]) {
                swap(array,child,parent);
                //继续向下调整
                parent=child;
                child=2*parent+1;
            }else {
                break;
            }
        }
    }
    private static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
    
    public static void bubbleSort(int[] array) {
        //外层循环确定趟数
        for (int i = 0; i < array.length-1; i++) {
            boolean flag=false;
            //内存循环调整元素，遍历后把最大元素放在最后，所以比较次数每次减1
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j]>array[j+1]) {
                    swap(array,j,j+1);
                    flag=true;
                }
            }
            if(!flag) break;
        }
    }
    private static int partitionHole(int[] array,int start,int end) {
        int key=array[start];
        while (start<end) {
            while (start<end&&array[end]>=key) {
                end--;
            }
            array[start]=array[end];
            while (start<end&&array[start]<=end) {
                start++;
            }
            array[end]=array[start];
        }
        array[start]=key;
        return start;
    }

    private static int partitionHoare(int[] array,int start,int end) {
        int i=start;//先存储好start下标，用于最后的交换
        int key=array[start];
        while (start<end) {
            while (start<end&&array[end]>=key) {
                end--;
            }
            while (start<end&&array[start]>=key) {
                start++;
            }
            swap(array,start,end);
        }
        swap(array,start,i);
        return start;
    }

    private static int partition(int[] array,int start,int end) {
        int prev=start;
        int cur=start+1;
        while (cur<=end) {
            if(array[cur]<array[start]&&array[++prev]!=array[cur]) {
                swap(array,cur,prev);
            }
            cur++;
        }
        swap(array,prev,start);
        return prev;
    }
    private static void quick(int[] array,int left,int right) {
        if(left>=right) {
            return;
        }
        //小区间使用直接插入排序： 主要 优化了递归的深度
        if(right-left+1<=7) {
            insertSort(array, left, right);
            return;
        }
        //三数取中：解决递归深度问题 基本上 有了三数取中  你的待排序序列 基本上每次都是二分N*logn
        int index = midNumIndex(array,left,right);
        swap(array,left,index);

        int pivot=partitionHoare(array,left,right);
        quick(array,left,pivot-1);
        quick(array,pivot+1,right);
    }
    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }
    private static int midNumIndex(int[] array,int left,int right) {
        int mid=(left+right)/2;
        int a=array[left]>array[right]?array[left]:array[right];
        return a>array[mid]?array[mid]:a;
    }
    private static void insertSort(int[] array,int start,int end) {
        for (int i = start+1; i <=end ; i++) {
            int tmp=array[i];
            int j=i-1;
            for (; j >=start ; j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            array[j+1]=tmp;
        }
    }

    public static void quickSort2(int[] array) {
        Stack<Integer> stack=new Stack<>();
        int left=0;
        int right=array.length-1;
        int index=midNumIndex(array,left,right);
        swap(array,left,index);
        int pivot=partitionHole(array,left,right);
        if(pivot>left+1) {
            stack.push(left);
            stack.push(pivot-1);
        }
        if (pivot<right-1) {
            stack.push(pivot+1);
            stack.push(right);
        }
        while (!stack.empty()) {
            right=stack.pop();
            left=stack.pop();
            index=midNumIndex(array,left,right);
            swap(array,left,index);
            pivot=partitionHole(array,left,index);
            if(pivot>left+1) {
                stack.push(left);
                stack.push(pivot-1);
            }
            if(pivot<right-1) {
                stack.push(pivot+1);
                stack.push(right);
            }
        }
    }

    public static void mergeSort(int[] array) {
        mergeSortFunc(array,0,array.length-1);
    }
    private static void mergeSortFunc(int[] array,int left,int right) {
        if(left>=right) {
            return;
        }
        int mid=(left+right)/2;
        //分解左边
        mergeSortFunc(array,left,mid);
        //分解右边
        mergeSortFunc(array,mid+1,right);
        //进行合并
        merge(array,left,right,mid);
    }

    /**
     * 实现归并
     * @param array
     * @param start
     * @param end
     * @param midIndex
     */
    private static void merge(int[] array,int start,int end,int midIndex) {
        int[] tmpArr=new int[end-start+1];
        int k=0;
        int s1=start;
        int s2=midIndex+1;
        while (s1<=midIndex&&s2<=end) {
            if(array[s1]<=array[s2]) {
                tmpArr[k++]=array[s1++];
            }else {
                tmpArr[k++]=array[s2++];
            }
        }
        //当两个归并段中有一段没数据后，把另一段剩余数据全部拷贝到tmpArr数组中去
        while (s1<=midIndex) {
            tmpArr[k++]=array[s1++];
        }
        while (s2<=end) {
            tmpArr[k++]=array[s2++];
        }
        //把排好序的数字拷贝回原数组
        for (int i = 0; i < k; i++) {
            array[i+start]=tmpArr[i];
        }
    }
    public static void mergeSort2(int[] array) {
        int gap=1;
        while (gap<array.length) {
            for (int i = 0; i < array.length; i+=gap*2) {
                int s1=i;
                int e1=s1+gap-1;
                if(e1>=array.length) {
                    e1=array.length-1;
                }
                int s2=e1+1;
                if(s2>=array.length) {
                    s2=array.length-1;
                }
                int e2=s2+gap-1;
                if(e2>=array.length) {
                    e2=array.length-1;
                }
                merge(array,s1,e2,e1);
            }
            gap*=2;
        }
    }
}
