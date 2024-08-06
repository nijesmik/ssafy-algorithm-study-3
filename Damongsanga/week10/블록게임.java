import java.util.ArrayList;
import java.util.List;



/**블록 게임
 * 알고리즘 : 구현
 * 핵심 아이디어 : 맨 아랫 줄이 연속으로 1개인 블록은 절대 없어질 수 없다
 * 구현 방법
 * 1. 삭제될 가능성이 있는 블록들을 List에 따로 저장한다. 이 때 검은 블록이 들어와야 할 위치를 required에 Node 클래스로 저장한다
 * 1.1 여기서 밑줄이 2개짜리인 세로로 긴 블록은 1개의 검은 블록만 있어도 지울 수 있다 (어자피 수직으로 떨어지기 때문에 수직으로 연속 2개의 검은 블록이 들어올 수 있는지 고려할 필요가 없다)
 * 2. 블록 List을 반복문 돌면서 블록이 삭제되지 않을 때까지 검은 블록을 떨어뜨린다. 여기서 블록의 빈칸 열만 검사한다. (불필요한 영역까지 검사할 필요 없음)
 * 3. 블록의 빈칸이 모두 지워지면 (검은 블록으로 채울 수 있다고 판단되면) 블록을 삭제하고 answer를 1 올린다.
 * */

class 블록게임 {
    static class Block {
        int num;
        List<Node> required;

        public Block(int num, List<Node> required) {
            this.num = num;
            this.required = new ArrayList<>(required);
        }
    }

    static class Node {
        int r; int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] board) {

        int N = board.length;
        List<Block> blocks = new ArrayList<>();

        boolean[] unableNumber = new boolean[201];
        unableNumber[0] = true;

        for (int i = N-1; i > 0; i--) {
            for (int j = 0; j < N; j++) {
                int number = board[i][j];
                if (unableNumber[number]) continue;
                if (j == N-1 || number != board[i][j+1]) unableNumber[number] = true;
                else {
                    if (j == N-2 || number != board[i][j+2]){
                        if (number == board[i-1][j]) blocks.add(new Block(number, List.of(new Node(i-1, j+1))));
                        else blocks.add(new Block(number, List.of(new Node(i-1, j))));
                        j++;
                    } else {
                        if (number == board[i-1][j]) blocks.add(new Block(number, List.of(new Node(i-1, j+1), new Node(i-1, j+2))));
                        else if (number == board[i-1][j+1]) blocks.add(new Block(number, List.of(new Node(i-1, j), new Node(i-1, j+2))));
                        else blocks.add(new Block(number, List.of(new Node(i-1, j), new Node(i-1, j+1))));
                        j += 2;
                    }
                }
            }
        }

        int answer = 0;

        boolean[] broken = new boolean[201];
        broken[0] = true;

        while (true){
            int count = blocks.size();
            List<Node> nodesToRemove = new ArrayList<>();
            List<Block> blocksToRemove = new ArrayList<>();

            for (Block block: blocks) {
                for (Node node : block.required){
                    for (int r = 0; r < N; r++) {
                        if (broken[board[r][node.c]]) continue;
                        if (r-1 == node.r){
                            nodesToRemove.add(node);
                        } else {
                            break;
                        }
                    }
                }

                block.required.removeAll(nodesToRemove);
                nodesToRemove.clear();

                if (block.required.isEmpty()){
                    broken[block.num] = true;
                    blocksToRemove.add(block);
                    answer++;
                }
            }

            blocks.removeAll(blocksToRemove);
            blocksToRemove.clear();

            if (count == blocks.size()) break;
        }

        return answer;

    }
}