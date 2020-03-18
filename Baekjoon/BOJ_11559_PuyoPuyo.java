package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_11559_PuyoPuyo {
	private static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//12*6
		
		int[][] map = new int[12][6];
		String str;
		char ch;
		for (int i = 0; i < 12; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < 6; j++) {
				ch = str.charAt(j);
				switch (ch) {
				case '.':
					map[i][j] = 0;
					break;
				case 'R':
					map[i][j] = 1;
					break;
				case 'G':
					map[i][j] = 2;
					break;
				case 'B':
					map[i][j] = 3;
					break;
				case 'P':
					map[i][j] = 4;
					break;
				case 'Y':
					map[i][j] = 5;
					break;
				default:
					break;
				}
			}
		}//map 채우기
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
		result = 0;
		//각각의 1,2,3,4,5를 bfs 혹은 dfs하면 됨
		ArrayList<int[]> list = new ArrayList<>();
		boolean[][] visit = new boolean[12][6];
		boolean check = true; //터진게 있으면 true, 없으면 false;
		while(check) {
			check = false;
			for(boolean[] is:visit) {
				Arrays.fill(is, false);
			}
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(map[i][j]>0 && !visit[i][j]) {
						list.clear();
						list.add(new int[] {i,j});
						visit[i][j] = true;
						int pos = 0;
						while(pos<list.size()) {
							int[] cur = list.get(pos);
							for (int k = 0; k < 4; k++) {
								int nr = cur[0] + dir[k][0];
								int nc = cur[1] + dir[k][1];
								if(nr>-1 && nc>-1 && nr<12 && nc<6 && !visit[nr][nc] && map[nr][nc]==map[i][j]) {
									visit[nr][nc] = true;
									list.add(new int[] {nr,nc});
								}
							}
							pos++;
						}
						//4개 이상 연결된 경우에만
						if(list.size()>3) {
							check = true;
							for (int k = 0; k < list.size(); k++) {
								int[] cur = list.get(k);
								map[cur[0]][cur[1]] = 0;
							}
						}
					}
				}
			}
			//블럭내리기
			if(check) {
				result++;
				for (int k = 0; k < 6; k++) {
					int empty = -1;
					for (int l = 11; l >= 0; l--) {
						if(map[l][k] == 0 && empty == -1) {
							empty = l;
						}else if(map[l][k] > 0) {
							if(empty == -1) continue;
							map[empty][k] = map[l][k];
							map[l][k] = 0;
							l = empty;
							empty = -1;
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
