import java.lang.reflect.Array;
import java.util.Arrays;

class MyArray<T extends Number> {
  public T[] array;
  public MyArray() {
  }
  public MyArray(Class<T>clazz,int capacity) {
      array=(T[])Array.newInstance(clazz,capacity);
  }
  public T getPos(int pos) {
      return this.array[pos];
  }
  public void setVal(int pos,T val) {
      this.array[pos] = val;
  }
  public T[] getArray() {
      return array;
  }
}
class A<T extends Comparable<T>> {
    public T findMaxVal(T[] array) {
        T maxVal=array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i].compareTo(maxVal)>0) {
                maxVal=array[i];
            }
        }
        return maxVal;
    }
}
class A1 {
    public <T extends Comparable<T>>T findMaxVal(T[] array) {
        T maxVal=array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i].compareTo(maxVal)>0) {
                maxVal=array[i];
            }
        }
        return maxVal;
    }
}
class A2 {
    public static <T extends Comparable<T>>T findMaxVal(T[] array) {
        T maxVal=array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i].compareTo(maxVal)>0) {
                maxVal=array[i];
            }
        }
        return maxVal;
    }
}
public class TestDemo {
    public static void main(String[] args) {
        Integer a1=100;
        Integer b1=100;
        System.out.println(a1==b1);
        Integer a2=200;
        Integer b2=200;
        System.out.println(a2==b2);
    }
    public static void main3(String[] args) {
        int a=10;
        Integer integer=a;//自动装箱  ->底层调用的还是Integer.valueOf
        Integer integer2=Integer.valueOf(a);//显示装箱
        Integer integer3=new Integer(a);//显示装箱

        int val=integer;//自动拆箱
        int val2=integer.intValue();//显示拆箱
        double val3=integer.doubleValue();//显示拆箱
        System.out.println(integer);
        System.out.println(integer2);
        System.out.println(integer3);
        System.out.println(val);
        System.out.println(val2);
        System.out.println(val3);
    }
    public static void main2(String[] args) {
        A<Integer> a=new A<>();
        Integer[] array={20,30,89,50};
        int maxVal=a.findMaxVal(array);
        System.out.println(maxVal);
    }
    public static void main1(String[] args) {
        MyArray<Integer> myArray1 = new MyArray<>();
        System.out.println(myArray1);

    }
}