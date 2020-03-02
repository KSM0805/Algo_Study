package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1873 {
	private static int H;
	private static int W;
	private static int N;
	private static char[][] map;
	private static int[] pos;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			pos = new int[3]; //전차의 위치와 방향
			for (int i = 0; i < H; i++) {
				String str = bf.readLine().trim(); //한 줄당 W 개의 문자가 들어옴.
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='^') {
						pos[0] = i;
						pos[1] = j;
						pos[2] = 0;
					}else if( map[i][j] == 'v') {
						pos[0] = i;
						pos[1] = j;
						pos[2] = 1;
					}else if( map[i][j] == '<') {
						pos[0] = i;
						pos[1] = j;
						pos[2] = 2;
					}else if( map[i][j] == '>') {
						pos[0] = i;
						pos[1] = j;
						pos[2] = 3;
					}
				}
				
			}
			N = Integer.parseInt(bf.readLine().trim());
			String Inst = bf.readLine().trim();
			
			for (int i = 0; i < N; i++) {
				int r = pos[0];
				int c = pos[1];
				switch (Inst.charAt(i)){
				case 'U':
					pos[2] = 0;
					map[pos[0]][pos[1]] = '.';
					if(r-1>-1 && map[r-1][c]=='.') {
						pos[0] = pos[0]-1;
					}
					map[pos[0]][pos[1]] = '^';
					break;
					
				case 'D':
					pos[2] = 1;
					map[pos[0]][pos[1]] = '.';
					if(r+1<H && map[r+1][c]=='.') {
						pos[0] = pos[0]+1;
					}
					map[pos[0]][pos[1]] = 'v';
					break;
					
				case 'L':
					pos[2] = 2;
					map[pos[0]][pos[1]] = '.';
					if(c-1>-1 && map[r][c-1]=='.') {
						pos[1] = pos[1]-1;
					}
					map[pos[0]][pos[1]] = '<';
					break;
					
				case 'R':
					pos[2] = 3;
					map[pos[0]][pos[1]] = '.';
					if(c+1<W && map[r][c+1]=='.') {
						pos[1] = pos[1]+1;
					}
					map[pos[0]][pos[1]] = '>';
					break;
					
				case 'S':
					int d = pos[2];
					boolean ball = true;
					int row = pos[0];
					int col = pos[1];
					while(ball) {
						row = row + dir[d][0];
						col = col + dir[d][1];
						if(row>H-1 || row<0 || col>W-1 || col<0) ball = false;
						else if(map[row][col] == '*') {
							map[row][col] = '.';
							ball = false;
						}
						else if(map[row][col]=='#') {
							ball = false;
						}
					}
				}
			}
			System.out.print("#"+test_case+" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}//for end
	}
}
