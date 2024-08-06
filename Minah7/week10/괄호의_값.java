// 성공
// https://www.acmicpc.net/problem/2504

package 약점_체크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호의_값 {
	static Stack<String> stack = new Stack<>();
	static String str;
	static boolean wrong = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		int result = 0;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == '(' || c == '[') {
				stack.add("" + c);
				continue;
			}
			i = calculate(i);
			if(wrong) {
				break;
			}
		}
		
		if(wrong) {
			result = 0;
		} else if(stack.contains("(") || stack.contains("[")) {
			result = 0; 
		} else {
			while(!stack.isEmpty()) {
				result += Integer.parseInt(stack.pop());
			}
		}
		
		System.out.println(result);
	}
	
	public static int calculate(int i) {
		int num = 0;
		do {
			if(stack.isEmpty() || (!stack.contains("(") && !stack.contains("["))) {
				wrong = true;
				break;
			}
			String s = stack.pop();
			if(Character.isDigit(s.charAt(0))) {
				num += Integer.parseInt(s);
				s = stack.pop();
			}
			char c = str.charAt(i);
			if(c == ')' && s.equals("(")) {
				if(num != 0) {
					num *= 2;
				} else {
					num = 2;
				}
			} else if(c == ']' && s.equals("[")) {
				if(num != 0) {
					num *= 3;
				} else {
					num = 3;
				}
			} else {
				wrong = true;
				break;
			}
			i++;
		} while (!wrong && i < str.length() && (str.charAt(i) == ')' || str.charAt(i) == ']'));
		
		if(!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
			num += Integer.parseInt(stack.pop());
		}
		
		stack.add(String.valueOf(num));
		
		return i - 1;
	}
}