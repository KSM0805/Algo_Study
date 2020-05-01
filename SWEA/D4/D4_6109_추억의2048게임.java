package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_6109_추억의2048게임 {
	private static int N;
	private static String d;
	private static int[][] map;
	private static boolean[][] visit;
	private static int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			d = st.nextToken(); // up, down, right, left
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][N];
			switch (d) {
            case "up":
                for (int i = 0; i < N; i++) {
                    for (int j = 1; j < N; j++) {
                        move(j,i,0);
                    }
                }
                break;
            case "down":
                for (int i = 0; i < N; i++) {
                    for (int j = N-2; j > -1; j--) {
                        move(j,i,1);
                    }
                }
                break;
            case "right":
                for (int i = 0; i < N; i++) {
                    for (int j = N-2; j > -1; j--) {
                        move(i,j,2);
                    }
                }
                break;
            case "left":
                for (int i = 0; i < N; i++) {
                    for (int j = 1; j < N; j++) {
                        move(i,j,3);
                    }
                }
                break;
            }
            System.out.println("#"+test_case);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
		}//testcase end
	}//main end

	private static void move(int r, int c, int k) {
		if(map[r][c] == 0) return;
        int nr = r + dir[k][0];
        int nc = c + dir[k][1];
        while(map[nr][nc] == 0) {
            nr += dir[k][0];
            nc += dir[k][1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                nr -= dir[k][0];
                nc -= dir[k][1];
                int tmp = map[r][c];
                map[r][c] = 0;
                map[nr][nc] = tmp;
                return;
            }
        }
        if(map[nr][nc] == map[r][c] && !visit[nr][nc]) {
            map[nr][nc] *= 2;
            visit[nr][nc] = true;
            map[r][c] = 0;
        } else {
            nr -= dir[k][0];
            nc -= dir[k][1];
            int tmp = map[r][c];
            map[r][c] = 0;
            map[nr][nc] = tmp;
        }
    }
}
