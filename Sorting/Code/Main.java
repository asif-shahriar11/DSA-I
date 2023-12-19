import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();
        boolean running = true;
        int n, order;
        while (running) {
            System.out.println("Enter any positive integer as the number of terms. To quit, enter any non-numeric key :");
            try {
                n = input.nextInt();
                input.nextLine();
                System.out.println("Select the order of generating integers.");
                System.out.println("For ascending order ... Enter 1");
                System.out.println("For descending order ... Enter 2");
                System.out.println("For random order ... Enter 3");
                order = input.nextInt();
                input.nextLine();
                if(order <1 || order >3)    {
                    System.out.println("Invalid order.");
                    throw new ArithmeticException();
                }
                int[] a = new int[n];
                if(order == 1) {
                    for(int i=0; i<n; i++)  a[i] = 10*i+random.nextInt(9);
                } else if(order == 2) {
                    for(int i=n-1; i>=0; i--)   a[n-i-1] = 10*i+random.nextInt(9);
                } else if(order ==3) {
                    for(int i=0; i<n; i++)  a[i] = random.nextInt();
                }
                System.out.print("Input order : ");
                if(order == 1) System.out.println("Ascending");
                else if(order == 2) System.out.println("Descending");
                else if(order == 3) System.out.println("Random");
                //System.out.println("Automatically generated array: ");
                //for(int x:a) System.out.print(x+" ");
                System.out.println();
                System.out.println();

                int[] a1 = new int[n];
                int[] a2 = new int[n];

                // sorting
                double startingTime,endingTime, quickSortingTime, mergeSortingTime;
                double totalQuick=0.0, totalMerge=0.0;
                //*
                System.out.println("Sorting ...");
                System.out.println();
                int iter = 5;
                for(int v=0; v<iter; v++) {
                    // copying
                    for(int i=0; i<n; i++) {
                        a1[i] = a[i];
                        a2[i] = a[i];
                    }

                    startingTime = System.nanoTime();
                    Sort.mergeSort(a1,0, a1.length-1);
                    endingTime = System.nanoTime();
                    mergeSortingTime = (endingTime-startingTime)/1000000;
                    totalMerge += mergeSortingTime;

                    //*/
                    startingTime = System.nanoTime();
                    Sort.quickSort(a2,0,a2.length-1);
                    endingTime = System.nanoTime();
                    quickSortingTime = (endingTime-startingTime)/1000000;
                    totalQuick += quickSortingTime;

                    // output
                    System.out.println("Sorted.");
                    System.out.println();
                    System.out.println("Time taken by MergeSort : "+mergeSortingTime+" milli-seconds");
                    System.out.println();
                    //for(int x:a2) System.out.print(x+" ");
                    System.out.println("Time taken by QuickSort : "+quickSortingTime+" milli-seconds");
                    System.out.println();
                    System.out.println();
                }

                ///*
                // printing arrays
                System.out.println("Merge Sorted Array\t\tQuick Sorted Array");
                System.out.println("------------------\t\t------------------");
                for (int i=0; i<n; i++) System.out.printf("%10d\t\t\t%15d\n",a1[i],a2[i]);

                 //*/
                System.out.println();
                System.out.println();
                System.out.println("Average Merge : "+totalMerge/iter);
                System.out.println("Average quick : "+totalQuick/iter);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Quitting ...");
                running = false;
            }
        }

        input.close();
    }
}
