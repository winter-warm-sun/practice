package action.group;

import action.Action;

import javax.swing.*;

public class GroupCount extends Action {
    public GroupCount() {
        super();
        JLabel jLabel=new JLabel("count");
        add(jLabel);
    }

    @Override
    public void init() {

    }
}
