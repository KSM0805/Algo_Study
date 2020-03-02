package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_5653 {
	private static int result;
	private static int N;
	private static int K;
	private static int M;
	private static int[][][] map;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); //세로길이
			M = Integer.parseInt(st.nextToken()); //가로길이
			K = Integer.parseInt(st.nextToken()); //목표시간
			
			LinkedList<int[]> list = new LinkedList<>();
			map = new int[2*K+N][2*K+M][2];
			for (int i = K; i < K+N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = K; j < K+M; j++) {
					map[i][j][0] = Integer.parseInt(st.nextToken()); // 생명력 수치
					if(map[i][j][0] > 0) {
						list.add(new int[] {i,j,map[i][j][0],map[i][j][0]+1}); // i: 행위치, j: 열위치, map[i][j]: 생명력 수치, map[i][j]: 활성화될 시간
						map[i][j][1] = 1; // 활성화 시간
					}
				}
			}
			int time = 2;
			int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
			while(time<=K) {
				for (int i = 0; i < list.size(); i++) {
					int[] arr = list.get(i);
					if(arr[3] == time) { // 활성화 되고 1시간 동안은 번식
						for (int j = 0; j < 4; j++) {
							int nr = arr[0] + dir[j][0];
							int nc = arr[1] + dir[j][1];
							if(map[nr][nc][0] > -1 && (map[nr][nc][1] == 0 || map[nr][nc][1] == time)) { // 죽은 셀에 근접 안하는지 확인할것, 같이 활성화(arr[3]-1)된 셀은 전염x 
								int idx = list.indexOf(new int[] {nr,nc,map[nr][nc][0], time + map[nr][nc][0] + 1});
								if(map[nr][nc][0]<arr[2]) {
									if(idx != -1) {
										list.remove(idx);
									}
									map[nr][nc][0] = arr[2];
									map[nr][nc][1] = time;
									list.add(new int[] {nr, nc, map[nr][nc][0], time + map[nr][nc][0] + 1});
								}
							}
						}
					}
					if(arr[3]+arr[2]-1 <= time) {
						map[arr[0]][arr[1]][0] = -1;
						list.remove(i);
						i--;
						
					}
				}
				time++;
			}
			result = list.size();
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
