import java.util.Scanner;

public class NonRepeatingCharacter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<Character> queue = new Queue<>();

        System.out.println("Input a string: ");
        String stringOld = input.nextLine();
        char [] charArray = stringOld.toCharArray();
        int [] freq = new int[26];

        StringBuilder stringBuilder = new StringBuilder();

        for (char c : charArray) {
            if (freq[c - 'a'] == 0) queue.enqueue(c);
            freq[c - 'a'] += 1;
            while((!queue.isEmpty()) && (freq[queue.front() - 'a'] != 1))   queue.dequeue();
            if (queue.isEmpty()) stringBuilder.append('#');
            else stringBuilder.append(queue.front());
        }

        String stringNew = new String(stringBuilder);

        System.out.println(stringNew);

        input.close();

    }
}
