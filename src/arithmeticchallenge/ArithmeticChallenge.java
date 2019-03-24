/**
 * <h1>Arithmetic Challenge</h1>
 * <h3>Instructor Client</h3>
 * Arithmetic Challenge is a maths test program that has student
 * this is the instructor client
 */
package arithmeticchallenge;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import javax.swing.JPanel;
import java.net.*;
import java.io.*;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

        



/**
 * @author David Beauchamp
 * @version 1.0
 * @since 2018/04/26
 */
public class ArithmeticChallenge extends JFrame implements ActionListener, FocusListener
{
    private JTextField txtFirstNum, txtSecondNum, txtAnswer, txtStudentQuestion, txtStudentAnswer;
    private JTextArea txaIncorrectAnswers, txaQuestionsAsked;
    private JButton btnOne, btnTwo, btnThree, btnExit, btnDisplay, btnDisplayPre, btnSavePre, btnDisplayOrder, btnSaveOrder, btnDisplayPost, btnSavePost, btnSend, btnStudentSubmit, btnStudentExit;
    private JLabel lblTitle, lblEnterQuestion, lblFirstNumber, lblOperator, lblSecondNumber, lblAnswer, lblIncorrect, lblQuestionsAsked, lblPreOrder, lblInOrder, lblPostOrder, lblSort, lblStudentQuestion, lblStudentAnswer, lblTitleColourBar, lblPreOrderColorBar, lblInOrderColorBar, lblPostOrderColorBar, lblAnsweredIncorrectColorBar, lblAnswersAskedColorBar, lblQuestionBG, lblQuestionsBG, lblBg;
    private JComboBox cboOperator;
    
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread1 client = null;
    private String serverName = "localhost";
    private int serverPort = 4444;

    JFrame drawTree = new JFrame();
    Graphics gee;
    JTable table;
    MyModel ArithmeticModel;
    ArrayList<Object[]> dataValues = new ArrayList();
    DList MyDList = new DList();
    BinaryTree MyBtNode = new BinaryTree();
    BinaryTree PaintNode = new BinaryTree();
    BtNode paint;
    
    int numOfQuestions = 100; // value plucked out of the air
    int currentQuestion = 0;
    Answers MyQuestions[] = new Answers[numOfQuestions];
    String[] unpack = new String[4];
    public String msg = "";
    public String answerStorage = ""; // this is meant to only hold one value for checking that the returned answer matches the correct answer
    
    FileWriter writerPre;
    FileWriter writerPost;
    FileWriter writerIn;
    
    String path = "E:\\Tafe\\"; 
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArithmeticChallenge arithemticChallengeApp = new ArithmeticChallenge();
        arithemticChallengeApp.RunInstructorApp();
    }

    // third party library. could use String.Utils
    
    // Sorry for the folds, they made my life easier
    // Done
    // <editor-fold defaultstate="collapsed" desc="Run and Screen">
    /**
     * This method is the basic run function that allows the for the 
     * application to be closed, it also has the frame attributes, 
     * calls network methods and GUI. 
     */
    private void RunInstructorApp()
    {

        setBounds(10, 10, 650, 750);
        setTitle("Arithmetic Challenge");

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        showInstructorGUI();

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
    private void showInstructorGUI()
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        getContentPane().setBackground(Color.white);
        showTextFields(springLayout);
        showButtons(springLayout);
        showLabels(springLayout);
        showComboBoxs(springLayout);
        showTextAreas(springLayout);
        QuestionPanel(springLayout);
        WordAssociationTable(springLayout);

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
        lblTitle = LibraryItems.LocateAJLabel(this, layout, "Instructor", 200, 10, true, 50, 255, 0, 255, false, 255, 255, 255);
        lblEnterQuestion = LibraryItems.LocateAJLabel(this, layout, "Enter question, then click Send.", 10, 90, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblFirstNumber = LibraryItems.LocateAJLabel(this, layout, "First Number:", 10, 120, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblOperator = LibraryItems.LocateAJLabel(this, layout, "Operator:", 10, 150, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblSecondNumber = LibraryItems.LocateAJLabel(this, layout, "Second Number:", 10, 180, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblAnswer = LibraryItems.LocateAJLabel(this, layout, "Answer:", 10, 210, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblSort = LibraryItems.LocateAJLabel(this, layout, "Sort:", 270, 260, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblIncorrect = LibraryItems.LocateAJLabel(this, layout, "Incorrectly answered questions:", 10, 360, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblQuestionsAsked = LibraryItems.LocateAJLabel(this, layout, "Questions asked:", 10, 500, true, 15, 255, 255, 255, false, 0, 0, 0);
        lblPreOrder = LibraryItems.LocateAJLabel(this, layout, "Pre-Order", 55, 640, true, 15, 255, 255, 255, false, 255, 255, 255);
        lblInOrder = LibraryItems.LocateAJLabel(this, layout, "In-Order", 275, 640, true, 15, 255, 255, 255, false, 255, 255, 255);
        lblPostOrder = LibraryItems.LocateAJLabel(this, layout, "Post-Order", 510, 640, true, 15, 255, 255, 255, false, 255, 255, 255);

        lblTitleColourBar = LibraryItems.LocateAJLabel(this, layout, "                                            ", 10, 10, true, 50, 0, 0, 140, true, 0, 0, 0);
        lblPreOrderColorBar = LibraryItems.LocateAJLabel(this, layout, "                                      ", 10, 640, true, 15, 0, 0, 140, true, 0, 0, 0);
        lblInOrderColorBar = LibraryItems.LocateAJLabel(this, layout, "                                      ", 220, 640, true, 15, 0, 0, 140, true, 0, 0, 0);
        lblPostOrderColorBar = LibraryItems.LocateAJLabel(this, layout, "                                      ", 475, 640, true, 15, 0, 0, 140, true, 0, 0, 0);
        lblAnsweredIncorrectColorBar = LibraryItems.LocateAJLabel(this, layout, "                                                                                                                                                        ", 10, 360, true, 15, 176, 224, 230, true, 0, 0, 140);
        lblAnswersAskedColorBar = LibraryItems.LocateAJLabel(this, layout, "                                                                                                                                                        ", 10, 500, true, 15, 176, 224, 230, true, 0, 0, 140);
    }
    
    /**
     * This method is used for getting and positioning TextFields. 
     * It uses Spring layout
     * @param layout 
     */
    private void showTextFields(SpringLayout layout)
    {
        txtFirstNum = LibraryItems.LocateAJTextField(this, this, layout, 8, 140, 120);
        txtSecondNum = LibraryItems.LocateAJTextField(this, this, layout, 8, 140, 180);
        txtAnswer = LibraryItems.LocateAJTextField(this, this, layout, 8, 140, 210);
        txtAnswer.setEditable(false);
    }
    
    /**
     * This method is used for getting and positioning Buttons. 
     * It uses Spring layout
     * @param layout 
     */
    private void showButtons(SpringLayout layout)
    {
        btnOne = LibraryItems.LocateAJButton(this, this, layout, "1", 310, 262, 80, 20);
        btnTwo = LibraryItems.LocateAJButton(this, this, layout, "2", 390, 262, 80, 20);
        btnThree = LibraryItems.LocateAJButton(this, this, layout, "3", 470, 262, 80, 20);
        btnExit = LibraryItems.LocateAJButton(this, this, layout, "Exit", 535, 300, 80, 20);
        btnSend = LibraryItems.LocateAJButton(this, this, layout, "Send", 150, 300, 80, 20);
        btnDisplay = LibraryItems.LocateAJButton(this, this, layout, "Display", 535, 500, 80, 20);
        btnDisplayPre = LibraryItems.LocateAJButton(this, this, layout, "Display", 10, 665, 75, 20);
        btnSavePre = LibraryItems.LocateAJButton(this, this, layout, "Save", 85, 665, 75, 20);
        btnDisplayOrder = LibraryItems.LocateAJButton(this, this, layout, "Display", 220, 665, 75, 20);
        btnSaveOrder = LibraryItems.LocateAJButton(this, this, layout, "Save", 295, 665, 75, 20);
        btnDisplayPost = LibraryItems.LocateAJButton(this, this, layout, "Display", 475, 665, 75, 20);
        btnSavePost = LibraryItems.LocateAJButton(this, this, layout, "Save", 550, 665, 75, 20);
    }
    
    /**
     * This method is used for getting and positioning TextAreas. 
     * It uses Spring layout
     * @param layout 
     */
    private void showTextAreas(SpringLayout layout)
    {
        txaIncorrectAnswers = LibraryItems.LocateAJTextArea(this, layout, txaIncorrectAnswers, 10, 390, 6, 55, true, 169, 169, 169, 1);
        txaQuestionsAsked = LibraryItems.LocateAJTextArea(this, layout, txaQuestionsAsked, 10, 530, 6, 55, true, 169, 169, 169, 1);
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

    /**
     * This method is used for getting and positioning the Panel and the Table.
     * Table attributes have been set at the bottom. It uses Spring layout
     * @param myPanelLayout 
     */
    public void WordAssociationTable(SpringLayout myPanelLayout)
    {
        // Create a panel to hold all other components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel);

        // Create column names
        String columnNames[] =
        {
            "FirstNum", "Operator", "SecondNum", "Answer"
        };

        // constructor of JTable model
        ArithmeticModel = new MyModel(dataValues, columnNames);

        // Create a new table instance
        table = new JTable(ArithmeticModel);

        // Configure some of JTable's paramters
        table.isForegroundSet();
        table.setShowHorizontalLines(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        add(table);

        // Change the text and background colours
        table.setSelectionForeground(Color.white);
        table.setSelectionBackground(Color.red);

        // Add the table to a scrolling pane, size and locate
        JScrollPane scrollPane = table.createScrollPaneForTable(table); // scrollbar not working 

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        topPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVisible(true);
        topPanel.setPreferredSize(new Dimension(367, 157));
        myPanelLayout.putConstraint(SpringLayout.WEST, topPanel, 260, SpringLayout.WEST, this);
        myPanelLayout.putConstraint(SpringLayout.NORTH, topPanel, 90, SpringLayout.NORTH, this);

        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(new Color(176, 224, 230));
        // matte border allows you to set the values of each side of the border
        Border tableHearderBorder = BorderFactory.createMatteBorder(2, 2, 0, 2, new Color(0, 0, 140));
        table.getTableHeader().setBorder(tableHearderBorder);
        Border tableBodyBorder = BorderFactory.createMatteBorder(0, 2, 2, 2, new Color(0, 0, 140));
        table.setBorder(tableBodyBorder);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // make a for loop for the table.getcolumn
        for (int i = 0; i < 4; i++)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        }
    }
   
    /**
     * This method is used for positioning and displaying a Combo box 
     * and setting it's values. It uses Spring layout
     * @param layout 
     */
    private void showComboBoxs(SpringLayout layout)
    {
        // add code to this for the operators ----  ----    ----    ----    ----    ----
        cboOperator = LibraryItems.LocateAJComboBox(this,/* this,*/ layout, 140, 147);
        cboOperator.addItem("+");
        cboOperator.addItem("-");
        cboOperator.addItem("*");
        cboOperator.addItem("/");
    }
    // </editor-fold>
    // Done
    // <editor-fold defaultstate="collapsed" desc="Draw Tree to Frame">
    /**
     * This method is for the tree drawing. It calls paint method. 
     */
    // initFrame Draws graphics to the screen (called by btnDisplay)
    public void initFrame()
    {
        drawTree = new JFrame()
        {
            @Override
            public void paint(Graphics g)
            {
                gee = g;
                MyBtNode.paintPreorderTraverseTree(MyBtNode.root, gee, 80, 920, 450, 900);

            }
        };
    }
    // </editor-fold>
    // Done
    // <editor-fold defaultstate="collapsed" desc="Buttons">
    @Override
    public void actionPerformed(ActionEvent e)
    {
        /**
         * This Button calls the bubbleSort on When clicked
         */
        if (e.getSource() == btnOne)
        {
            SortLibrary.bubbleSort(dataValues);
            table.repaint();
        }
        
        /**
         * This Button calls the InsertionSort on When clicked
         */
        if (e.getSource() == btnTwo)
        {
            SortLibrary.InsertionSort(dataValues);
            table.repaint();
        }
        
        /**
         * This Button calls the SelectionSort on When clicked
         */
        if (e.getSource() == btnThree)
        {
            SortLibrary.SelectionSort(dataValues);
            table.repaint();

        }
        
        /**
         * This button evaluates the values given in the question and gives 
         * the answer, it also stores the values in the table and adds it 
         * to the binary tree as a node. The Value is also sent to the 
         * student.
         */
        if (e.getSource() == btnSend)
        {
            switch (cboOperator.getSelectedItem().toString())
            {
                    
                case "+":
                int answer = Integer.parseInt(txtFirstNum.getText()) + Integer.parseInt(txtSecondNum.getText());
                txtAnswer.setText(Integer.toString(answer));
                break;

                case "-":
                answer = Integer.parseInt(txtFirstNum.getText()) - Integer.parseInt(txtSecondNum.getText());
                txtAnswer.setText(Integer.toString(answer));
                break;
                    
                case "*":
                answer = Integer.parseInt(txtFirstNum.getText()) * Integer.parseInt(txtSecondNum.getText());
                txtAnswer.setText(Integer.toString(answer));
                break;
                
                case "/":
                answer = Integer.parseInt(txtFirstNum.getText()) / Integer.parseInt(txtSecondNum.getText());
                txtAnswer.setText(Integer.toString(answer));
                break;
                
            }

            // make this work, look in DList and Answers 
            dataValues.add(new Object[]
            {
                txtFirstNum.getText(), cboOperator.getSelectedItem(), txtSecondNum.getText(), txtAnswer.getText()
            });
            MyDList.head.append(new Node(txtFirstNum.getText(), cboOperator.getSelectedItem().toString(), txtSecondNum.getText(), txtAnswer.getText()));
                     
            //MyDList.print(txaIncorrectAnswers); // this for print to textArea use this to narrow problem
            ArithmeticModel.fireTableDataChanged();

            MyBtNode.addBtNode(Integer.parseInt(txtAnswer.getText()), txtFirstNum.getText() + cboOperator.getSelectedItem().toString() + txtSecondNum.getText());
            System.out.println();
            //answerStorage = Integer.parseInt(txtAnswer.toString());
          
             send();
             txtFirstNum.requestFocus();
             MyQuestions[currentQuestion] = new Answers();
             
        }
        
        /**
         * this button displays the tree to the screen and sets the 
         * frame values
         */
        if (e.getSource() == btnDisplay) //this is for the tree graphical repesentation
        {
            initFrame();
            drawTree.setBounds(10, 30, 1900, 1000);
            drawTree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            drawTree.setVisible(true);
            drawTree.setResizable(true);
            drawTree.setTitle("Draw Tree");
        }
        
        /**
         * this button writes the binary tree values to the QuestionsAsked
         * text area in pre-order
         */
        if (e.getSource() == btnDisplayPre)
        {
            txaQuestionsAsked.setText("");
            txaQuestionsAsked.append("PRE-ORDER ");
            MyBtNode.preorderTraverseTree(MyBtNode.root, txaQuestionsAsked);
        }
        
        /**
         * this button saves the binary tree values to 2 files but also only 
         * allows one text file to exist and overwrites the the serialized file.
         * the output of this will be in the directory labeled in the path 
         * variable and a de-serialized file written to the console. the text
         * file will be written in pre-order
         */
        if (e.getSource() == btnSavePre)
        {
            try
            {   
                File file = new File(path + "preOrderHash.txt");
                if (file.exists()) file.delete();
                file.createNewFile();
                writerPre = new FileWriter(path + "preOrderHash.txt");
                HashMap<String, String> preMap = new HashMap<>();
                MyBtNode.SaveHashPreorderTraverseTree(MyBtNode.root, txaQuestionsAsked, preMap, writerPre);
                writerPre.close();
                MyBtNode.hashPreSerial(preMap);
                MyBtNode.hashPreSerialOut(preMap);
            } 
            catch (Exception ex)
            {
                System.out.println("Error Writing to file");
                System.out.println(ex);
            }
        }
        
        /**
         * this button writes the binary tree values to the QuestionsAsked
         * text area in in-order
         */
        if (e.getSource() == btnDisplayOrder)
        {
            txaQuestionsAsked.setText("");
            txaQuestionsAsked.append("IN-ORDER ");
            MyBtNode.inOrderTraverseTree(MyBtNode.root, txaQuestionsAsked);
        }
        
        /**
         * this button saves the binary tree values to 2 files but also only 
         * allows one text file to exist and overwrites the the serialized file.
         * the output of this will be in the directory labeled in the path 
         * variable and a de-serialized file written to the console. the text
         * file will be written in in-order
         */
        if (e.getSource() == btnSaveOrder)
        {
            try
            {
                File file = new File(path + "inOrderHash.txt");
                if (file.exists()) file.delete();
                file.createNewFile();
                writerPre = new FileWriter(path + "inOrderHash.txt");
                HashMap<String, String> inMap = new HashMap<>();
                MyBtNode.SaveHashInOrderTraverseTree(MyBtNode.root, txaQuestionsAsked, inMap, writerPre);
                writerPre.close();
                MyBtNode.hashInSerial(inMap);
                MyBtNode.hashInSerialOut(inMap);
            } 
            catch (Exception ex)
            {
                System.out.println("Error Writing to file");
                System.out.println(ex);
            }
        }

        /**
         * this button writes the binary tree values to the QuestionsAsked
         * text area in post-order
         */
        if (e.getSource() == btnDisplayPost)
        {
            txaQuestionsAsked.setText("");
            txaQuestionsAsked.append("POST-ORDER ");
            MyBtNode.postOrderTraverseTree(MyBtNode.root, txaQuestionsAsked);
        }
        
        /**
         * this button saves the binary tree values to 2 files but also only 
         * allows one text file to exist and overwrites the the serialized file.
         * the output of this will be in the directory labeled in the path 
         * variable and a de-serialized file written to the console. the text
         * file will be written in post-order
         */
        if (e.getSource() == btnSavePost)
        {
            try
            {
                File file = new File(path + "postOrderHash.txt");
                if (file.exists()) file.delete();
                file.createNewFile();
                writerPre = new FileWriter(path + "postOrderHash.txt");
                HashMap<String, String> postMap = new HashMap<>();
                MyBtNode.SaveHashPostOrderTraverseTree(MyBtNode.root, txaQuestionsAsked, postMap, writerPre);
                writerPre.close();
                MyBtNode.hashPostSerial(postMap);
                MyBtNode.hashPostSerialOut(postMap);
            } 
            catch (Exception ex)
            {
                System.out.println("Error Writing to file");
                System.out.println(ex);
            }
        }
        
        /**
         * This button exists the program and calls the send function to
         * send any values that where in the question box.
         */
        if (e.getSource() == btnExit)
        {
            send();
            System.exit(0);
        }
    }
    // </editor-fold>

    // Done
    /**
     * This focus lost event is purely for catching if users enter nonsense 
     * values in the number fields
     * @param e 
     */
    @Override
    public void focusLost(FocusEvent e)
    {
        if (e.getSource() == txtFirstNum)
        {
            if(txtFirstNum.getText().matches("[0-9]+") || txtFirstNum.getText().equals(""))
            {
                // do nothing
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please enter numbers only!");
            }
        }
        if (e.getSource() == txtSecondNum)
        {
            if(txtSecondNum.getText().matches("[0-9]+") || txtSecondNum.getText().equals(""))
            {
                // do nothing
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please enter numbers only!");
            }
        }
    }

    // Done
    // <editor-fold defaultstate="collapsed" desc="Classes">
    /**
     * This class is for managing the the table  
     */
    class MyModel extends AbstractTableModel
    {

        ArrayList<Object[]> al;

        // the headers
        String[] header;

        // constructor 
        MyModel(ArrayList<Object[]> obj, String[] header)
        {
            // save the header
            this.header = header;
            // and the data
            al = obj;
        }

        /**
         * this gets the row count
         * @return the amount of rows
         */
        // method that needs to be overload. The row count is the size of the ArrayList
        public int getRowCount()
        {
            return al.size();
        }

        /**
         * this gets the column count
         * @return the amount of columns
         */
        // method that needs to be overload. The column count is the size of our header
        @Override
        public int getColumnCount()
        {
            return header.length;
        }

        /**
         * this gets the value at a particular table index
         * @param rowIndex
         * @param columnIndex
         * @return the value at the index 
         */
        // method that needs to be overload. The object is in the arrayList at rowIndex
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return al.get(rowIndex)[columnIndex];
        }
        
        /**
         * this gets the name of the column
         * @param index
         * @return the value at the top (header) of that column
         */
        // a method to return the column name 
        public String getColumnName(int index)
        {
            return header[index];
        }
        
        /**
         * this adds rows to the table
         * @param word1
         * @param word2 
         */
        // a method to add a new line to the table
        void add(String word1, String word2)
        {
            // make it an array[2] as this is the way it is stored in the ArrayList
            // (not best design but we want simplicity)
            String[] str = new String[2];
            str[0] = word1;
            str[1] = word2;
            al.add(str);
            // inform the GUI that I have change
            fireTableDataChanged();
        }
    }
    // </editor-fold>
    // Done 
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
     * this method sends question and answer value to the student GUI and also 
     * sets the text fields back to basic though it keeps the answer displayed
     * the answer is stored in answerStorage. 
     */
    private void send()  
    {
        try
        {   // may need to make changes to this to make the split easier at the other end
            String temp = txtFirstNum.getText() +","+ cboOperator.getSelectedItem().toString() +","+ txtSecondNum.getText() +","+ txtAnswer.getText();
            streamOut.writeUTF(temp);
            streamOut.flush();
            txtFirstNum.setText("");
            cboOperator.setSelectedItem("+");
            txtSecondNum.setText("");
            //txtAnswer.setText("");
            answerStorage = txtAnswer.getText();
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
     * answerStorage is used here to print the correct answer to the text area
     * correct student answers write to console what the student socket number
     * is and that they were correct. incorrect answers are sent to the 
     * incorrect answer text area. 
     * @param msg 
     */
    public void handle(String msg)
    { 
        this.msg = msg; // this is just to store globally

        String unpackUtilsAfter = StringUtils.substringAfter(msg, " ");
        println(unpackUtilsAfter);
        
        String unpackUtilsBefore = StringUtils.substringBefore(msg, ":");
        println(unpackUtilsBefore);
       
        String[] unpack = unpackUtilsAfter.split(",");
        String unpackFixed = unpack[0].substring(unpack[0].indexOf(":") + 1);
        unpackFixed.trim();

        if (unpackUtilsBefore.equals(GetInstructorSocketNumber()))
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
                if (unpack[3].equals(answerStorage))  // correct answer
                {
                    println(unpackFixed +" "+ unpack[1].toString() +" "+ unpack[2]);
                }
                else // incorrect answer
                {
                    txaIncorrectAnswers.append(unpackFixed + " " + unpack[1] + " " + unpack[2] + " = " + answerStorage + "    ");
                }
            }
        }
    }
    
    /**
     * this method is used to extract the socket number for this user
     * @return String value of the socket number
     */
    public String GetInstructorSocketNumber()
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
            client = new ChatClientThread1(this, socket);
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
        client.close();
        client.stop();
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
