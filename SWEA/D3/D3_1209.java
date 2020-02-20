package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class D3_1209 {
	public static void main(String[] args) throws NumberFormatException, IOException { 
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
//		int T = Integer.parseInt(bf.readLine().trim());

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int[][] arr = new int [100][100];
			int T = Integer.parseInt(bf.readLine().trim());
			
			String a;
			String[] b = new String[100];
			int max = 0;
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				a = bf.readLine().trim();
				b = a.split(" ");
				sum = 0;
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(b[j]);
					sum += Integer.parseInt(b[j]);
				}
				if(max<sum) max = sum;
			}
			for (int i = 0; i < 100; i++) {
				sum = 0;
				for (int j = 0; j < 100; j++) {
					sum += arr[j][i];
				}
				if(max<sum) max = sum;
			}
			sum = 0;
			for (int i = 0; i < 100; i++) {
				sum += arr[i][i];
			}
			if(max<sum) max = sum;
			sum = 0;
			for (int i = 0; i < 100; i++) {
				sum += arr[i][99-i];
			}
			if(max<sum) max = sum;
			System.out.println("#"+T+" "+max);
		}//for end
	}
}
