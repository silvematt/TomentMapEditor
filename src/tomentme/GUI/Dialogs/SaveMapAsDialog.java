package tomentme.GUI.Dialogs;
import javax.swing.*;

import tomentme.TomentEditor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

/*
 * Represents the DialogBox that allows to save a map with a name
 */
public class SaveMapAsDialog extends JDialog
{
    // Input field
    private JTextField mapIDValue;

    // Constructor
    public SaveMapAsDialog(JFrame frame, String title, boolean modal)
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
        JLabel idLabel = new JLabel("Save as:");
        content.add(idLabel);

        // Id panel and value
        JPanel idPanel = new JPanel();
        mapIDValue = new JTextField(20);
        mapIDValue.setText("");
        idPanel.add(mapIDValue);
        content.add(idPanel);
        
        // Save button
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

    // Saves the map and closes the dialog
    public void SaveAndDispose()
    {
        TomentEditor.instance.currentMap.ID = mapIDValue.getText();
        TomentEditor.instance.SaveMap();
        this.dispose();
    }
}
