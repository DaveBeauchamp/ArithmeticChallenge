/**
 * <h1>Arithmetic Challenge</h1>
 * <h3>Student Client</h3>
 * Arithmetic Challenge is a maths test program that has student
 * this is the student client
 */
package arithmeticchallenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

/**
 * @author David Beauchamp
 * @version 1.0
 * @since 2018/04/26
 */
public class Student extends JFrame implements ActionListener, FocusListener
{
    private JTextField txtQuestion, txtAnswer, txtFirstNum, txtSecondNum, txtComboBox;
    private JButton btnExit, btnSubmit;
    private JLabel lblTitle, lblEnterQuestion, lblQuestion, lblAnswer, lblTitleColourBar, lblFirstNumber, lblOperator, lblSecondNumber;
    
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread2 client2 = null;
    private String serverName = "localhost";
    private int serverPort = 4444;
    String[] unpack = new String[4];
    public String msg = "";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Student arithemticChallengeApp = new Student();
        arithemticChallengeApp.RunStudentApp();
    }
    // Done
    // <editor-fold defaultstate="collapsed" desc="Run and Screen">
    /**
     * This method is the basic run function that allows the for the 
     * application to be closed, it also has the frame attributes, 
     * calls network methods and GUI. 
     */
    public void RunStudentApp()
    {

        setBounds(100, 100, 265, 305);
        setTitle("Arithmetic Challenge");

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        showStudentGUI();

        setResizable(false);
        setVisible(true);
        connect(serverName, serverPort);
        
        //CHAT RELATED ---------------------------
        getParameters(); 
        //----------------------------------------
    }
    
    /**
     * This method calls all the GUI components 
     */
    private void showStudentGUI()
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        getContentPane().setBackground(Color.white);
        showTextFields(springLayout);
        showButtons(springLayout);
        showLabels(springLayout);
        QuestionPanel(springLayout);
    }
    // </editor-fold>
    // Done
    // <editor-fold defaultstate="collapsed" desc="Screen Items">
    /**
     * This method is used for getting and positioning labels. 
     * It uses Spring layout
     * @param layout 
     */
    private void showLabels(SpringLayout layout)
    {
        lblTitle = LibraryItems.LocateAJLabel(this, layout, "Student", 45, 10, true, 50, 255, 0, 255, false, 255, 255, 255);
        lblEnterQuestion = LibraryItems.LocateAJLabel(this, layout, "Enter your answer and click Submit.", 10, 90, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblFirstNumber = LibraryItems.LocateAJLabel(this, layout, "First Number:", 10, 120, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblOperator = LibraryItems.LocateAJLabel(this, layout, "Operator:", 10, 143, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblSecondNumber = LibraryItems.LocateAJLabel(this, layout, "Second Number:", 10, 165, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblAnswer = LibraryItems.LocateAJLabel(this, layout, "Answer:", 10, 195, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblTitleColourBar = LibraryItems.LocateAJLabel(this, layout, "                   ", 0, 10, true, 50, 0, 0, 140, true, 0, 0, 0);
    }
    
    /**
     * This method is used for getting and positioning TextFields. 
     * It uses Spring layout
     * @param layout 
     */
    private void showTextFields(SpringLayout layout)
    {
        txtFirstNum = LibraryItems.LocateAJTextField(this, this, layout, 10, 130, 120);
        txtFirstNum.setEditable(false); 
        txtComboBox = LibraryItems.LocateAJTextField(this, this, layout, 10, 130, 143);
        txtComboBox.setEditable(false);
        txtSecondNum = LibraryItems.LocateAJTextField(this, this, layout, 10, 130, 165);
        txtSecondNum.setEditable(false);
        txtAnswer = LibraryItems.LocateAJTextField(this, this, layout, 10, 130, 195);
    }
    
    /**
     * This method is used for getting and positioning Buttons. 
     * It uses Spring layout
     * @param layout 
     */
    private void showButtons(SpringLayout layout)
    {
        btnExit = LibraryItems.LocateAJButton(this, this, layout, "Exit", 175, 250, 80, 20);
        btnSubmit = LibraryItems.LocateAJButton(this, this, layout, "Submit", 165, 220, 80, 20);
    }

    // </editor-fold>
    // Done
    // <editor-fold defaultstate="collapsed" desc="Panels and Tables">
    /**
     * This method is used for getting and positioning the Question Panel. 
     * It uses Spring layout
     * @param myPanelLayout 
     */
    public void QuestionPanel(SpringLayout myPanelLayout)
    {
        // Create a panel to hold all other components
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        add(questionPanel);

        // Add the table to a scrolling pane, size and locate
        questionPanel.setPreferredSize(new Dimension(250, 156));
        myPanelLayout.putConstraint(SpringLayout.WEST, questionPanel, 5, SpringLayout.WEST, this);
        myPanelLayout.putConstraint(SpringLayout.NORTH, questionPanel, 90, SpringLayout.NORTH, this);
        Border questionBorder = BorderFactory.createLineBorder(new Color(0, 0, 140), 3);
        questionPanel.setBorder(questionBorder);
        questionPanel.setBackground(new Color(176, 224, 230));
    }

    // </editor-fold>
    // Done
    // <editor-fold defaultstate="collapsed" desc="Buttons">
    @Override
    public void actionPerformed(ActionEvent e)
    {
        /**
         * this button sends the students answer back to the instructor
         * and clears the text fields
         */
        if (e.getSource() == btnSubmit)
        {
           send();
           txtFirstNum.setText("");
           txtComboBox.setText("");
           txtSecondNum.setText("");
           txtAnswer.setText("");
        }
        
        /**
         * This button exists the program
         */
        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }
    }
    // </editor-fold>

    /**
     * This focus lost event is purely for catching if users enter nonsense 
     * values in the answer field
     * @param e 
     */
    @Override
    public void focusLost(FocusEvent e)
    {
        if (e.getSource() == txtAnswer)
        {
            if(txtAnswer.getText().matches("[0-9]+") || txtAnswer.getText().equals(""))
            {
                // do nothing
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please enter numbers only!");
            }
        }
    }
    
    //
    // <editor-fold defaultstate="collapsed" desc="Networking">
    /**
     * this method gets the connection to the server
     * @param serverName
     * @param serverPort 
     */
    public void connect(String serverName, int serverPort)
    {
        println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(serverName, serverPort);
            println("Connected: " + socket);
            open();
        }
        catch (UnknownHostException uhe)
        {
            println("Host unknown: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            println("Unexpected exception: " + ioe.getMessage());
        }
    }
    
    /**
     * this method sends question and student answer value to the Instructor
     * if the answer given by the student is the same as the answer sent by the
     * instructor a pop up saying correct will appear, though if the answer
     * does not match the pop up will say incorrect.
     */
    private void send() // call this in the submit button
    {
        try
            
        {
            println(msg);
            String[] unpack = msg.split(",");
            
            String unpackFixed = unpack[0].substring(unpack[0].indexOf(":")+2);
            unpackFixed.trim();

            if (unpack[3].equals(txtAnswer.getText())) // correct answer
            {   
                streamOut.writeUTF("Correct answer, from, " + GetThisClientSocketNumber() +","+ unpack[3] );
                JOptionPane.showMessageDialog(null, "Correct!");
                
            }
            else    // incorrect answer
            {
                String sendToInstructor = unpackFixed +","+ txtComboBox.getText() +","+ txtSecondNum.getText() +","+ txtAnswer.getText();
                streamOut.writeUTF(sendToInstructor);
                JOptionPane.showMessageDialog(null, "Incorrect");
            }
            
            streamOut.flush();
            
        }
        catch (IOException ioe)
        {
            println("Sending error: " + ioe.getMessage());
            close();
        }
    
    }
   
    /**
     * this unpacks the value sent by the server, though it will check if 
     * the sender id is itself as the server sends everything to all devices 
     * that are connected. if the sender is itself do nothing. 
     * the values that were sent from the instructor are put in the
     * number and operator text fields for the student to answer 
     * @param msg 
     */
    public void handle(String msg)
    {
        this.msg = msg; // this is just to store globally
        
        String unpackUtilsAfter = StringUtils.substringAfter(msg, " ");
        println(unpackUtilsAfter);
        
        String unpackUtilsBefore = StringUtils.substringBefore(msg, ":");
        println(unpackUtilsBefore);
       
        //println("Instruct " + getInstructorSocketHeader);

        if (unpackUtilsBefore.equals(GetThisClientSocketNumber()))
        {
            //println("Duplicate"); // this is to do nothing as the "Dulicate" response was only to test that was working.
        } 
        else
        {
            if (msg.equals(".bye"))
            {
                println("Good bye. Press EXIT button to exit ...");
                close();
            } 
            else
            {
                String[] unpack = msg.split(",");
                String unpackFixed = unpack[0].substring(unpack[0].indexOf(":") + 2);
                unpackFixed.trim();

                txtFirstNum.setText(unpackFixed);
                txtComboBox.setText(unpack[1]);
                txtSecondNum.setText(unpack[2]);
            }
        }
    }
    
    /**
     * this method is used to extract the socket number for this user
     * @return String value of the socket number
     */
    public String GetThisClientSocketNumber()
    {
        String getMySocket = socket.toString();
        String mySocket = getMySocket.substring(getMySocket.indexOf("localport")+10);
        mySocket.trim();
        mySocket = mySocket.split("]")[0];
        return mySocket;
    }
    
    /**
     * this is used to open a connection to the server
     */
    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client2 = new ChatClientThread2(this, socket);
        }
        catch (IOException ioe)
        {
            println("Error opening output stream: " + ioe);
        }
    }
    
    /**
     * this is used to close the connection to the server
     */
    public void close()
    {
        try
        {
            if (streamOut != null)
            {
                streamOut.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException ioe)
        {
            println("Error closing ...");
        }
        client2.close();
        client2.stop();
    }
    
    /**
     * this method prints the input to the console 
     * @param msg 
     */
    void println(String msg)
    {
        //display.appendText(msg + "\n");
//        lblMessage.setText(msg);
        System.out.println(msg);
    }
    
    /**
     * this method has the parameters for connection to the server
     */
    public void getParameters()
    {
//        serverName = getParameter("host");
//        serverPort = Integer.parseInt(getParameter("port"));
        
        serverName = "localhost";
        serverPort = 4444;        
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Unused Overrides">
    @Override
    public void focusGained(FocusEvent e)   // this not used
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>
}
