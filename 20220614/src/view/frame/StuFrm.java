package frame;

import action.student.*;

public class StuFrm extends Frame{
    public StuFrm() {
        super();
        setTitle("软件学院德育学分评分系统——学生界面");
        seeAction();
        entryAction();
        checkAction();
        consultAction();
        modifyAction();
        add(tab);
    }

    //查看标准
    public void seeAction() {
        StudentSee studentSee=new StudentSee();
        tab.add("查看标准",studentSee);
    }
    //自评录入
    public void entryAction() {
        StudentEntry studentEntry=new StudentEntry();
        tab.add("自评录入",studentEntry);
    }
    //查看结果
    public void checkAction() {
        StudentCheck studentCheck=new StudentCheck();
        tab.add("查看结果",studentCheck);
    }
    //疑惑咨询
    public void consultAction() {
        StudentConsult studentConsult=new StudentConsult();
        tab.add("疑惑咨询",studentConsult);
    }
    //信息修改
    public void modifyAction() {
        StudentModify studentModify=new StudentModify();
        tab.add("信息修改",studentModify);
    }
}
