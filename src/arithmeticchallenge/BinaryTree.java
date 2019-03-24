package arithmeticchallenge;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.AttributedCharacterIterator;
import javax.swing.JTextArea;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//Source:  http://www.newthinktank.com/2013/03/binary-tree-in-java/
// New Think Tank

// take binary tree and put it into a hashing algorithm
// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Hashing/hashing.html

public class BinaryTree extends Graphics        // might think about extending this class to allow for the use of graphics without doing all the crazy stuff I tried before. 
{

    public Graphics g;

    BtNode root;
    
    String path = "E:\\Tafe\\"; 

    // <editor-fold defaultstate="collapsed" desc="Draw to Frame">
    // This is my method that paints to the Draw Tree frame
    public void paintPreorderTraverseTree(BtNode focusBtNode, Graphics g, int vMove, int centralX, int xLeft, int xRight)
    {
        // variables
        int verticalMove = vMove;
        int xLocation = centralX;
        int moveLeft = xLeft;
        int moveRight = xRight;

        if (focusBtNode != null)
        {

            this.g = g;
            this.g.setColor(Color.BLACK);                                       // set draw colour black before draw text
            this.g.drawString(focusBtNode.toString(), xLocation, verticalMove); // draw text 

            this.g.setColor(Color.RED);                                         // set draw colour red before draw oval
            this.g.drawOval(xLocation - 1, verticalMove - 38, 65, 65);          // put circle/oval around the text
            verticalMove += 120;                                                // move down the page

            // node spacing  
            paintPreorderTraverseTree(focusBtNode.leftChild, this.g, verticalMove, xLocation -= moveLeft, moveLeft -= moveLeft / 2, moveLeft += moveLeft);
            paintPreorderTraverseTree(focusBtNode.rightChild, this.g, verticalMove, xLocation += moveRight, moveLeft -= moveLeft / 2, moveRight -= moveRight / 2);

        }
        // draw null text and circle/oval for empty locations, not needed though
        // helpful during creation could comment this block out
        if (focusBtNode == null)
        {
            this.g.drawString("      null", xLocation, verticalMove);
            this.g.drawOval(xLocation - 1, verticalMove - 38, 65, 65);
        }
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="add node">
    public void addBtNode(int key, String name)
    {

        // Create a new BtNode and initialize it
        BtNode newBtNode = new BtNode(key, name);

        // If there is no root this becomes root
        if (root == null)
        {

            root = newBtNode;

        } else
        {

            // Set root as the BtNode we will start
            // with as we traverse the tree
            BtNode focusBtNode = root;

            // Future parent for our new BtNode
            BtNode parent;

            while (true)
            {

                // root is the top parent so we start
                // there
                parent = focusBtNode;

                // Check if the new BtNode should go on
                // the left side of the parent BtNode
                if (key < focusBtNode.key)
                {

                    // Switch focus to the left child
                    focusBtNode = focusBtNode.leftChild;

                    // If the left child has no children
                    if (focusBtNode == null)
                    {

                        // then place the new BtNode on the left of it
                        parent.leftChild = newBtNode;
                        return; // All Done

                    }

                } else
                { // If we get here put the BtNode on the right

                    focusBtNode = focusBtNode.rightChild;

                    // If the right child has no children
                    if (focusBtNode == null)
                    {

                        // then place the new BtNode on the right of it
                        parent.rightChild = newBtNode;
                        return; // All Done

                    }

                }

            }
        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Traverse order">
    // All BtNodes are visited in ascending order
    // Recursion is used to go to one BtNode and
    // then go to its child BtNodes and so forth
    public void inOrderTraverseTree(BtNode focusBtNode, JTextArea order) //LVR
    {

        if (focusBtNode != null)
        {

            // Traverse the left BtNode
            inOrderTraverseTree(focusBtNode.leftChild, order);

            // Visit the currently focused on BtNode
            //System.out.println(focusBtNode);
            order.append(focusBtNode.toString() + ",   ");

            // Traverse the right BtNode
            inOrderTraverseTree(focusBtNode.rightChild, order);

        }
    }

    public void SaveHashInOrderTraverseTree(BtNode focusBtNode, JTextArea order, HashMap hash, FileWriter writer) //LVR
    {

        if (focusBtNode != null)
        {

            // Traverse the left BtNode
            SaveHashInOrderTraverseTree(focusBtNode.leftChild, order, hash, writer);

            hashIn(focusBtNode, hash, writer);

            // Traverse the right BtNode
            SaveHashInOrderTraverseTree(focusBtNode.rightChild, order, hash, writer);

        }
    }

    public void hashIn(BtNode focusBtNode, HashMap hash, FileWriter writer) // method to call inside overload to write to hashmap
    {
        hash.put(focusBtNode.key, focusBtNode.ArithQuestion);
        try
        {
            writer.append("Key: " + focusBtNode.key + "\t " + "Value: " + focusBtNode.ArithQuestion + "\r\n");
        } 
        catch (Exception e)
        {
            System.out.println("Error Writing to file");
            System.out.println(e);
        }
    }

    //  Source:  https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
    //  beginnersbook.com 
    public void hashInSerial(HashMap hash)
    {
        try
        {           
            FileOutputStream fileOutStream = new FileOutputStream(path + "hashInSerial.ser");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(hash);
            objectOutStream.close();
            fileOutStream.close();
            System.out.printf("Serialized HashMap data is saved in hashInSerial.ser");
        } 
        catch (Exception ex)
        {
            System.out.printf("Serailze failed: " + ex);
        }
    }
    
    //  Source:  https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
    //  beginnersbook.com 
    public void hashInSerialOut(HashMap hash)
    {
        try
      {
         FileInputStream FileInputStream = new FileInputStream(path + "hashInSerial.ser");
         ObjectInputStream ObjectInputStream = new ObjectInputStream(FileInputStream);
         hash = (HashMap) ObjectInputStream.readObject();
         ObjectInputStream.close();
         FileInputStream.close();
      }
      catch(IOException ioe)
      {
         return;
      }
      catch(ClassNotFoundException c)
      {
         System.out.println("Class not found");
         return;
      }
      System.out.println("Deserialized HashMap..");
      // Display content using Iterator
      Set set = hash.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) 
      {
         Map.Entry mentry = (Map.Entry)iterator.next();
         System.out.print("key: "+ mentry.getKey() + " & Value: ");
         System.out.println(mentry.getValue());
      }
    }
    
    public void preorderTraverseTree(BtNode focusBtNode, JTextArea order) // VLR     
    {

        if (focusBtNode != null)
        {
            //System.out.println(focusBtNode);
            order.append(focusBtNode.toString() + ",   ");
            
            preorderTraverseTree(focusBtNode.leftChild, order);
            preorderTraverseTree(focusBtNode.rightChild, order);

        }
    }

    public void SaveHashPreorderTraverseTree(BtNode focusBtNode, JTextArea order, HashMap hash, FileWriter writer) // VLR     
    {

        if (focusBtNode != null)
        {

            hashPre(focusBtNode, hash, writer);

            SaveHashPreorderTraverseTree(focusBtNode.leftChild, order, hash, writer);
            SaveHashPreorderTraverseTree(focusBtNode.rightChild, order, hash, writer);

        }
    }

    public void hashPre(BtNode focusBtNode, HashMap hash, FileWriter writer) // method to call inside overload to write to hashmap
    {
        hash.put(focusBtNode.key, focusBtNode.ArithQuestion);
        try
        {
            writer.append("Key: " + focusBtNode.key + "\t " + "Value: " + focusBtNode.ArithQuestion + "\r\n");
        } 
        catch (Exception e)
        {
            System.out.println("Error Writing to file");
            System.out.println(e);
        }
    }
    
    //  Source:  https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
    //  beginnersbook.com 
    
    
    public void hashPreSerial(HashMap hash)
    {
        try
        {           
            FileOutputStream fileOutStream = new FileOutputStream(path + "hashPreSerial.ser");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(hash);
            objectOutStream.close();
            fileOutStream.close();
            System.out.printf("Serialized HashMap data is saved in hashPreSerial.ser");
        } 
        catch (Exception ex)
        {
            System.out.printf("Serailze failed: " + ex);
        }
    }
    
    //  Source:  https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
    //  beginnersbook.com 
    public void hashPreSerialOut(HashMap hash)
    {
        try
      {
         FileInputStream FileInputStream = new FileInputStream(path + "hashPreSerial.ser");
         ObjectInputStream ObjectInputStream = new ObjectInputStream(FileInputStream);
         hash = (HashMap) ObjectInputStream.readObject();
         ObjectInputStream.close();
         FileInputStream.close();
      }
      catch(IOException ioe)
      {
         return;
      }
      catch(ClassNotFoundException c)
      {
         System.out.println("Class not found");
         return;
      }
      System.out.println("Deserialized HashMap..");
      // Display content using Iterator
      Set set = hash.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) 
      {
         Map.Entry mentry = (Map.Entry)iterator.next();
         System.out.print("key: "+ mentry.getKey() + " & Value: ");
         System.out.println(mentry.getValue());
      }
    }

    public void postOrderTraverseTree(BtNode focusBtNode, JTextArea order)   //LRV
    {
        if (focusBtNode != null)
        {
            postOrderTraverseTree(focusBtNode.leftChild, order);
            postOrderTraverseTree(focusBtNode.rightChild, order);

            //System.out.println(focusBtNode);
            order.append(focusBtNode.toString() + ",   ");
        }
    }

    public void SaveHashPostOrderTraverseTree(BtNode focusBtNode, JTextArea order, HashMap hash, FileWriter writer)   //LRV
    {
        if (focusBtNode != null)
        {
            SaveHashPostOrderTraverseTree(focusBtNode.leftChild, order, hash, writer);
            SaveHashPostOrderTraverseTree(focusBtNode.rightChild, order, hash, writer);

            hashPost(focusBtNode, hash, writer);
        }
    }

    public void hashPost(BtNode focusBtNode, HashMap hash, FileWriter writer) // method to call inside overload to write to hashmap
    {
        hash.put(focusBtNode.key, focusBtNode.ArithQuestion);
        try
        {
            writer.append("Key: " + focusBtNode.key + "\t " + "Value: " + focusBtNode.ArithQuestion + "\r\n");
        } 
        catch (Exception e)
        {
            System.out.println("Error Writing to file");
            System.out.println(e);
        }
    }
    
    //  Source:  https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
    //  beginnersbook.com 
    public void hashPostSerial(HashMap hash)
    {
        try
        {           
            FileOutputStream fileOutStream = new FileOutputStream(path + "hashPostSerial.ser");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(hash);
            objectOutStream.close();
            fileOutStream.close();
            System.out.printf("Serialized HashMap data is saved in hashPostSerial.ser");
        } 
        catch (Exception ex)
        {
            System.out.printf("Serailze failed: " + ex);
        }
    }
    
    //  Source:  https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
    //  beginnersbook.com 
    public void hashPostSerialOut(HashMap hash)
    {
        try
      {
         FileInputStream FileInputStream = new FileInputStream(path + "hashPostSerial.ser");
         ObjectInputStream ObjectInputStream = new ObjectInputStream(FileInputStream);
         hash = (HashMap) ObjectInputStream.readObject();
         ObjectInputStream.close();
         FileInputStream.close();
      }
      catch(IOException ioe)
      {
         return;
      }
      catch(ClassNotFoundException c)
      {
         System.out.println("Class not found");
         return;
      }
      System.out.println("Deserialized HashMap..");
      // Display content using Iterator
      Set set = hash.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) 
      {
         Map.Entry mentry = (Map.Entry)iterator.next();
         System.out.print("key: "+ mentry.getKey() + " & Value: ");
         System.out.println(mentry.getValue());
      }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Find Node">
    public BtNode findBtNode(int key)
    {

        // Start at the top of the tree
        BtNode focusBtNode = root;

        // While we haven't found the BtNode
        // keep looking
        while (focusBtNode.key != key)
        {

            // If we should search to the left
            if (key < focusBtNode.key)
            {

                // Shift the focus BtNode to the left child
                focusBtNode = focusBtNode.leftChild;

            } else
            {

                // Shift the focus BtNode to the right child
                focusBtNode = focusBtNode.rightChild;

            }

            // The BtNode wasn't found
            if (focusBtNode == null)
            {
                return null;
            }

        }

        return focusBtNode;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Abstract Overrides">
    @Override
    public Graphics create()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translate(int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setColor(Color c)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPaintMode()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXORMode(Color c1)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Font getFont()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFont(Font font)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FontMetrics getFontMetrics(Font f)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getClipBounds()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clipRect(int x, int y, int width, int height)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setClip(int x, int y, int width, int height)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape getClip()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setClip(Shape clip)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillRect(int x, int y, int width, int height)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearRect(int x, int y, int width, int height)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawOval(int x, int y, int width, int height)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillOval(int x, int y, int width, int height)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawString(String str, int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Classes">
class BtNode
{

    int key;    // this is the anwer to the question, use the answer as the root, nodes and leaves
    String ArithQuestion; // this is the question string stored in the table

    BtNode leftChild;
    BtNode rightChild;

    BtNode(int key, String name)    // removing the string as it is irrelivant to what I am trying to do
    {

        this.key = key;
        this.ArithQuestion = name;

    }

    public String toString() // making changes to this to use int only and not worry about the rest of the text stored in the object
    {

        return ArithQuestion + " = " + key;

        /*
		 * return name + " has the key " + key + "\nLeft Child: " + leftChild +
		 * "\nRight Child: " + rightChild + "\n";
         */
    }

}
// </editor-fold>

// spare fold to copy paste
// <editor-fold defaultstate="collapsed" desc="user-description">
  
// </editor-fold>
