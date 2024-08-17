// 성공
// https://www.acmicpc.net/problem/14719

package 약점_체크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물 {
	static int result = 0;
	static int[] block;
	static int height;
	static int width;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		int min = 500;
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		block = new int[width];
		for(int i = 0; i < width; i++) {
			block[i] = Integer.parseInt(st.nextToken());
			if(block[i] > max) {
				max = block[i];
			}
			if(block[i] < min) {
				min = block[i];
			}
		}
		
		min++;
		
		if(width > 2) {
			for(int h = min; h <= max; h++) {
				count(h);
			}			
		}
		
		System.out.println(result);
	}
	
	public static void count(int h) {
		for(int w = 0; w < width - 2; w++) {
			int add = 0;
			if(block[w] >= h && block[w + 1] < h) {
				w++;
				while(w < width && block[w] < h) {
					add++;
					w++;
				}
				if(w < width) {
					result += add;
					w--;
				}
			}
		}
	}
}