package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1912_연속합_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine().trim());
		
		int sum = 0; // 연속합의 최댓값 
		int min = 0; // 연속합의 최솟값
		
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");

		sum = Integer.parseInt(st.nextToken());
		int max = sum;
		
		if (min > sum) {
			min = sum;
		}
		
		for (int i = 1; i < n; i++) {
			sum += Integer.parseInt(st.nextToken());
			if (max < sum - min) {
				max = sum - min;
			}
			
			if (min > sum) {
				min = sum;
			}
		}
		System.out.println(max);
		
	}
}
