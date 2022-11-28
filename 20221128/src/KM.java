import java.util.HashMap;
import java.util.HashSet;

public class KM {
    /*
    输入一定能够保证，数组中所有的数都出现了M次，只有一种数出现了K次
    1<=K<M
    返回这种数
    */

    public static int km(int[] arr,int k,int m) {
        int[] count=new int[32];
        //统计arr数组中所有数上1出现的位置的次数
        for(int num:arr) {
            for (int i = 0; i < 32; i++) {//每个数的32位都需要统计
                count[i]+=(num>>i)&1;
            }
        }
        int ans=0;
        for (int i = 0; i < 32; i++) {
            count[i]%=m;
            if(count[i]!=0) {
                ans|=1<<i;//将对应位为1的替换到ans上
            }
        }
        return ans;
    }
    public static int test(int[] arr,int k,int m) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int num:arr) {
            if(map.containsKey(num)) {
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        int ans=0;
        for (int num: map.keySet()) {
            if(map.get(num)==k) {
                ans=num;
                break;
            }
        }
        return ans;
    }

    public static int[] randomArray(int maxKinds,int maxValue,int k,int m) {
        int kTimeNum=randomNumber(maxValue);
        //真命天子出现次数
        int times=k;
        //最少得出现两种数
        int numKinds=(int)(Math.random()*maxKinds)+2;
        //生成随机数组,长度由每种数的出现次数决定
        int[] arr=new int[times+(numKinds-1)*m];
        int index=0;
        for (int i = 0; i < times; i++) {
            arr[index++]=kTimeNum;
        }
        numKinds--;
        HashSet<Integer> set=new HashSet<>();
        set.add(kTimeNum);
        while (numKinds!=0) {
            int curNum=0;
            do {
                curNum=randomNumber(maxValue);
            }while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++]=curNum;
            }
        }
        //arr填好了然后打乱
        for (int i = 0; i < arr.length; i++) {
            int j=(int) (Math.random()*arr.length);//0~N-1
            int tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
        return arr;
    }

    //随机生成出现K次的数
    //范围[-maxValue,maxValue]
    public static int randomNumber(int maxValue) {
        return (int)(Math.random()*(maxValue+1))-(int)(Math.random()*(maxValue+1));
    }
    public static void main(String[] args) {
        int maxKinds=10;//最多出现maxKinds种数
        int maxValue=50;//最大值
        int testTimes=10000;//测试次数
        int maxKM=9;//k、m的最大值
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int a=(int)(Math.random()*maxKM)+1;//1~9
            int b=(int)(Math.random()*maxKM)+1;//1~9
            int k=Math.min(a,b);
            int m=Math.max(a,b);
            //k<m
            if(k==m) {
                m++;
            }
            int[] arr=randomArray(maxKinds,maxValue,k,m);
            int ans1=km(arr,k,m);
            int ans2=test(arr,k,m);
            if(ans1!=ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束!");
    }
}
