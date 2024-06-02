package programmers;

import java.util.Arrays;

public class 프로그래머스_요격_시스템 {
	public int solution(int[][] targets) {
		Arrays.sort(targets, (a, b) -> {
			if (a[0] != b[0]) {
				return a[0] - b[0];
			}
			return b[1] - a[1];
		});

		int ans = 1;
		int index = 1;
		int start = targets[0][0];
		int end = targets[0][1];

		while (index < targets.length) {
			if (targets[index][0] < end) {
				start = targets[index][0];
				end = Math.min(targets[index][1], end);
				index++;
			} else {
				start = targets[index][0];
				end = targets[index][1];
				ans++;
				index++;
			}
		}
		return ans;
	}
}
