// 미완성
// https://www.acmicpc.net/problem/2504

package 약점_체크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호의_값 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		
		int total = 0;
		int temp = 0;
		
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < len; i++) {
			if(str.charAt(i) == '(' || str.charAt(i) == '[') {
				stack.add(str.charAt(i));
			} else {
				if(str.charAt(i) == ')') {
					if(!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
						if(temp == 0) {
							temp += 2;
						} else {
							temp *= 2;
						}
						
						if(stack.isEmpty() || (i < len - 1 && (str.charAt(i + 1) == '(' || str.charAt(i + 1) == '['))) {
							total += temp;
							temp = 0;
						}
					} else {
						total = 0;
						break;
					}
				} else {
					if(!stack.isEmpty() && stack.peek() == '[') {
						stack.pop();
						if(temp == 0) {
							temp += 3;
						} else {
							temp *= 3;
						}
						
						if(stack.isEmpty() || (i < len - 1 && (str.charAt(i + 1) == '(' || str.charAt(i + 1) == '['))) {
							total += temp;
							temp = 0;
						}
					} else {
						total = 0;
						break;
					}
				}
			}
		}
		
		total += temp;
		
		System.out.println(total);
	}
}
