package tomentme.GUI;

import java.awt.event.ActionEvent;

import javax.swing.*;

import tomentme.TomentEditor;
import tomentme.GUI.Dialogs.MapSettings;
import tomentme.GUI.Dialogs.OpenMapDialog;

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

    public static class NewMapAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TomentEditor.instance.NewMap();
        }
    }

    public static class OpenMapAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            OpenMapDialog settings = new OpenMapDialog(GUI.GetFrame(), "Open Map", true);
        }
    }
}
