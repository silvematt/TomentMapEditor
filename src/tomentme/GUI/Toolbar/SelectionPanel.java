package tomentme.GUI.Toolbar;

import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class SelectionPanel 
{
    public SelectionPanel(JPanel toolSections)
    {
        JPanel selectedFacePanel = new JPanel();
        selectedFacePanel.setLayout(new FlowLayout());
        selectedFacePanel.setBackground(Color.YELLOW);

        JPanel selectedFaceSelection = new JPanel();
        selectedFaceSelection.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel sfLabel = new JLabel("Selected Info: ");
        selectedFaceSelection.add(sfLabel);
        toolSections.add(selectedFaceSelection);

        BufferedImage myPicture;
        try 
        {
            myPicture = ImageIO.read(new File("C:/Users/silve/Desktop/wall1.bmp"));
            ImageIcon img = new ImageIcon(myPicture);
            Image newimg = img.getImage().getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            JLabel picLabel = new JLabel(new ImageIcon(newimg));
            picLabel.setPreferredSize(new Dimension(64,64));
            selectedFacePanel.add(picLabel, BorderLayout.LINE_START);

        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        JPanel sbtnPnl = new JPanel();
        JButton sbtn = new JButton("Select");
        sbtn.setBorder(null);
        sbtn.setPreferredSize(new Dimension(70,15));
        sbtnPnl.add(sbtn);

        selectedFacePanel.add(sbtnPnl);

        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(0, 1));

        JButton TOPBtn = new JButton("TOP");
        JButton BOTTOMBtn = new JButton("BOTTOM");
        JButton LEFTBtn = new JButton("LEFT");
        JButton RIGHTBtn = new JButton("RIGHT");
        JButton FORWARDBtn = new JButton("FORWARD");
        JButton BACKBtn = new JButton("BACK");

        TOPBtn.setPreferredSize(new Dimension(60, 15));
        BOTTOMBtn.setPreferredSize(new Dimension(60, 15));
        LEFTBtn.setPreferredSize(new Dimension(60, 15));
        RIGHTBtn.setPreferredSize(new Dimension(60, 15));
        FORWARDBtn.setPreferredSize(new Dimension(60, 15));
        BACKBtn.setPreferredSize(new Dimension(60, 15));

        TOPBtn.setBorder(null);
        BOTTOMBtn.setBorder(null);
        LEFTBtn.setBorder(null);
        RIGHTBtn.setBorder(null);
        FORWARDBtn.setBorder(null);
        BACKBtn.setBorder(null);

        TOPBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        BOTTOMBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        LEFTBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        RIGHTBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        FORWARDBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        BACKBtn.setFont(new Font("Arial", Font.PLAIN, 10));


        pnl.add(TOPBtn);
        pnl.add(BOTTOMBtn);
        pnl.add(LEFTBtn);
        pnl.add(RIGHTBtn);
        pnl.add(FORWARDBtn);
        pnl.add(BACKBtn);


        selectedFacePanel.add(pnl, BorderLayout.LINE_END);

        toolSections.add(selectedFacePanel);
    }
}
