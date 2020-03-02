package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_5656 {
	private static int result;
	private static int N;
	private static int H;
	private static int W;
	private static int[][] map;
	private static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); // 떨어뜨릴 구슬의 갯수 :1~4
			W = Integer.parseInt(st.nextToken()); // 가로길이:2~12
			H = Integer.parseInt(st.nextToken()); // 세로길이:2~15
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//최대값구하기 이므로 dfs
			for (int i = 0; i < W; i++) {
				dfs(i,1,map); //첫번째로 떨어뜨릴 구슬 위치
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

	private static void dfs(int idx, int cnt, int[][] m) { // 떨어뜨릴 구슬 위치, 떨어뜨린 횟수
		int[][] copyMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			copyMap[i] = Arrays.copyOf(m[i], W);
		}
		//블럭 부수기
		LinkedList<int[]> q = new LinkedList<>();
		for (int i = 0; i < H; i++) {
			if(copyMap[i][idx] == 1) {
				copyMap[i][idx] = 0;
				break;
			}
			if(copyMap[i][idx] > 1) {
				q.offer(new int[] {i,idx, copyMap[i][idx]});
				copyMap[i][idx] = 0;
				break;
			}
		}
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			int num = arr[2];
			for (int i = 0; i < 4; i++) {
				int nr = r;
				int nc = c;
				for (int j = 1; j < num; j++) {
					nr += dir[i][0];
					nc += dir[i][1];
					if(nr>-1 && nc>-1 && nr<H && nc<W) {
						if(copyMap[nr][nc] > 1) {
							q.offer(new int[] {nr,nc,copyMap[nr][nc]});
							copyMap[nr][nc] = 0;
						}else {
							copyMap[nr][nc] = 0;
						}
					}
				}
			}
			
		}
		int total = 0;
		if(cnt==N) {
			//남은 블럭 세기
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(copyMap[i][j] > 0) total++;
				}
			}
			result = Math.min(result, total);
			return;
		}
		//빈자리 채우기
		LinkedList<Integer> list = new LinkedList<>();
		int zero = 0;
		for (int i = 0; i < W; i++) {
			top:
			for (int j = H-1; j >-1; j--) {
				if(copyMap[j][i] == 0) {
					for (int k = j-1; k > -1; k--) {
						if(copyMap[k][i] > 0) {
							copyMap[j][i] = copyMap[k][i];
							copyMap[k][i] = 0;
							break;
						}else if(k==0) break top;
					}
				}
			}
		}
		//다음 구슬 위치찾기
		for (int i = 0; i < W; i++) {
			dfs(i,cnt+1,copyMap);
		}
	}
}
