// 성공
// https://www.acmicpc.net/problem/14888

package 약점_체크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 연산자_끼워넣기 {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int n;
	static int[] num;
	static int[] operator;
	static int[] temp;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		operator = new int[n - 1];
		temp = new int[n - 1];
		visited = new boolean[n - 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int idx = 0;
		for(int i = 0; i < 4; i++) {
			int temp = Integer.parseInt(st.nextToken());
			for(int j = 0; j < temp; j++) {
				operator[idx++] = i;
			}
		}
		
		permutation(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void permutation(int depth) {
		if(depth == n - 1) {
			calculator();
			return;
		}
		for(int i = 0; i < n - 1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				temp[depth] = operator[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void calculator() {
		int idx = 0;
		int result = num[idx++];
		for(int i = 0; i < n - 1; i++) {
			switch(temp[i]) {
				case 0:
					result += num[idx];
					break;
				case 1:
					result -= num[idx];
					break;
				case 2:
					result *= num[idx];
					break;
				case 3:
					result /= num[idx];
					break;
			}
			idx++;
		}
		if(result > max) {
			max = result;
		}
		if(result < min) {
			min = result;
		}
	}
}
