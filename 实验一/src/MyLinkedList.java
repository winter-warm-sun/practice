class Information {
    private String number;
    private String name;
    private String sex;
    private String position;
    private String degree;
    private String post;
    private String graSchool;
    private String career;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    @Override
    public String toString() {
        return "Information{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", position='" + position + '\'' +
                ", degree='" + degree + '\'' +
                ", post='" + post + '\'' +
                ", graSchool='" + graSchool + '\'' +
                ", career='" + career + '\'' +
                '}';
    }
}
class Node {
    public Node next;
    public Information data;
    public Node(Information data) {
        this.data = data;
    }
}
public class MyLinkedList {
    public Node head;

    /**
     * 根据具体信息实例化教师
     * @param number
     * @param name
     * @param sex
     * @param position
     * @param degree
     * @param post
     * @param graSchool
     * @param career
     */
    public Node create(String number,String name,String sex, String position,
                              String degree,String post,String graSchool,String career) {

        Information data=new Information();
        Node node=new Node(data);
        data.setNumber(number);
        data.setName(name);
        data.setSex(sex);
        data.setPosition(position);
        data.setDegree(degree);
        data.setPost(post);
        data.setGraSchool(graSchool);
        data.setCareer(career);
        return node;
    }

    /**
     * 遍历链表打印教师信息
     */
    public void print() {
        Node cur=head;
        while (cur!=null) {
            System.out.println(cur.data);
            cur=cur.next;
        }
    }

    /**
     * 输入工号查询信息
     * @param number  工号
     */
    public void select(String number) {
        if(this.head==null) {
            System.out.println("信息表为空！");
        }
        Node cur=this.head;
        while (cur!=null) {
            if(cur.data.getNumber().equals(number)) {
                System.out.println(cur.data);
                return;
            }
            cur=cur.next;
        }
        System.out.println("不存在该教师");
    }

    /**
     * 增添教师信息
     */
    public void add(Node node) {
        if(this.head==null) {
            this.head=node;
            return;
        }
        node.next=this.head;
        this.head=node;
    }

    /**
     * 输入教师工号删除该教师信息
     * @param number  教师工号
     */
    public void delete(String number) {
        //空链表直接返回
        if(this.head==null) {
            return;
        }
        //如果是第一个节点，单独考虑直接删除即可
        if(this.head.data.getNumber().equals(number)) {
            this.head=this.head.next;
            return;
        }

        //其他情况需要找到要删除节点的前驱
        Node ahead=searchDeleteAhead(number);
        if(ahead==null) {
            System.out.println("不存在该教师");
        }else {
            ahead.next=ahead.next.next;
        }
    }

    /**
     * 私有方法，用于查找要删除节点的前一个节点
     * @param number
     * @return
     */
    private Node searchDeleteAhead(String number) {
        Node cur=this.head;
        while (cur.next!=null) {
            if(cur.next.data.getNumber().equals(number)) {
                return cur;
            }else {
                cur=cur.next;
            }
        }
        return null;
    }
}
