package action.group;

import action.Action;

import javax.swing.*;

public class GroupExamine extends Action {
    public GroupExamine() {
        super();
        JLabel jLabel=new JLabel("examine");
        add(jLabel);
    }

    @Override
    public void init() {

    }
}
