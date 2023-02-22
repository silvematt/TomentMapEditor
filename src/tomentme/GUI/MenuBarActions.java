package tomentme.GUI;

import java.awt.event.ActionEvent;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.GUI.Dialogs.MapSettings;

public class MenuBarActions 
{
    public static class ExitAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }

    public static class OpenSettingsAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            MapSettings settings = new MapSettings(GUI.GetFrame(), "Settings", true);
        }
    }
}
