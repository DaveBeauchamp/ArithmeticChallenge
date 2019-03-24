/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmeticchallenge;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Answers
{
    String answerVal1;
    String answerVal2;            
    String answerVal3;
    String answerVal4;
    
//    String question = answerVal1 + answerVal2 + answerVal3;
//    int answer = Integer.parseInt(answerVal4);
    

    public Answers()
    {
        answerVal1 = "First Value";
        answerVal2 = "Operator";
        answerVal3 = "Second Value";
        answerVal4 = "Answer to question";
    }        

    
    public Answers(String Part1, String Part2, String Part3, String Part4)
    {
 
        answerVal1 = Part1;
        answerVal2 = Part2;
        answerVal3 = Part3;
        answerVal4 = Part4;
    }  
    
    
    
    // this is for the answer to concat the values & operator
    public String getQuestion(String Part1, String Part2, String Part3)
    { 
        String question = answerVal1 + answerVal2 + answerVal3;
        
        return question;
    }
    
    // more concise
//    public String getQuestion(String Question)
//    { 
//        this.question = answerVal1 + answerVal2 + answerVal3;
//        
//        Question = question;
//        
//        return Question;
//    }
 
    // this is for the answer part to use toInt(on the Answer column)
    public String getAnswer(String Part4)
    {
        String answer = answerVal4;
        
        return answer;
    }
    
    // more concise
//    public int getAnswer(int Answer)
//    { 
//        this.answer = Integer.parseInt(answerVal4);
//        
//        Answer = answer;
//        
//        return Answer;
//    }
    

    
}
