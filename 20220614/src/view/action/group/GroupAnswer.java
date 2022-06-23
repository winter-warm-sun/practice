package action.group;

import action.Action;

import javax.swing.*;

public class GroupAnswer extends Action {
    public GroupAnswer() {
        super();
        JLabel jLabel=new JLabel("answer");
        add(jLabel);
    }

    @Override
    public void init() {

    }
}
