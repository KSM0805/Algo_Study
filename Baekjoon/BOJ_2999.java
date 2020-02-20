package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2999 {
	private static int N;
	private static int C;
	private static int R;
	private static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String msg = bf.readLine().trim();
		N = msg.length();
		int a = 0, b = 0;
		for (int i = 1; i < N; i++) {
			if(N%i==0) {
				if(i > N/i) {
					a = N/i;
					b = i;
				}else {
					a = i;
					b = N/i;
				}
				if(R < a) {
					R = a;
					C = b;
				}
			}
		} // R과 C정하기
		map = new char[R][C];
		int pos = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				map[j][i] = msg.charAt(pos);
				pos += 1;
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
		}
	}
}
