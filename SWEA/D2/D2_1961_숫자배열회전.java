package swea.d2;

import java.util.Arrays;
import java.util.Scanner;

public class D2_1961 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int num = sc.nextInt();
			int[][] arr = new int[num][num];
			for (int i = 0; i < num; i++) { //행받기
				for (int j = 0; j < num; j++) { //열받기
					arr[i][j] = sc.nextInt();
				}
			}
			int[][] arr90 = new int[num][num];
			int[][] arr180 = new int[num][num];
			int[][] arr270 = new int[num][num];
			for (int i = 0; i < num; i++) { // 90도 돌리기 열순서
				for (int j = num-1; j >-1; j--) {
					arr90[i][num-1-j] = arr[j][i];
				}
			}
			for (int i = num-1; i > -1; i--) { // 180도 돌리기 행순서
				for (int j = num-1; j >-1; j--) {
					arr180[num-1-i][num-1-j] = arr[i][j];
				}
			}
			for (int i = num-1; i >-1; i--) { // 270도 돌리기 열순서
				for (int j = 0; j <num; j++) {
					arr270[num-1-i][j] = arr[j][i];
				}
			}
			System.out.println("#"+test_case);
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < 3; j++) {
					switch (j) {
						case 0:
							for (int k = 0; k < num; k++) {
								System.out.print(arr90[i][k]);
							}
							System.out.print(" ");
							break;
						case 1:
							for (int k = 0; k < num; k++) {
								System.out.print(arr180[i][k]);
							}
							System.out.print(" ");
							break;
						case 2:
							for (int k = 0; k < num; k++) {
								System.out.print(arr270[i][k]);
							}
							System.out.println();
							break;
					}
				}
			}
		}
	}

}
