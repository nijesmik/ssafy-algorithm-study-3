package programmers;

public class 프로그래머스_리틀_프렌즈_사천성 {
    static class Card {
        int r1, c1, r2, c2, size;
    }

    static char[][] map;

    public String solution(int m, int n, String[] board) {
        map = new char[board.length][];
        int cardCnt = 0;
        boolean[] visited = new boolean[26];
        Card[] cards = new Card[26];
        for (int i = 0; i < 26; ++i) {
            cards[i] = new Card();
        }

        for (int i = 0; i < board.length; ++i) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < map[i].length; ++j) {
                if (map[i][j] == '.' || map[i][j] == '*') {
                    continue;
                }
                int now = map[i][j] - 65;
                visited[now] = true;
                if (cards[now].size == 0) {
                    cards[now].r1 = i;
                    cards[now].c1 = j;
                    cards[now].size++;
                } else {
                    cards[now].r2 = i;
                    cards[now].c2 = j;
                    cardCnt++;
                }
            }
        }

        int prev = cardCnt;
        StringBuilder sb = new StringBuilder();
        while (true) {
            for (int i = 0; i < 26; ++i) {
                if (!visited[i]) {
                    continue;
                }
                Card card = cards[i];
                if (isPossible(card.r1, card.c1, card.r2, card.c2)) {
                    cardCnt--;
                    map[card.r1][card.c1] = map[card.r2][card.c2] = '.';
                    visited[i] = false;
                    char c = (char) (i + 65);
                    sb.append(c);
                    i = -1;
                }
            }
            if (prev == cardCnt) {
                break;
            }
            prev = cardCnt;
        }
        if (cardCnt == 0) {
            return sb.toString();
        } else {
            return "IMPOSSIBLE";
        }
    }

    static boolean isPossible(int r1, int c1, int r2, int c2) {
        if (r1 == r2) {
            int start = Math.min(c1, c2) + 1;
            int end = Math.max(c1, c2);
            for (int i = start; i < end; ++i) {
                if (map[r1][i] != '.') {
                    return false;
                }
            }
            return true;
        } else if (c1 == c2) {
            int start = Math.min(r1, r2) + 1;
            int end = Math.max(r1, r2);
            for (int i = start; i < end; ++i) {
                if (map[i][c1] != '.') {
                    return false;
                }
            }
            return true;
        } else {
            // r1c1이 왼쪽에 있는거로 고정
            if (c1 > c2) {
                c1 ^= c2;
                c2 ^= c1;
                c1 ^= c2;
                r1 ^= r2;
                r2 ^= r1;
                r1 ^= r2;
            }
            // 왼쪽이 더 높거나 낮거나
            boolean flag1 = true; // 가로먼저
            boolean flag2 = true; // 세로방향
            if (r1 < r2) {
                for (int i = c1 + 1; i <= c2; ++i) {
                    if (map[r1][i] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = r1 + 1; i < r2; ++i) {
                    if (map[i][c2] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = r1 + 1; i <= r2; ++i) {
                    if (map[i][c1] != '.') {
                        flag2 = false;
                        break;
                    }
                }

                for (int i = c1 + 1; i < c2; ++i) {
                    if (map[r2][i] != '.') {
                        flag2 = false;
                        break;
                    }
                }
            } else {
                for (int i = c1 + 1; i <= c2; ++i) {
                    if (map[r1][i] != '.') {
                        flag1 = false;
                        break;
                    }
                }
                for (int i = r1 - 1; i > r2; --i) {
                    if (map[i][c2] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = r1 - 1; i >= r2; --i) {
                    if (map[i][c1] != '.') {
                        flag2 = false;
                        break;
                    }
                }
                for (int i = c1 + 1; i < c2; ++i) {
                    if (map[r2][i] != '.') {
                        flag2 = false;
                        break;
                    }
                }
            }
            return flag1 || flag2;
        }

    }


}
