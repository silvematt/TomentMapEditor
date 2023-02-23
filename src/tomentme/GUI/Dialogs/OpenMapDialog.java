package tomentme.GUI.Dialogs;
import javax.swing.*;

import tomentme.TomentEditor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class OpenMapDialog extends JDialog
{
    private JTextField mapIDValue;

    public OpenMapDialog(JFrame frame, String title, boolean modal)
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
        JLabel idLabel = new JLabel("Insert file name located in \"Data/maps/\"");
        content.add(idLabel);

        JPanel idPanel = new JPanel();
        mapIDValue = new JTextField(20);
        mapIDValue.setText("");
        idPanel.add(mapIDValue);
        content.add(idPanel);
        
        JButton openBtn = new JButton("Open");
        content.add(openBtn);
        openBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                OpenAndDispose();
            }
        });
        this.add(content);
    }

    public void OpenAndDispose()
    {
        TomentEditor.instance.OpenMap(mapIDValue.getText());
        this.dispose();
    }
}
