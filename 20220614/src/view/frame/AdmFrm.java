package frame;


import action.admin.AdminMangeStudent;
import action.admin.AdminMangeTeacher;

public class AdmFrm extends Frame {
    public AdmFrm(){
        super();
        setTitle("软件学院德育学分评分系统——管理员界面");
        mangeTeacherAction();
        mangeStudentAction();
        add(tab);
    }
    //教师信息管理
    public void mangeTeacherAction() {
        AdminMangeTeacher adminMangeTeacher=new AdminMangeTeacher();
        tab.add("教师信息管理",adminMangeTeacher);
    }

    //学生信息管理
    public void mangeStudentAction() {
        AdminMangeStudent adminMangeStudent=new AdminMangeStudent();
        tab.add("学生信息管理",adminMangeStudent);
    }
}
