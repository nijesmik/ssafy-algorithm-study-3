// 성공
// https://www.acmicpc.net/problem/1806

package 약점_체크;

import java.util.*;
import java.io.*;

public class Partial_sum {
	static int n;
	static int s;
	static int[] num;
	static int result;
	
	public static void getPartialSum () {
		for(int i = 0; i < n - 1; i++) {
			int sum = num[i];
			for(int add = i + 1; add < n; add++) {
				sum += num[add];
				if(sum >= s) {
					int cnt = add - i + 1;
					if(result == 0) {
						result = cnt;
					} else if(result > cnt) {
						result = cnt;
					}
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		num = new int[n];
		result = 0;
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			if(num[i] >= s) {
				result = 1;
			}
		}
		
		if(result != 1) {
			getPartialSum();
		}
		
		System.out.println(result);
	}
}
