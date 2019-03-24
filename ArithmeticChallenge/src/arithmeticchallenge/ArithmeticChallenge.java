/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmeticchallenge;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

/**
 *
 * @author student
 */
public class ArithmeticChallenge extends JFrame implements ActionListener, MouseListener
{
    private JTextField txtFirstNum, txtSecondNum, txtAnswer, txtStudentQuestion, txtStudentAnswer;
    private JTextArea txaIncorrectAnswers, txaQuestionsAsked;
    private JButton btnOne, btnTwo, btnThree, btnExit, btnDisplay, btnDisplayPre, btnSavePre, btnDisplayOrder, btnSaveOrder, btnDisplayPost, btnSavePost, btnSend, btnStudentSubmit, btnStudentExit;
    private JLabel lblTitle, lblEnterQuestion, lblFirstNumber, lblOperator, lblSecondNumber, lblAnswer, lblIncorrect, lblQuestionsAsked, lblPreOrder, lblInOrder, lblPostOrder, lblSort, lblStudentQuestion, lblStudentAnswer, lblTitleColourBar, lblPreOrderColorBar, lblInOrderColorBar, lblPostOrderColorBar, lblAnsweredIncorrectColorBar, lblAnswersAskedColorBar, lblQuestionBG, lblQuestionsBG;
    private JComboBox cboOperator; 
    
     
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        
        ArithmeticChallenge arithemticChallengeApp = new ArithmeticChallenge();
        
        arithemticChallengeApp.RunInstructorApp();
        arithemticChallengeApp.paint(null);
        
        //arithemticChallengeApp.RunStudentApp();
        
    }
    
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
        
        JPanel bgPanel = new JPanel();
        
        bgPanel.setBackground(Color.red);
        bgPanel.setPreferredSize(new Dimension(200,200));
        this.add(bgPanel);
        
   
    }
    
//    private void RunStudentApp()   
//    {
//        
//        setBounds(500, 500, 300, 300); 
//        setTitle("Arithmetic Challenge");
//        
//        addWindowListener(new WindowAdapter()
//        {
//            @Override
//            public void windowClosing(WindowEvent e)
//            {
//                System.exit(0);
//            }
//        });
//             
//        showStudentGUI();
//        
//        setResizable(true);     
//        setVisible(true);
//    }
    
    
    private void showInstructorGUI() 
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        getContentPane().setBackground(Color.white);
        //getContentPane().setBackground(new Color(152, 251, 152)); // pale green for testing UI locations 
        //getContentPane().setBackground(new Color(176,224,230)); // used  this for visibility of fields, kill it later maybe site: https://www.rapidtables.com/web/color/RGB_Color.html
        showTextFields(springLayout);
        showButtons(springLayout);
        showLabels(springLayout);
        showComboBoxs(springLayout);
        showTextAreas(springLayout);
        
       
        
        
    }
    
//    private void showStudentGUI() 
//    {
//        SpringLayout springLayout = new SpringLayout();
//        setLayout(springLayout);
//        getContentPane().setBackground(Color.white);
//        //getContentPane().setBackground(new Color(176,224,230)); // used  this for visibility of fields, kill it later maybe site: https://www.rapidtables.com/web/color/RGB_Color.html
//        showStudentTextFields(springLayout);
//        showStudentButtons(springLayout);
//        showStudentLabels(springLayout);
//          
//    }
    
    
    private void showLabels(SpringLayout layout) 
    {
        // label
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
        
        // colour strips
        // lblAnsweredIncorrectColorBar, lblAnswersAskedColorBar
        lblTitleColourBar = LibraryItems.LocateAJLabel(this, layout, "                                            ", 10, 10, true, 50, 0, 0, 140, true, 0, 0, 0);
        lblPreOrderColorBar = LibraryItems.LocateAJLabel(this, layout, "                                      ", 10, 640, true, 15, 0, 0, 140, true, 0, 0, 0);
        lblInOrderColorBar = LibraryItems.LocateAJLabel(this, layout, "                                      ", 220, 640, true, 15, 0, 0, 140, true, 0, 0, 0);
        lblPostOrderColorBar = LibraryItems.LocateAJLabel(this, layout, "                                      ", 475, 640, true, 15, 0, 0, 140, true, 0, 0, 0);
        lblAnsweredIncorrectColorBar = LibraryItems.LocateAJLabel(this, layout, "                                                                                                                                                        ", 10, 360, true, 15, 176, 224, 230, true, 0, 0, 140);
        lblAnswersAskedColorBar = LibraryItems.LocateAJLabel(this, layout, "                                                                                                                                                        ", 10, 500, true, 15, 176, 224, 230, true, 0, 0, 140);
         
        
        // make a panel, pane, label to layer behind the question block to highlight 
        // that part of the page and use border to make it look good 
        // once you have it working 
        // then make student page and wait for hints on how to use table for top left
        
    }
    
    private void showTextFields(SpringLayout layout) 
    {
        txtFirstNum = LibraryItems.LocateAJTextField(this, this, layout, 8, 140, 120);
        txtSecondNum = LibraryItems.LocateAJTextField(this, this, layout, 8, 140, 180);
        txtAnswer = LibraryItems.LocateAJTextField(this, this, layout, 8, 140, 210);
    }
    
    private void showComboBoxs(SpringLayout layout) 
    {
        cboOperator = LibraryItems.LocateAJComboBox(this, this, layout, 140, 147);
    }
    
    
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
    
    private void showTextAreas(SpringLayout layout)   
    {    
        txaIncorrectAnswers = LibraryItems.LocateAJTextArea(this, layout, txaIncorrectAnswers, 10, 390, 6, 55, true, 169, 169, 169, 1);
        txaQuestionsAsked = LibraryItems.LocateAJTextArea(this, layout, txaQuestionsAsked, 10, 530, 6, 55, true, 169, 169, 169, 1);
        // see library for inspiration though add a border to the is for visibility (both text areas)
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void showStudentTextFields(SpringLayout layout)
    {
        // txtStudentQuestion, txtStudentAnswer
    
    }
    
    private void showStudentButtons(SpringLayout layout)
    {
        // btnStudentSubmit, btnStudentExit
    
    }

    private void showStudentLabels(SpringLayout layout)
    {
        // lblStudentQuestion, lblStudentAnswer
    
    }
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void RunStudentApp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}


