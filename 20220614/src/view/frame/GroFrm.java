package frame;

import action.group.GroupAnswer;
import action.group.GroupCount;
import action.group.GroupExamine;

public class GroFrm extends Frame{
    public GroFrm() {
        super();
        setTitle("软件学院德育学分评分系统——评定组界面");
        examineAction();
        countAction();
        answerAction();
        add(tab);
    }
    //审核
    public void examineAction() {
        GroupExamine groupExamine=new GroupExamine();
        tab.add("审核",groupExamine);
    }
    //统计
    public void countAction() {
        GroupCount groupCount=new GroupCount();
        tab.add("统计",groupCount);
    }
    //答疑
    public void answerAction() {
        GroupAnswer groupAnswer=new GroupAnswer();
        tab.add("答疑",groupAnswer);
    }
}
