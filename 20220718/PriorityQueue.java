// JDK中PriorityQueue的实现： 
public class PriorityQueue<E> extends AbstractQueue<E> 
implements java.io.Serializable {
// ... 
// 默认容量 
private static fifinal int DEFAULT_INITIAL_CAPACITY = 11; 
// 内部定义的比较器对象，用来接收用户实例化PriorityQueue对象时提供的比较器对象 
private fifinal Comparator<? super E> comparator; 
// 用户如果没有提供比较器对象，使用默认的内部比较，将comparator置为null 
public PriorityQueue() { 
this(DEFAULT_INITIAL_CAPACITY, null); 
}
// 如果用户提供了比较器，采用用户提供的比较器进行比较 
public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) { 
// Note: This restriction of at least one is not actually needed, 
// but continues for 1.5 compatibility 
if (initialCapacity < 1) 
throw new IllegalArgumentException(); 
this.queue = new Object[initialCapacity]; 
this.comparator = comparator; 
}
// ... 
// 向上调整： 
// 如果用户没有提供比较器对象，采用Comparable进行比较 
// 否则使用用户提供的比较器对象进行比较 
private void siftUp(int k, E x) { 
if (comparator != null) 
siftUpUsingComparator(k, x); 
else
siftUpComparable(k, x); 
}
// 使用Comparable 
@SuppressWarnings("unchecked") 
private void siftUpComparable(int k, E x) { 
Comparable<? super E> key = (Comparable<? super E>) x; 
while (k > 0) { 
int parent = (k - 1) >>> 1; 
Object e = queue[parent]; 
if (key.compareTo((E) e) >= 0) 
break; 
queue[k] = e; 
k = parent; 
} 
queue[k] = key; 
}
// 使用用户提供的比较器对象进行比较 
@SuppressWarnings("unchecked") 
private void siftUpUsingComparator(int k, E x) { 
while (k > 0) { 
int parent = (k - 1) >>> 1; 
Object e = queue[parent]; 
if (comparator.compare(x, (E) e) >= 0) 
break; 
queue[k] = e; 
k = parent; 
} 
queue[k] = x; 
}
}
