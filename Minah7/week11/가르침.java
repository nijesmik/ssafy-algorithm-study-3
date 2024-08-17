// 성공
// https://www.acmicpc.net/problem/1062

package 약점_체크;

import java.util.*;
import java.io.*;

public class 가르침 {
	static int n;
	static int k;
	static int result;
	static String[] word;
	static boolean[] visited = new boolean[26];
	
	public static void count() {
		int cnt = 0;
		for(int s = 0; s < n; s++) {
			boolean read = true;
			for(int i = 0; i < word[s].length(); i++) {
				if(!visited[word[s].charAt(i) - 'a']) {
					read = false;
					break;
				}
			}
			if(read) {
				cnt++;
			}
		}
		result = Math.max(result, cnt);
	}
	
	public static void dfs(int start, int depth) {
		if(depth == k - 5) {
			count();
			return;
		}
		for(int i = start; i < 26; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		word = new String[n];
		for(int s = 0; s < n; s++) {
			String str = br.readLine();
			word[s] = str.substring(4, str.length() - 4);				
		}
		
		if(k == 26) {
			result = n;
		} else if(k < 5) {
			result = 0;
		} else {
			result = 0;
			visited['a' - 'a'] = true;
			visited['n' - 'a'] = true;
			visited['t' - 'a'] = true;
			visited['i' - 'a'] = true;
			visited['c' - 'a'] = true;
			dfs(0, 0);
		}
		
		System.out.println(result);
	}
}