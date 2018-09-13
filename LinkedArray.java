package linkedArray;

import java.util.Arrays;

/*-----------------------------------Class LinkedArray-------------------------------------*/

public class LinkedArray {

    public static void main(String[] args) {
        LinkArray<String> ad = new LinkArray(10);                        // Creation of Add Object
        
    /*--------------------------Adding Elements to LinkedArray list------------------------ ----*/        
        
        ad.add("10");      
        ad.add("20");
        ad.add("30");
        ad.add("40");
        ad.add("50");
        ad.add("60");
        ad.add("70");
        ad.add("80");
        ad.add("90");
        ad.add("100");
        ad.add("110");
        ad.add("120");                                                  // Insertiong element
        ad.add("700", 4);                                               // Inserting at index
        ad.remove(6);                                                   // Removing from index
        System.out.println("Reterived Element is -> "+ad.get(11));      // Retreiving element at index
        ad.set("500",11);                                               // Replacing the old element from given element at given index
        ad.remove(5, 7);                                                // Removing the element from and to spcified index
        System.out.println("index of 120 is -> "+ad.indexOf("120"));    // Returns the first index of sppcified element
        ad.printList();                                                 // Prints complete list
    }
}

/*----------------------------------------Class Add-> for adding element-----------------------------*/

class LinkArray<T>
{
    Node head;                                                           // Head Node handle
    final int max;                                                       // Maximum element in each array
    
    /*-----------------------------Default constructor with max value as 20--------------------------*/
    
    public LinkArray()
    {
        this(20);                                                       // Passing max to pamaterized constructor
    }
    
    /*-----Add Condtructor for user who provides maximum element that should stored in each array-----*/
    public LinkArray(int max)
    {
        this.max = max;
    }
    
    /*-----------------------------------Add Node-------------------------------------*/    
    
    void addNode(Node temp, T v)
    {
        Node temp2 = new Node(max);
        temp.setNext(temp2);
        temp2.setData(v);
    }
    
    //+++++++++++++ Add data
    
    /*-----------------------------------Add Element-------------------------------------*/
    
    void add(T v)
    {
        Node temp;
        if(head == null)
        {
            temp = new Node(max);
            head = temp;
        }
        else
        {
            temp = head;
        }
        while(temp.getNext() != null)
        {
            temp = temp.getNext();
        }
        if(temp.getCount() < max)
        {
            temp.setData(v);
        }
        else
        {
            addNode(temp, v);
        }   
    }

    /*------------------Insert data at specified index----------------------*/
    
    void add(T data, int idx)
    {
        try 
        {
            int index = idx/max;
            Node temp = head;
            for(int i = 0; i < index; i++)
            {
                temp = temp.getNext();
            }
            T da;
            while(temp != null)
            {
                int i;
                for(i = (idx%max) - 1; i < temp.getCount(); i++)
                {
                    da = (T)temp.getData(i);
                    temp.setData(data, i);
                    data = (T)da;
                }
                idx = 1;
                if(temp.getNext() == null && i ==  temp.getCount())
                {
                    temp.setData(data);
                }
                temp = temp.getNext();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    /*--------------------------------Return Element----------------------------*/
    
    T get(int idx)
    {
        int index = idx / max;
        Node temp = head;
        try{
            for(int i = 0; i < index; i++)
            {
                temp = temp.getNext();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return (T) temp.getData(idx%max);  
    }
    
    /*------- returns index of first occurance of specified element-----------------------*/
    
    int indexOf(T data)
    {
        int index = -1;
        Node temp = head;
        boolean flag = false;
        label : while(temp != null)
        {
            for(int i = 0; i < temp.getCount(); i++)
            {
                index++;
                if(temp.getData(index%max) == data)
                {
                    flag = true;
                    break label;
                }
            }
            temp = temp.getNext();
        }
        if(flag == true)
            return index;
        else
            return -1;
    }
    
    
    /*---------------------Replacing element with given element at specified index--------------------------*/   
    
    void set(T data, int idx)
    {
        int index = idx / max;
        Node temp = head;
        for(int i = 0; i < index; i++)
        {
            temp = temp.getNext();
        }
        temp.setData(data, idx%max);
    }
    
    /*------------------Remove the data at specified index----------------------*/
    
    T remove(int idx)
    {
        int index = idx/max;
        Node temp = head;
        for(int i = 0; i < index; i++)
        {
            temp = temp.getNext();
        }
        T data = (T)temp.getData(index);
        while(temp != null)
        {
            int i;
            for(i = (idx%max); i < temp.getCount() - 1; i++)
            {
                temp.setData((T)temp.getData(i+1), i);
            }
            if(temp.getNext() != null)
                temp.setData(temp.getNext().getData(0), i);
            if(temp.getNext() != null && temp.getNext().getCount() == 1)
            {
                temp.setNext(null);
            }
            temp = temp.getNext();
            idx = 0;
        }
        return data;
    }
    
    /*----------------- Removing all elements specified between form and to index--------------*/
    
    void remove(int from, int to)
    {
        if(from < to)
        {
            int indexFrom = from/max;
            Node temp = head;
            for(int i = 0; i < indexFrom; i++)
            {
                temp = temp.getNext();
            } 
            
            int indexTo = (from - to)/max;
            Node temp2 = temp;
            for(int i = 0; i < indexTo; i++)
            {
                temp2 = temp.getNext();
            }
            indexFrom = from;
            indexTo = to + 1;
            while(temp2 != null)
            {
                while(indexTo < temp2.getCount())
                {
                    if(temp.getCount() == indexFrom)
                    {
                        temp = temp.getNext();
                        indexFrom = 0;
                    }
                    temp.setData(temp2.getData(indexTo % max), indexFrom % max);
                    indexFrom++;                
                    indexTo++;
                }
                temp2 = temp2.getNext();
                indexTo = 0;
            }
            temp.setNext(null);
            temp.clearFrom(indexFrom);
        }
    }
    
    /*------------------finds the first occurance of that data and removes it--------------------------*/ 
    
    T remove(T data)
    {
        boolean match = false;
        Node temp = head;
        int index = 0;
        label : while(temp != null)
        {
            T arr[] = (T[]) temp.getData();
            for(int i = 0; i < arr.length; i++)
            {
                if(arr[i] == data)
                {
                    match = true;
                    break label;
                }
                else
                    index++;
            }
            temp = temp.getNext();
        }
        if(match == true)
            return remove(index);
        else
            return null;
    }
    
    /*-------------------------------clear------------------------------*/
    
    public void clear()
    {
        head = null;
    }
    
    /*---------------------Sorting The whole list----------------------*/
    /*---------------------Incomplete implementation---------------------*/
    public void sort()
    {
        Node temp = head;
        Node temp2;
        int count = 0;
        while(temp != null)
        {
            count++;
            temp.sort();
            temp = temp.getNext();
        }
        /* if count is odd then we will merge the last array after all the sorting is done till count - 1 array*/
        boolean lastArrayLeft = false;
        if(count % 2 != 0)
        {
            count = count - 1;
            lastArrayLeft = true;
        }
        int multiplication = 1;
        while(count == 1)
        {
            temp = head;;
            multiplication = multiplication * 2;
            count = count / 2;
            for(int i = 0; i < count; i++)
            {
                temp = temp.getNext();
                
                
                
            }
        }
        
    }
    
    /*-------------------------Incomplete Implementation-------------------------*/
    
    private void perfromMergeSort(Node temp, Node temp1, int length)
    {
        int index = 0;
        int index2 = 0;
        
        while(index != length && index2 != length)
        {
            Comparable left = (Comparable)temp.getData(index%max);
            Comparable right = (Comparable)temp1.getData(index2%max);
            if(left.compareTo(right) > 0)
            {
                T da = (T)temp.getData(index%max);
                
            }
        }
    }
    
    /*---------------------------------Printing Whole List--------------------------*/ 
    
    void printList()
    {
        Node temp = head;
        while(temp != null)
        {
            int length = temp.getCount();
            T arr[] = (T[]) temp.getData();         //TypeCasting Of Object into T
            for(int i = 0; i < length; i++)
            {
                System.out.println(""+arr[i]+"  "+i+"  "+temp); 
            }
            temp = temp.getNext();
        }
    }
}


class Node<T>
{
    private T data[];                                   // Array for storing data
    private Node next;                                  //Link to next node
    private int count = 0;                              // number of element in that array
    
    Node(){}            /*---------------Empty Node---------------*/

    Node(int length)        /*-----------------Node Constructor----------------------*/
    {
        this.data = (T[])new Object[length];           //Creating Generic Array
    }
    
    //+++++++++++++Count Getter+++++++++++
    
    int getCount() {             //--------------get current value of count--------------
        return count;
    }
    
    //+++++++++++++Data Setter Getter++++++++++
    
    void setData(T data) {         //--------------set data at current value of count--------------
        this.data[count++] = data;
    }
    
    void setData(T data, int index)      //----------------Setting data at that particualr index-------------
    {
        this.data[index] = data;
    }
    
    T[] getData() {            //-----------------get whole array -------------------------
        return data;
    }
    
    T getData(int index)          //-------------------returning data at that particular index----------
    {
        return this.data[index];
    }
    
    //+++++++++++++Next Getter Setter++++++++++++++
    
    void setNext(Node next) {        //-----------------set Node---------------------
        this.next = next;
    }
    
    Node getNext()          //----------------------get Next link-----------------------------
    {
        return next;
    } 
    
    void clearFrom(int indexFrom) {
        for(int i = indexFrom; i < count; i++)
        {
            data[i] = null;
        }
        this.count = indexFrom;
    }
    
    void sort()
    {
        Arrays.sort(data);
    }
}