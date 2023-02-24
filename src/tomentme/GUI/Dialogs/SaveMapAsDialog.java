package tomentme.GUI.Dialogs;
import javax.swing.*;

import tomentme.TomentEditor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class SaveMapAsDialog extends JDialog
{
    private JTextField mapIDValue;

    public SaveMapAsDialog(JFrame frame, String title, boolean modal)
    {
        super(frame, title, modal);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Initialize();
        
        this.setLocationRelativeTo(frame);
        this.setSize(300, 135);
        this.setVisible(true);
    }

    public void Initialize()
    {
        JPanel content = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel idLabel = new JLabel("Save as:");
        content.add(idLabel);

        JPanel idPanel = new JPanel();
        mapIDValue = new JTextField(20);
        mapIDValue.setText("");
        idPanel.add(mapIDValue);
        content.add(idPanel);
        
        JButton openBtn = new JButton("Save");
        content.add(openBtn);
        openBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                SaveAndDispose();
            }
        });
        this.add(content);
    }

    public void SaveAndDispose()
    {
        TomentEditor.instance.currentMap.ID = mapIDValue.getText();
        TomentEditor.instance.SaveMap();
        this.dispose();
    }
}
