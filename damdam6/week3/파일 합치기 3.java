package y24Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek13975 {

    static int T;

    public static void main(String[] args) throws Exception {
        // 걍 pq만 쓰면 되는거 맞음?
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//
//        2
//        4
//        40 30 30 50
//        15
//        1 21 3 4 5 35 5 4 3 5 98 21 14 17 32

        T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {

            int N = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            PriorityQueue<Long> pqu = new PriorityQueue<>();

            for (int j = 0; j < N; j++) {
                pqu.add(Long.parseLong(st.nextToken()));
            }

            Long pay = 0L;
            while (pqu.size() > 1) {
                Long a = pqu.poll();
                Long b = pqu.poll();
                pay += a + b;
                pqu.add(a + b);
            }
            System.out.println(pay);

        }

    }
}
