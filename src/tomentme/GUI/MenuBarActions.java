package tomentme.GUI;

import java.awt.event.ActionEvent;

import javax.swing.*;

public class MenuBarActions 
{
    public static class ExitAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO Auto-generated method stub
            System.exit(0);
        }
    }
}
