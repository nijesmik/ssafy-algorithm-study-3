package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_17478_재귀함수가_뭔가요 {
	static StringBuilder sb = new StringBuilder();
	static String[] arr = {"\"재귀함수가 뭔가요?\"", "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
		"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.", "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
		"라고 답변하였지."};
	static String salt = "____";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		call(Integer.parseInt(br.readLine()));
		System.out.print(sb);
		br.close();
	}

	static void call(int n) {
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		recurr(n, "");
	}

	static void recurr(int n, String prefix) {
		if (n == 0) {
			sb.append(prefix).append(arr[0]).append("\n");
			sb.append(prefix).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			sb.append(prefix).append(arr[4]).append("\n");
			return;
		}
		for (int i = 0; i < 4; i++) {
			sb.append(prefix).append(arr[i]).append("\n");
		}
		recurr(n - 1, prefix + salt);
		sb.append(prefix).append(arr[4]).append("\n");
	}
}
