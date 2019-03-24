/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmeticchallenge;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author student
 */
public class ArithmeticChallengeStudent extends JFrame implements ActionListener, MouseListener 
{
    private JTextField txtFirstNum, txtSecondNum, txtAnswer, txtStudentQuestion, txtStudentAnswer;
    private JTextArea txaIncorrectAnswers, txaQuestionsAsked;
    private JButton btnOne, btnTwo, btnThree, btnExit, btnDisplay, btnDisplayPre, btnSavePre, btnDisplayOrder, btnSaveOrder, btnDisplayPost, btnSavePost, btnSend, btnStudentSubmit, btnStudentExit;
    private JLabel lblTitle, lblEnterQuestion, lblFirstNumber, lblOperator, lblSecondNumber, lblAnswer, lblIncorrect, lblQuestionsAsked, lblPreOrder, lblInOrder, lblPostOrder, lblSort, lblStudentQuestion, lblStudentAnswer;
    private JComboBox cboOperator; 
     
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        
        ArithmeticChallenge arithemticChallengeStudentApp = new ArithmeticChallenge();
        arithemticChallengeStudentApp.RunStudentApp();
    }
    
    
    private void RunStudentApp()   
    {
        
        setBounds(500, 500, 300, 300); 
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
        
        setResizable(true);     
        setVisible(true);
    }
    
   
    private void showStudentGUI() 
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        getContentPane().setBackground(Color.white);
        //getContentPane().setBackground(new Color(176,224,230)); // used  this for visibility of fields, kill it later maybe site: https://www.rapidtables.com/web/color/RGB_Color.html
        showStudentTextFields(springLayout);
        showStudentButtons(springLayout);
        showStudentLabels(springLayout);
          
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
    
    
    
}
