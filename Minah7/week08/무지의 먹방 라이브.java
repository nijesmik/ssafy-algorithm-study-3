// 실패해서 클론 코딩 함. 
// 다시 풀어보기.

import java.util.ArrayList;
import java.util.Collections;

class Solution {
	public int solution(int[] food_times, long k) {
		ArrayList<Integer> st = new ArrayList<>();
		st.add(0);
		for(int x : food_times) {
			st.add(x);
		}
		Collections.sort(st);
		int rest = food_times.length;
		int ans = -1;
		
		for(int i = 1; i < st.size(); i++) {
			long time = (long) (st.get(i) - st.get(i - 1)) * rest;
			if(time > k) {
				k = k % rest;
				int cnt = 0;
				for(int j = 0; j < food_times.length; j++) {
					if(food_times[j] < st.get(i)) {
						continue;
					}
					if(cnt == k) {
						ans = j+1;
						break;
					}
					cnt++;
				}
				break;
			}
			k -= time;
			rest--;
		}
		return ans;
	}
}