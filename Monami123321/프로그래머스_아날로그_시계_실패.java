package programmers;

public class 프로그래머스_아날로그_시계_실패 {
	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		int start = h1 * 3600 + m1 * 60 + s1;
		int end = h2 * 3600 + m2 * 60 + s2;
		double[] prev = getAngle(start);
		int cnt = 0;
		if(start == 0 || start == 3600 * 12) {
			cnt++;
		}
		for(int i = start + 1; i <= end; ++i) {
			double[] now = getAngle(i);
			// if(prev[2] == 354) {
			//     now[2] = 360;
			// }
			// 초침 - 분침
			if(prev[2] < prev[1] && now[1] <= now[2]) {
				cnt++;
			}
			// 초침 - 시침
			if(prev[2] < prev[0] && now[0] <= now[2]) {
				cnt++;
			}

			// 분침 - 시침
			if(prev[1] < prev[0] && now[0] <= now[1]) {
				cnt++;
			}
			// if(prev[2] == 354) {
			//     now[2] = 0;
			// }
			prev = now;
		}

		if(start < 3600 * 12 &&  3600 * 12 <= end) {
			cnt -= 2;
		}
		return cnt;

	}
	static double[] getAngle(int t) {
		// 시침 -> 3600 * 12 동안 한 바퀴 -> 1초에 360 / 3600 * 12 도
		// 분침 -> 3600 초에 360도
		double hour = ((double)t / 120) % 360;
		double minute = ((double)t / 10) % 360;
		double second = (6 * t) % 360;
		return new double[] {hour, minute, second};
	}
}
