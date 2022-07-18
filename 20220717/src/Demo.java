import java.util.Comparator;
import java.util.PriorityQueue;

class Student {
    public int age;

    public Student(int age) {
        this.age = age;
    }
}
class AgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o2.age-o1.age;
    }
}
public class Demo {
    public static void main(String[] args) {
        Student student1=new Student(5);
        Student student2=new Student(15);
        AgeComparator ageComparator=new AgeComparator();
        PriorityQueue<Student> priorityQueue=new PriorityQueue<>(ageComparator);
        priorityQueue.offer(student1);
        priorityQueue.offer(student2);
    }
}
