import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int targetIndex;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        targetIndex = Integer.parseInt(bf.readLine());
        System.out.println(findMoo(0, 3));  // 시작하는 k=0, 길이는 S0 = "moo"의 길이 3
    }

    // k는 재귀의 깊이, length는 현재 Sk의 길이
    static char findMoo(int k, int length) {
        // k와 상관 없이 적용
        if (targetIndex <= 3) {
            if (targetIndex == 1) return 'm';
            else return 'o';
        }

        int prevLength = length;
        int middleLength = k + 4;  // "m" 다음에 오는 "o"의 개수는 k + 3, 그리고 "m" 자신
        int totalLength = 2 * prevLength + middleLength;

        if (targetIndex > totalLength) {
            // 다음 S(k+1)로 이동
            return findMoo(k + 1, totalLength);
        } else {
            if (targetIndex <= prevLength) {
                // 앞쪽 Sk에 위치한 경우
                return findMoo(k - 1, (prevLength - (k + 3)) / 2);
            } else if (targetIndex > prevLength + middleLength) {
                // 뒤쪽 Sk에 위치한 경우
                targetIndex -= (prevLength + middleLength);
                return findMoo(k - 1, (prevLength - (k + 3)) / 2);
            } else {
                // 중간의 "mooo..." 부분에 위치한 경우
                int middleIndex = targetIndex - prevLength;
                if (middleIndex == 1) return 'm';
                else return 'o';
            }
        }
    }
}
