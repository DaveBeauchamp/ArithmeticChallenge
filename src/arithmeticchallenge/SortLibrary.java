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
public class SortLibrary 
{

    // Done
    public static void bubbleSort(ArrayList<Object[]> arr)  
    {
        
        for(int j=0; j<arr.size(); j++) // j is the top row
        {  
            for(int i=j+1; i<arr.size(); i++) // i is the rows below j
            {  
                // this if has another version though it is based on strings 
                if(Integer.parseInt(arr.get(i)[2].toString()) < Integer.parseInt(arr.get(j)[2].toString()))
                {  
                   Object[] words = arr.get(j); 
                   arr.set(j, arr.get(i));
                   arr.set(i, words);
                }  
            }
            
        }  
    }
    
    // Done
    public static void InsertionSort(ArrayList<Object[]> arr) 
    { 
        int i;
        int j;
        Object[] key;
        
        for(j = 1; j < arr.size(); j++) // j is the top row
        {  
            
            key = arr.get(j);
            
            for(i = j -1; (i >= 0) && (Integer.parseInt((String)arr.get(i)[0]) < Integer.parseInt((String)key[0])); i--) 
            {  
                arr.set(i+1, arr.get(i));
            }
            
            arr.set(i+1, key);
            
            
        }  

        
    }
    

    // Done
    public static void SelectionSort(ArrayList<Object[]> arr) // fix this and add all of this to the main project to debug
    {
        
        int i;
        int j;
        int first;
        Object[] temp;
        
        for(i = arr.size() - 1; i > 0; i--) // could flip the operators to make it work the other way. 
        {  
            first = 0;
            for(j = 1 ; j < i; j++) 
            {  
                if (Integer.parseInt(arr.get(j)[3].toString()) < Integer.parseInt(arr.get(first)[3].toString()));
                    first = j;

            }
            
            temp = arr.get(first);
            //key = arr.get(Integer.parseInt(first[3].toString()));
            arr.set(first, arr.get(i));
            //arr.set(Integer.parseInt(first[3].toString()), arr.get(i));
            arr.set(i, temp);                       
        }  
    }
    
    //----------------------------------------------------------------------------------------------------------------------------

    
    
    
    // okay so from what I can tell sorting arrayLists is a real pain because of the structure of arrayLists. I don't care anymore.
    // lots of fuctions, methods and other things to help with arrays but not ArrayLists. Not imposible but annoying.
    // anything south of here is a lost cause at the moment, can be done I just don't care.
    public static void mergeSort(ArrayList<Object[]> arr)
    {
        
        
//        // ordering the main list yes it is cheating 
//        for(int j=0; j<arr.size(); j++) // j is the top row
//        {  
//            for(int i=j+1; i<arr.size(); i++) 
//            {  
//                
//                if(Integer.parseInt(arr.get(i)[3].toString()) < Integer.parseInt(arr.get(j)[3].toString()))
//                {  
//                   Object[] Order = arr.get(j); 
//                   arr.set(j, arr.get(i));
//                   arr.set(i, Order);
//                }  
//            }
//            
//        }
      
       ArrayList<Object[]> cloneList = (ArrayList<Object[]>)arr.clone(); // this seems to work, looks like casting the type of arr as an object ish
       ArrayList<Object[]> bottom = new  ArrayList();
       ArrayList<Object[]> top = new  ArrayList();
       
       
       
       
       
       
//       bubbleSort(bottom);
//       bubbleSort(top);
       // now  i have 4 arrayLists the original the clone to be adjusted and the the two split to and bottom
       
       // could make if statements for the amount of arrayLists
       // if arrayList.size() is greater than one (a single record or index 0)
       // create new arraylist. probably use a for loop to manage the naming temp1, temp2, temp3 etc.
       // not sure how to implement it yet though probably not to hard with some thought
       
       for (int kek = 0; kek <= arr.size()-1; kek++)
       {
            if (kek <= arr.size()/2 -1)
            {
                bottom.add(arr.get(kek));
            }
            if (kek >= arr.size()/2)
            {
                top.add(arr.get(kek));
            }
       }
     
       int bottomPointer = 0;
       int topPointer = 0;
       int clonePointer= 0;
       
       while (clonePointer <= cloneList.size()-1)
       {
           if (clonePointer == cloneList.size()-1) break;
        
        if (bottomPointer > bottom.size()-1) bottomPointer = bottom.size();
        if (topPointer > top.size()-1) topPointer = top.size();    
       
           if (Integer.parseInt(bottom.get(bottomPointer)[3].toString()) <= Integer.parseInt(top.get(topPointer)[3].toString())) 
           {
               cloneList.set(clonePointer, bottom.get(bottomPointer));
               bottomPointer++;
               clonePointer++;
               
               if (bottomPointer > bottom.size()-1) 
           {
               int topPointerPlusOne = topPointer+1;

               bottomPointer = bottom.size();
               if(Integer.parseInt(top.get(topPointer)[3].toString()) <= Integer.parseInt(top.get(topPointerPlusOne)[3].toString()))
               {
                    cloneList.set(clonePointer, top.get(topPointer));
                    topPointer++;
                    clonePointer++;
                    int topMax = top.size()-1;
                    int cloneMax = cloneList.size()-1;
                    if (clonePointer == cloneMax)
                    {
                        cloneList.set((cloneMax), top.get(topMax));
                    }

               }
           }
               
               
           }
           else //(Integer.parseInt(bottom.get(bottomPointer)[3].toString()) > Integer.parseInt(top.get(topPointer)[3].toString()))
           {
               cloneList.set(clonePointer, top.get(topPointer));
               topPointer++;
               clonePointer++;
               
               if (topPointer > top.size()-1) 
               {
                   
                int bottomPointerPlusOne = bottomPointer+1;
                topPointer = bottom.size();
                   if(Integer.parseInt(bottom.get(bottomPointer)[3].toString()) <= Integer.parseInt(bottom.get(bottomPointerPlusOne)[3].toString()))
                    {
                        cloneList.set(clonePointer, bottom.get(bottomPointer));
                        bottomPointer++;
                        clonePointer++;
                        int bottomMax = top.size()-1;
                        int cloneMax = cloneList.size()-1;
                        if (clonePointer == cloneList.size()) cloneList.set(cloneMax, bottom.get(bottomMax));
                    }
               }
             
           }
           
        }
       
           for(int pp = 0; pp <= cloneList.size()-1; pp++)
           {
               arr.set(pp, cloneList.get(pp));
              
           }

    }
    
    
//    // make with arrayLists
//    public static void merge(ArrayList<Object[]> orig, ArrayList<Object[]> aux, int start, int mid, int end) {
//        int i, j, z = start;
//
//        if(orig[mid] <= orig[mid+1])return; 
//        
//        for(i=start, j = mid+1; i!=mid+1 || j!=end+1;){
//            if(i==mid+1)               while(j!=end+1){ aux[z++] = orig[j++]; }
//            else if(j==end+1)          while(i!=mid+1){ aux[z++] = orig[i++]; }
//            else if(orig[i]<=orig[j])  aux[z++] = orig[i++];
//            else                       aux[z++] = orig[j++];
//        }    
//        System.out.println(Arrays.toString(orig));
//        System.out.println("start = "+start+" mid = "+mid+" end = "+end);
//        System.out.println(Arrays.toString(aux)+"\n");
//        System.arraycopy(aux, start, orig, start, end-start+1); // change this to a more appropriate line 
//    }
//
//    public static void sort(ArrayList<Object[]> orig, int start, int end) {
//        ArrayList<Object[]> aux = (ArrayList<Object[]>)orig.clone();
//        
//        for (int i = 0; i <= orig.size()-1; i++)
//        {
//            aux.set(i, null); // empty the arraylist
//        }
//        
//        int size = orig.size()-1; // setting the size value
//        
//        for (int count1 = 1; count1 < size; count1 *= 2) // count1 changes by double
//        {           // ok it gets a bit crazy, this is borrowed code
//            for (int count2 = 0; count2 < size - count1; count2 += count1 + count1) 
//            {
//                merge(orig, aux, count2, count2 + count1 - 1, Math.min(count2 + count1 + count1 - 1, size-1));
//            }
//        }
//    }
    
    
}


    
    
    
    
    

