import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();    // number of plants
        int k = input.nextInt();    // number of friends
        int[] prices = new int[n];
        for(int i=0; i<n; i++)  prices[i] = input.nextInt();    // prices
        int m = n/k+1;  // number of plants a friend must buy at most
        int idx = n;
        Sort.mergeSort(prices,0, prices.length-1);   // increasing prices
        int totalCost = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<k && idx!=0; j++)    totalCost += (i+1)*prices[--idx];
        }
        System.out.println(totalCost);

        input.close();
    }
}
