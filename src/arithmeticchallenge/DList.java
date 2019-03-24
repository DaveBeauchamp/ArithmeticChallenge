/**
 * **************************************************************************
 */
/*                                                                           */
/*                    Doubly-Linked List Manipulation                        */
/*                                                                           */
/*                     January 1998, Toshimi Minoura                         */
/*                                                                           */
/**
 * **************************************************************************
 */
// Filename: Doubly-LinkedList_ToshimiMinoura
// Source:   TBA
package arithmeticchallenge;

// A Node is a node in a doubly-linked list.

import javax.swing.JTextArea;

class Node
{              // class for nodes in a doubly-linked list

    Node prev;              // previous Node in a doubly-linked list
    Node next;              // next Node in a doubly-linked list
    Answers classAnswers;
    //public char data;       // data stored in this Node

    Node()
    {                // constructor for head Node 
        prev = this;           // of an empty doubly-linked list
        next = this;
        classAnswers = new Answers();
        classAnswers.answerVal1 = "This text is in node pos1";
        classAnswers.answerVal2 = "This text is in node pos2";
        classAnswers.answerVal3 = "This text is in node pos3";
        classAnswers.answerVal4 = "This text is in node pos4";
        // data = 'H';           // not used except for printing data in list head
    }

    Node(String a1, String a2, String a3, String a4)
    {       // constructor for a Node with data
        prev = null;
        next = null;
        classAnswers = new Answers(a1 , a2, a3, a4);    // had to add args/ params here to make it work was going to default in Answers.java
        //this.data = data;     // set argument data to instance variable data
    }

    public void append(Node newNode)
    {  // attach newNode after this Node
        newNode.prev = this;
        newNode.next = next;
        if (next != null)
        {
            next.prev = newNode;
        }
        next = newNode;
        // change this to display in the console window
//        System.out.println("Node with data " + newNode.classAnswers.answerVal1
//                + " appended after Node with data " + classAnswers.answerVal1);
        // put modified here
        //System.out.println(newNode.classAnswers.answerVal1 + " " + newNode.classAnswers.answerVal2 + " " + newNode.classAnswers.answerVal3 + " = " + newNode.classAnswers.answerVal4); 
    }

    public void insert(Node newNode)
    {  // attach newNode before this Node
        newNode.prev = prev;
        newNode.next = this;
        prev.next = newNode;;
        prev = newNode;
//        System.out.println("Node with data " + newNode.classAnswers.answerVal1
//                + " inserted before Node with data " + classAnswers.answerVal1);
    }

    public void remove()
    {              // remove this Node
        next.prev = prev;                 // bypass this Node
        prev.next = next;
//        System.out.println("Node with data " + classAnswers.answerVal1 + " removed");
    }
    public String toString()
    {   // may need to add more to this with the extra results, answerVal3 and answerVal4
        return this.classAnswers.answerVal1 + " - " + this.classAnswers.answerVal2; 
    }   // get to this when you have more time (^ above ^)
}

class DList
{

    Node head;

    public DList()
    {
        head = new Node();
    }

    public DList(String s1, String s2, String s3, String s4)
    {
        head = new Node(s1, s2, s3 ,s4);
    }

    public Node find(String ans1)
    {          // find Node containing x
        for (Node current = head.next; current != head; current = current.next)
        {
            if (current.classAnswers.answerVal1.compareToIgnoreCase(ans1) == 0)
            {        // is x contained in current Node?
                System.out.println("Data " + ans1 + " found");
                return current;               // return Node containing x
            }
        }
        System.out.println("Data " + ans1 + " not found");
        return null;
    }

    //This Get method Added by Matt C
    public Node get(int i)
    {
        Node current = this.head;
        if (i < 0 || current == null)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (i > 0)
        {
            i--;
            current = current.next;
            if (current == null)
            {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return current;
    }

    public String toString()
    {
        String str = "";
        if (head.next == head)
        {             // list is empty, only header Node
            return "List Empty";
        }
        str = "list content = ";
        for (Node current = head.next; current != head && current != null; current = current.next)
        {
            str = str + current.classAnswers.answerVal1;
        }
        return str;
    }

    public void print()
    {                  // print content of list
        if (head.next == head)
        {             // list is empty, only header Node
            System.out.println("list empty");
            return;
        }
        System.out.print("list content = ");    // intersesting use of for loop may use != for some 
                                                // loops in future
        for (Node current = head.next; current != head; current = current.next)
        {
            System.out.print(" " + current.classAnswers.answerVal1 + " " + current.classAnswers.answerVal2 + " " + current.classAnswers.answerVal3 + " = " + current.classAnswers.answerVal4);
            
        }
        System.out.println("");
    }
    
    // copied this one for using the jTextarea 
    public JTextArea print(JTextArea contents)
    {                  // print content of list
        if (head.next == head)
        {             // list is empty, only header Node
            System.out.println("list empty");
            return contents;
        }
        //System.out.print("list content = ");    // intersesting use of for loop may use != for some 
                                                // loops in future
        contents.setText(null); // this stop the crazy extra text in the taxt area, comment out and see what I mean
        
        for (Node current = head.next; current != head; current = current.next)
        {
            //System.out.print(" " + current.classAnswers.answerVal1 + " " + current.classAnswers.answerVal2 + " " + current.classAnswers.answerVal3 + " = " + current.classAnswers.answerVal4);
            contents.append(current.classAnswers.answerVal1 + " " + current.classAnswers.answerVal2 + " " + current.classAnswers.answerVal3 + " = " + current.classAnswers.answerVal4 + "     ");
        }
        System.out.println("");
        return contents;
    }
 

}
