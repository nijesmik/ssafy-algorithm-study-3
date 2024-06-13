import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] buildings;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    buildings = new int[N+1];
    for(int i = 1; i <= N; i++) {
      buildings[i] = Integer.parseInt(st.nextToken());
    }

    int answer = 0;
    for(int i = 1; i <= N; i++) {
      answer = Math.max(answer, getBuildingCount(i));
    }

    System.out.println(answer);
  }

  static int getBuildingCount(int idx) {
    int count = 0;
    int y = buildings[idx];
    double tmp = 0;
    for(int i = idx-1; i > 0 ; i--) {
      double gradient = getGradient(idx, y, i, buildings[i]);
      if(i==idx-1 || tmp > gradient) {
        tmp = gradient;
        count++;
      }
    }

    for(int i = idx+1; i <= N; i++) {
      double gradient = getGradient(idx, y, i, buildings[i]);
      if(i == idx+1 || tmp < gradient) {
        tmp = gradient;
        count++;
      }
    }
    return count;
  }

  static double getGradient(int x, int y, int r, int c) {
    return (double) (y - c) / (x - r);
  }
}
