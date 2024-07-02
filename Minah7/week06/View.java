// 성공

package Array1;

import java.util.Scanner;

public class View {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int tc = 1; tc <= 10; tc++) {
			int answer = 0;
			
			int n = sc.nextInt();
			int[] height = new int[n];
			for(int building = 0; building < n; building++) {
				height[building] = sc.nextInt();
			}
			
			for(int building = 2; building < n - 2; building++) {
				int max = 0;
				boolean flag = true;
				for(int b = building - 2; b <= building + 2; b++) {
					if(b == building) {
						continue;
					}
					
					if(height[b] >= height[building]) {
						flag = false;
						break;
					}
					max = Math.max(max, height[b]);
				}
				
				if(flag) {
					answer += height[building] - max;
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}