// 정점 N개의 트리에서 두 사람이 게임을 진행하려 한다.
// 각 정점은 1번부터 N번 까지 번호가 매겨져 있고 루트노드는 1번 노드이다.
// 게임은 서로 턴을 번갈아 가며 진행되고 트리 위에 놓을 수 있는 말과 함께 진행된다.
// 두 사람의 점수는 모두 0점으로 시작한다.

// 각 턴마다 두 사람은 다음과 같은 작업을 반복한다.

// 현재 말이 놓여 있는 정점의 번호만큼 자신의 점수에 더한다.
// 현재 말이 놓여 있는 정점의 자식 정점이 없다면 그대로 게임을 종료한다.
// 자식 정점이 존재한다면 자식 정점 중 원하는 자식 정점으로 말을 옮긴다.
// 게임이 종료되었을 때 선공의 점수가 후공의 점수보다 높거나 같다면 선공이 승리하고 아니라면 후공이 승리한다.
// 두 사람이 최적으로 플레이할 때, 처음 말이 놓여져 있는 정점의 번호에 따라 선공이 이기는지 후공이 이기는지 구해보자.

// 실패. 20/100

import java.io.*;
import java.util.*;

class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        static ArrayList<ArrayList<Integer>> tree;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tree.get(x).add(y);
            // tree.get(y).add(x);
        }

        for(int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            int firstScore = 0, secondScore = 0;
            int cnt = 0;
            int node = i;
            int change = 0;
            while(true) {
                visited[node] = true;

                if(cnt % 2 == 0) {
                    firstScore += node;
                } else {
                    secondScore += node;
                }
                boolean check = false;
                for(int next : tree.get(node)) {
                    if(!visited[next]) {
                        if(check) {
                            if(change > next) {
                                change = next;
                            }
                        } else {
                            change = next;
                        }
                        check = true;
                    }
                }
                
                if(!check) {
                    break;
                } else {
                    node = change;
                }

                cnt++;
            }

            if(firstScore >= secondScore) {
                bw.write("1" + "\n");
            } else {
                bw.write("0" + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}