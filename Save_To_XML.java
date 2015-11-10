//Some code taken from an in-class ToDoApp project
//converted to save music notes given various positions.
//This is what I came up with, there may be a simpler
//means of saving these notes & their positions to
//an XML file, so this code may be subject to change.
//Implementation into the project itself is to be
//done at a later time.


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.sql.*;

class Save {

    private String note; //title-->note
    private String posx; //desc-->posx
    
    public Save(String note, String posx) {
        this.note = note;
        this.posx = posx;
    }
    
    public void setNote(String note) { //setTitle-->setNote
        this.note = note;
    }
    
    public void setPosX(String posx) { //setDesc-->setPosX
        this.posx = posx;
    }
    
    public String getNote() { //getTitle-->getNote
        return note;
    }
    
    public String getPosx() { //getDesc-->getPosX
        return posx;
    }
    
    public String toString() {
        return note;
    }
    
    
}

//ToDo --> Save
//ToDoApp --> Save_To_XML
public class Save_To_XML extends JFrame {
     
    public Save_To_XML() {

        super();
        
        Vector<Save> myListModel = new Vector<Save>();
        JList<Save> myList = new JList<Save>(myListModel);
        try {
                SAXBuilder builder = new SAXBuilder();
                Document xmlDoc = builder.build(new File("saeje.xml")); //name of the file?

                Element notesElement = xmlDoc.getRootElement(); //todosElement-->notesElement

                for (Object notesObj: notesElement.getChildren()) { //todoObj-->notesObj
                        Element notes = (Element) notesObj; //todo-->notes

                        String posx = notes.getAttributeValue("Position");
                        String note = notes.getAttributeValue("Note");

                        myListModel.add(new Save(note, posx));

                }

        } catch (Exception ex) {}
        this.setLayout(new BorderLayout());
                
                

        JPanel top = new JPanel();

        this.add(top, BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        this.add(bottom, BorderLayout.CENTER);

        top.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        top.add(new JLabel("Save Note", JLabel.RIGHT), c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextField notesField = new JTextField(""); //todoField-->notesField
        top.add(notesField, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        top.add(new JLabel("Position", JLabel.RIGHT), c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextArea posxField = new JTextArea("",10,40); //descField-->posxField
        top.add(posxField, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        top.add(new JButton() {
            {
                this.setText("Save");
                this.addActionListener(new ActionListener() {

                    //trigger every beat in a meter?
                    public void actionPerformed(ActionEvent arg0) {
                        if (notesField.getText().isEmpty()){
                            System.out.println("Nothing's entered!");
                        }
                        else{
                            try{
    
                                SAXBuilder builder = new SAXBuilder();
                                Document xmlDoc = builder.build(new File("saeje.xml"));

                                Element notesElement = xmlDoc.getRootElement();

                                Element Save = new Element("Save");
                                Save.setAttribute("Note",notesField.getText());
                                Save.setAttribute("Position",posxField.getText());

                                notesElement.addContent(Save);

                                XMLOutputter xmlOutput = new XMLOutputter();
                                    xmlOutput.setFormat(Format.getPrettyFormat());
                                    xmlOutput.output(xmlDoc, new FileWriter("saeje.xml"));

                                System.out.println("Added Save: '" + notesField.getText() + "'");

                                }catch (Exception ex) {}
                        
                        }
                        myListModel.add(new Save(notesField.getText(),posxField.getText()));
                        myList.setListData(myListModel);
                        updateUI();
                    }
                });
            }
        }, c);
        
        
        bottom.setLayout(new BorderLayout());
        JScrollPane scroller = new JScrollPane(myList);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bottom.add(scroller,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Save_To_XML Save = new Save_To_XML();
        Save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Save.pack();
        Save.setVisible(true);
    }

}
