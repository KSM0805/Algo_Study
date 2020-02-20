package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Jungol_1141 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

//		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(bf.readLine().trim()); // 문자열을 정수로 변환,trim() 공백 제거

//		int num = sc.nextInt();
		int[] cow = new int[num];
		
		for (int i = 0; i < num; i++) {
			cow[i] = Integer.parseInt(bf.readLine().trim());
		}
		long cnt = 0;
		int max = 0;
		for (int i = 0; i < num-1; i++) {
			max = cow[i]; //힙을 거치지 않고 변수에 저장한 후 비교하는게 더 빠름
			for (int j = i+1; j < num; j++) {
				if(max>cow[j]) {
					cnt++;
				}
				else {
					break;
				}
			}
		}
		System.out.println(cnt);
	}

}
