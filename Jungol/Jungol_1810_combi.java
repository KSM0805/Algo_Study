package jungol;

import java.util.Arrays;
import java.util.Scanner;

/*public class jungol_1810_combi {
	static int[] num;
	static int[] re;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = new int[9];
		re = new int[7];
		
		for (int i = 0; i < 9; i++) {
			num[i] = sc.nextInt();
		}
		combination(0,1);
	}

	private static void combination(int cnt, int start) {
		if(cnt == 7) {
			
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += re[i];
			}
			if(sum==100) {
				System.out.println(Arrays.toString(re));
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			re[cnt] = num[i-1];
			combination(cnt+1, i+1);
		}
		
	}

}*/
