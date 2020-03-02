package swea.d2;

import java.util.Arrays;
import java.util.Scanner;

public class D2_1954 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int[][] dir = { { 0, 1, 0, -1}, {1, 0, -1, 0}};

		for(int test_case = 1; test_case <= T; test_case++){
			int num = sc.nextInt();
			int r = 0;
			int c = -1;
			int i = 0;
			int[][] ar = new int[num][num];
			int step = 1;
			while(step <= num*num) {//for문을 안돌리고 범위에서 벗어나면 다이렉션을 바꾸는 방법
				int x = (i)%4;
				r = dir[0][x] + r;
				c = dir[1][x] + c;
				if(r<0 || c<0 || r>num-1 || c>num-1 || ar[r][c] != 0) { // 방향에서 벗어나는 경우만 해야함 전부하면 x
																														// 그러므로 배열의 값이 있는지 확인
					r = -dir[0][x] + r;
					c = -dir[1][x] + c;
					i++;
					continue;
				}
				ar[r][c] = step;
				step++;
			}
			for (int[] js : ar) {
				System.out.println(Arrays.toString(js));
			}
		}
	}
}
