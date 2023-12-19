/**
 * Offline 01: A resizable array data structure
 * by Asif Shahriar, 1805040
 */

class Array {
    private String [] array;
    private int size;
    private int top = -1;

    Array() {
        size = 10;  // Array of predefined size = 10
        array = new String[10];
    }

    Array(int n) {
        size = n;   // Array of user defined size
        array = new String[n];
    }

    Array(String[] A) {
        size = A.length;
        array = new String[A.length];
        for(int i=0; i<A.length; i++)   array[i] = A[i];
        top = A.length-1;
    }

    public Array getArray() { return this; }    // returns the Array itself

    public String getAnElement(int i)  {
        // checking validity of index
        if(i<0) throw new IndexOutOfBoundsException("Cannot return element: Index "+i+" is invalid");
        else if(i>=length()) throw new IndexOutOfBoundsException("Cannot return element: Index "+i+" is out of length "+length());
        else return array[i]; }  // returns the element at i-th index of the Array

    public void add(String s) {
        // inserting a string element at the end of the Array
        // if the Array is already full, size is increased by 1 and then the new element is placed
        if(top==size-1) {
            String[] temp = new String[size];  // temporary Array to hold the elements of the original array
            for(int i=0; i<size; i++)   temp[i] = array[i];
            array = new String[size*2]; // size of the original Array is increased
            size *= 2;
            for(int i=0; i<temp.length; i++) {
                array[i] = temp[i];   // placing the elements back into the original Array of increased size
            }
        }
        array[++top] = s;
    }

    public void add(int i, String s) {
        // checking validity of index
        if(i<0) throw new IndexOutOfBoundsException("Cannot add element: Index "+i+" is invalid");
        // elements of Array cannot be disjoint
        else if(i>length()) throw new IndexOutOfBoundsException("Cannot add element: Index "+i+" is out of length "+length());
        else {
            // the string element is inserted at the i-th index
            // if there is an existing element at the i-th index, that and the following elements are pushed
            // by one position to accommodate the new element
            // size of the array is increased if necessary
            if(top==size-1) {
                String[] temp = new String[size];
                for(int k=0; k<size; k++)   temp[k] = array[k];
                array = new String[size*2];
                size *= 2;
                for(int k=0; k<temp.length; k++) {
                    array[k] = temp[k];
                }
            }
            ++top;
            for(int j=top; j>i; j--) array[j] = array[j-1]; // pushing the existing elements by one position
            array[i] = s;
        }
    }

    public void remove(String s) {
        // removing all string elements of the Array that match with the given string
        // this method is case-sensitive
        String[] temp = new String[size];
        for(int i=0; i<size; i++)   temp[i] = array[i];
        for(int i=0; i<temp.length; i++) {
            if(temp[i]!=null) {
                if(temp[i].equals(s))   temp[i] = null;
            }
        }
        top = -1;
        for(int i=0; i<temp.length; i++) {
            if(temp[i]!=null)   array[++top] = temp[i];
        }
        int i = top+1;
        while(i<size)   array[i++] = null;

    }

    public int[] findIndex(String s) {
        // returns all indices of the Array where the given string is placed
        int count = 0;
        for(String s1:array) {
            if(s1!=null) {
                if(s1.equalsIgnoreCase(s))  count++;
            }
        }
        int[] indices = new int[count]; // integer array to hold the indices
        count = 0;
        for(int i=0; i<size; i++) {
            if(array[i]!=null) {
                if(array[i].equalsIgnoreCase(s))    indices[count++] = i;
            }
        }
        return indices;
    }

    public Array subArray(int start, int end) {
        // returns a subArray which holds the elements of the original Array
        // from index 'start' to 'end', both inclusive
        Array temp = new Array(end-start+1);
        for(int i=start; i<=end; i++) temp.add(array[i]);
        return temp;
    }

    public void merge(Array a, Array b) {
        // both Arrays must be sorted
        array = new String[a.length()+b.length()];  // previous elements will be discarded
        top = -1;
        int i=0, j=0;
        while(i<a.length() && j<b.length()) {
            if(a.getAnElement(i).compareTo(b.getAnElement(j))<0)    array[++top] = a.getAnElement(i++);
            else    array[++top] = b.getAnElement(j++);
        }
        // now some elements of only one sorted Array may remain
        // adding those elements to the Array
        while(i<a.length()) array[++top] = a.getAnElement(i++);
        while(j<b.length()) array[++top] = a.getAnElement(j++);
    }

    public int length() { return top+1; }    // returns the number of elements in the Array
    // not the pre-defined/user-defined size

    public int capacity() {return size; }   // return the capacity of the Array

    public boolean isEmpty() { return top==-1; }    // returns whether the Array is empty

    // printing the Array
    public void print() {
        try {
            for(int i=0; i<length(); i++) System.out.print(getAnElement(i)+" ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        System.out.println();
    }

}

public class Main {
    public static void main(String[] args) {

        // Array with user defined size
        Array array = new Array(10);
        // adding elements
        array.add("Marcedes");
        array.add("Audi");
        array.add("Rolls-Royce");
        array.add("Ferrari");
        array.add("McLaren");

        // printing the elements of the Array
        array.print();

        // adding some more elements
        array.add("Tesla");
        array.add("AstonMartin");
        array.add("Bughatti");
        array.print();

        // adding element at a certain index
        try {
            array.add(3, "Harrier");
            array.add(5,"Marcedes");
            array.add(1,"VoxWagon");
            array.add(11, "LandRover");   // this will add
            array.add(15, "Lexus"); // but this will not add
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        // trying an invalid index
        try {
            array.add(-1, "KIA");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        array.print();

        // getting an element from the Array
        try {
            System.out.println("Element at index 7: "+array.getAnElement(7));
            System.out.println("Element at index 25: "+array.getAnElement(25)); // this will not print
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        // Length and capacity
        System.out.println("Length: "+array.length());
        System.out.println("Capacity: "+array.capacity());

        // finding the indices of a certain element
        int[] index;
        index = array.findIndex("Marcedes");
        for(int i:index) System.out.print(i+" ");
        System.out.println();

        // getArray
        Array newArray = array.getArray();
        newArray.print();

        // removing an element
        array.remove("Marcedes");
        array.print();

        // subArray
        Array array1 = array.subArray(3,6);
        array1.print();

        // merge
        Array array2 = new Array(3);
        Array array3 = new Array(4);
        array2.add("Audi");
        array2.add("Marcedes");
        array2.add("Toyota");
        array3.add("Bughatti");
        array3.add("Ferrari");
        array3.add("Harrier");
        array3.add("Tesla");
        Array array4 = new Array();
        array4.merge(array2,array3);
        array4.print();

    }
}
