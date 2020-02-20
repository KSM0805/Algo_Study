package jungol;

import java.util.Scanner;

public class Jungol_1175 {
	static int dice = 6;
	static int cnt;
	static int total;
	static int[] result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cnt = sc.nextInt();
		total = sc.nextInt();
		result = new int[cnt];
		combination(0,0);
	}
	private static void combination(int pos, int sum) {
		if(pos == cnt) {
			if(sum == total) {
				for (int i = 0; i < cnt; i++) {
					System.out.print(result[i]+" ");
				}
				System.out.println();
			}
			return;
		}
		for (int i = 1; i <= dice; i++) {
			result[pos] = i;
			combination(pos+1, sum + result[pos]);
		}
		
	}

}
