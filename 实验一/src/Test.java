public class Test {
    public static void main(String[] args) {
        MyLinkedList list=new MyLinkedList();
        Node teacher1=list.create("123","张三","男","教授",
                "博士","教师","同济大学","软件工程");
        Node teacher2=list.create("124","李四","男","院长",
                "硕士","教师","中南大学","计算机");
        list.add(teacher1);
        list.print();
        System.out.println("==============");
        list.add(teacher2);
        list.print();
        System.out.println("==============");
        list.select("123");
        System.out.println("==============");
        list.delete("123");
        list.print();
        System.out.println("==============");
    }
}
