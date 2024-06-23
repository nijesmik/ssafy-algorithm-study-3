package y24Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2097 {

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(minSquare(N));

    }

    public static int minSquare(int N) {

        if (N == 1 || N == 2) {
            return 4;
        }

        int a = (int) Math.round(Math.sqrt(N));
        if (a * a >= N) {
            return (a - 1) * 4;
        } else {
            return (a - 1) * 2 + (a * 2);
        }

    }
}
