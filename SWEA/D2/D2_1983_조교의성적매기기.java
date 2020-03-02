package swea.d2;

import java.util.Arrays;
import java.util.Scanner;

public class D2_1983 {
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int students = sc.nextInt();
			int target = sc.nextInt();
			int targetScore = 0;
			int[] score = new int [students];
			for (int i = 0; i < students; i++) {
				int middle = sc.nextInt();
				int end = sc.nextInt();
				int hw = sc.nextInt();
				score[i] = 35*middle + 45*end + 20*hw;
			}
			targetScore = score[target-1];
			Arrays.sort(score);
			
			int rank = 0;
			for (int i = 0; i < students; i++) {
				if(targetScore == score[i]) {
					rank = students - i - 1;
					break;
				}
			}
			// 40명이면 0~3까지 1 5~8까지 2
			int grade = (rank / (students/10));
			System.out.print("#"+test_case+" ");
			switch (grade) {
				case 0: 
					System.out.println("A+");
					break;
				case 1: 
					System.out.println("A0");
					break;
				case 2: 
					System.out.println("A-");
					break;
				case 3: 
					System.out.println("B+");
					break;
				case 4: 
					System.out.println("B0");
					break;
				case 5: 
					System.out.println("B-");
					break;
				case 6: 
					System.out.println("C+");
					break;
				case 7: 
					System.out.println("C0");
					break;
				case 8: 
					System.out.println("C-");
					break;
				case 9: 
					System.out.println("D0");
					break;
			}
		}//for end
	}

}
