import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Main {

    public static int waysToSum(int target, int[] faces) {

        int mod = 1000000007;

        int[][] sumTable = new int[faces.length][target+1];   // initialized with 0
        // value of sumTable[i][j] is the way to achieve sum 'j' using 'i' dices
        sumTable[0][0] = 1; // trivial case: there is 1 way to get sum '0' using '0' dice
        for(int i=1; i< faces.length; i++) {
            for(int j=i; j<=target; j++) {
                sumTable[i][j] = (sumTable[i][j-1] + sumTable[i-1][j-1]) % mod;

                // if j(sum) is greater than faces[i] (maximum face value of the i-th dice)
                // then we cannot achieve that sum by using any face values from 0 to faces[i] of that dice and
                // some face values of the previous dices, so these combinations need to be removed
                if(j>faces[i])  sumTable[i][j] = (sumTable[i][j] - sumTable[i-1][j-faces[i]-1]) % mod;
            }
        }

        return sumTable[faces.length-1][target];
    }

    public static void main(String[] args) {

        // taking input from file
        LinkedList<String> fileInput = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            fileInput.add(br.readLine());
            fileInput.add(br.readLine());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] inputs;
        inputs = fileInput.get(0).split(" ");   // first line of input
        int n = Integer.parseInt(inputs[0]);    // number of dice
        int targetSum = Integer.parseInt(inputs[1]);    // target sum
        inputs = fileInput.get(1).split(" ");   // second line of input
        int[] faces = new int[n+1];
        for(int i=1; i<=n; i++)  faces[i] = Integer.parseInt(inputs[i-1]);
        System.out.println(waysToSum(targetSum, faces));
    }
}
