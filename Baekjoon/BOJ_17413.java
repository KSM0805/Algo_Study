package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17413 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine().trim();
		int N = str.length();
		int start = 0;
		while(start<N) {
			for (int i = start; i < N; i++) {
				char c = str.charAt(i);
				if(c == '<') {
					for (int j = i-1; j >=start; j--) {
						System.out.print(str.charAt(j));
					}
					start = str.indexOf('>', start);
					for (int j = i; j <= start; j++) {
						System.out.print(str.charAt(j));
					}
					start += 1;
					break;
				}else if (c == ' ') {
					for (int j = i-1; j >= start; j--) {
						System.out.print(str.charAt(j));
					}
					System.out.print(" ");
					start = i + 1;
					break;
				}
				if(i==N-1) {
					for (int j = N-1; j >= start; j--) {
						System.out.print(str.charAt(j));
					}
					start = N;
					break;
				}
			}
		}
	}
}
