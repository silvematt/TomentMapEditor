package tomentme.GUI.Dialogs;
import javax.swing.*;

import tomentme.TomentEditor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

/*
 * Represents the DialogBox that allows to open a map
 */
public class OpenMapDialog extends JDialog
{
    // Input field
    private JTextField mapIDValue;

    // Constructor
    public OpenMapDialog(JFrame frame, String title, boolean modal)
    {
        super(frame, title, modal);
        
        // Set layout
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Initialize();
        
        // Set final properties
        this.setLocationRelativeTo(frame);
        this.setSize(300, 135);
        this.setVisible(true);
    }

    // Initializes the panel by adding all the content
    public void Initialize()
    {
        JPanel content = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel idLabel = new JLabel("Insert file name located in \"Data/maps/\"");
        content.add(idLabel);

        // Id panel and value
        JPanel idPanel = new JPanel();
        mapIDValue = new JTextField(20);
        mapIDValue.setText("");
        idPanel.add(mapIDValue);
        content.add(idPanel);
        
        // Open button
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

    // Opens the map in the editor
    public void OpenAndDispose()
    {
        TomentEditor.instance.OpenMap(mapIDValue.getText());
        this.dispose();
    }
}
