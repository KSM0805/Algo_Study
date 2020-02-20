package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1335 {
	private static int N;
	private static boolean[][] map;
	private static int[] cnt = new int[2];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine().trim());
		map = new boolean[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken())==1 ? true : false;
			}
		}
		mergeCnt(0,N,0,N);
		System.out.print(cnt[0]+"\n"+cnt[1]);
	}

	private static void mergeCnt(int startRow, int endRow, int startCol, int endCol) {
		boolean toggle = true;
		boolean value = map[startRow][startCol];
		for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				if(value != map[i][j]) {
					toggle = false;
					break;
				}
			}
		}
		if(toggle) {
			if(value) cnt[1]++;
			else cnt[0]++;
		}
		else {
			int midRow = (startRow+endRow)>>1;
			int midCol = (startCol+endCol)>>1;
			mergeCnt(startRow, midRow, startCol, midCol);
			mergeCnt(midRow, endRow, startCol, midCol);
			mergeCnt(startRow, midRow, midCol, endCol);
			mergeCnt(midRow, endRow, midCol, endCol);
		}
	}
}
