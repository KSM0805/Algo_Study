package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2 {
	private static int M;
	private static int N;
	private static char[][] map;
	private static int[][] gooseul;
	private static int result;
	private static int[] hole;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int[][][][] visit;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken()); //3~10
		M = Integer.parseInt(st.nextToken()); //3~10
		map = new char[N][M];
		gooseul = new int[2][2]; //0번째 빨간 구슬 1번째 파란구슬
		hole = new int[2];
		String str;
		for (int i = 0; i < N; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					gooseul[0][0] = i;
					gooseul[0][1] = j;
					map[i][j] = '.';
				}else if(map[i][j]=='B') {
					gooseul[1][0] = i;
					gooseul[1][1] = j;
					map[i][j] = '.';
				}else if(map[i][j]=='O') {
					hole[0] = i;
					hole[1] = j;
				}
			}
		}
		result = Integer.MAX_VALUE;
		//최소 -> bfs
		LinkedList<Goo> q = new LinkedList<>();
		q.offer(new Goo(gooseul[0][0],gooseul[0][1],gooseul[1][0],gooseul[1][1],1)); //초기위치 한번도 움직이지 않을때
		visit = new int[N][M][N][M]; // 빨간,파란 구슬 위치
		visit[gooseul[0][0]][gooseul[0][1]][gooseul[1][0]][gooseul[1][1]] = 1;
		while(!q.isEmpty()) {
			Goo cur = q.poll();
//			System.out.println(cur.toString());
			if(cur.redR < 0) {
				if(cur.blueR < 0) continue;
				else{
					result = Math.min(result, cur.cnt);
				}
				continue;
			}else if(cur.blueR < 0 && cur.blueC < 0) {
				continue;
			}else if(result < cur.cnt) continue;
            else if(cur.cnt>11) continue;
			//4방향 케이스 전부
			int[] changePos;
			//왼쪽으로 기울일때
			if(cur.redC <= cur.blueC) {
				changePos = move(cur.redR, cur.redC, cur.blueR, cur.blueC,2); //빨간 구슬이 먼저 움직이고 다음 파란구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}else if(visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] == 0 || visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] > cur.cnt+1) {
					visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] = cur.cnt+1;
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}
			}else {
				changePos = move( cur.blueR, cur.blueC, cur.redR, cur.redC,2); //파란 구슬이 먼저 움직이고 다음 빨간구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}else if(visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] == 0 || visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] > cur.cnt+1) {
					visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] = cur.cnt+1;
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}
			}
			//오른쪽으로 기울일때
			if(cur.redC >= cur.blueC) {
				changePos = move(cur.redR, cur.redC, cur.blueR, cur.blueC,3); //빨간 구슬이 먼저 움직이고 다음 파란구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}else if(visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] == 0 || visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] > cur.cnt+1) {
					visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] = cur.cnt+1;
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}
			}else {
				changePos = move( cur.blueR, cur.blueC, cur.redR, cur.redC,3); //파란 구슬이 먼저 움직이고 다음 빨간구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}else if(visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] == 0 || visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] > cur.cnt+1) {
					visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] = cur.cnt+1;
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}
			}
			//위쪽으로 기울일때
			if(cur.redR <= cur.blueR) {
				changePos = move(cur.redR, cur.redC, cur.blueR, cur.blueC,0); //빨간 구슬이 먼저 움직이고 다음 파란구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}else if(visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] == 0 || visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] > cur.cnt+1) {
					visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] = cur.cnt+1;
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}
			}else {
				changePos = move( cur.blueR, cur.blueC, cur.redR, cur.redC,0); //파란 구슬이 먼저 움직이고 다음 빨간구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}else if(visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] == 0 || visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] > cur.cnt+1) {
					visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] = cur.cnt+1;
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}
			}
			//아래쪽으로 기울일때
			if(cur.redR >= cur.blueR) {
				changePos = move(cur.redR, cur.redC, cur.blueR, cur.blueC,1); //빨간 구슬이 먼저 움직이고 다음 파란구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}else if(visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] == 0 || visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] > cur.cnt+1) {
					visit[changePos[0]][changePos[1]][changePos[2]][changePos[3]] = cur.cnt+1;
					q.offer(new Goo(changePos[0],changePos[1],changePos[2],changePos[3],cur.cnt+1));
				}
			}else {
				changePos = move( cur.blueR, cur.blueC, cur.redR, cur.redC,1); //파란 구슬이 먼저 움직이고 다음 빨간구슬
				if(changePos[0] < 0 || changePos[2] < 0) {
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}else if(visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] == 0 || visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] > cur.cnt+1) {
					visit[changePos[2]][changePos[3]][changePos[0]][changePos[1]] = cur.cnt+1;
					q.offer(new Goo(changePos[2],changePos[3],changePos[0],changePos[1],cur.cnt+1));
				}
			}
		}
		if(result == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(result-1);
	}

	private static int[] move(int row1, int col1, int row2, int col2, int mode) {
		int[] pos = new int[4];
		pos[0] = row1;
		pos[1] = col1;
		pos[2] = row2;
		pos[3] = col2;
		while((pos[0]>-1 && pos[1]>-1 && pos[0]<N && pos[1]<M) && (map[pos[0]][pos[1]]=='.')) {
			pos[0] += dir[mode][0];
			pos[1] += dir[mode][1];
			if(map[pos[0]][pos[1]]=='O') {
				pos[0] = -2;
				pos[1] = -2;
				break;
			}
		}
		pos[0] -= dir[mode][0];
		pos[1] -= dir[mode][1];
		while((pos[2]>-1 && pos[3]>-1 && pos[2]<N && pos[3]<M) && (pos[2] != pos[0] || pos[3] != pos[1]) && (map[pos[2]][pos[3]]=='.')) {
			pos[2] += dir[mode][0];
			pos[3] += dir[mode][1];
			if(map[pos[2]][pos[3]]=='O') {
				pos[2] = -2;
				pos[3] = -2;
				break;
			}
		}
		pos[2] -= dir[mode][0];
		pos[3] -= dir[mode][1];
		return pos;
	}
}
class Goo{
	int redR;
	int redC;
	int blueR;
	int blueC;
	int cnt;
	public Goo(int redR, int redC, int blueR, int blueC, int cnt) {
		super();
		this.redR = redR;
		this.redC = redC;
		this.blueR = blueR;
		this.blueC = blueC;
		this.cnt = cnt;
	}
}