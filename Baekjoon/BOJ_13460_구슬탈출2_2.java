package boj;
	
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
	
public class BOJ_13460_구슬탈출2_2 {
	private static int result;
	private static int N;
	private static int M;
	private static char[][] map;
	private static int[][][][] visit;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //3~10
		map = new char[N][M];
		int[] RED = new int[2];
		int[] BLUE = new int[2];
		String str;
		for (int i = 0; i < N; i++) {
			str = bf.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='R') {
					RED[0] = i;
					RED[1] = j;
					map[i][j]='.';
				}else if(map[i][j] == 'B') {
					BLUE[0] = i;
					BLUE[1] = j;
					map[i][j]='.';
				}
			}
		}
		result = 12;
		//최솟값 -> bfs
		visit = new int[N][M][N][M]; //빨간구슬 + 파란구슬 위치 (4차원)
		LinkedList<Gooseul> q = new LinkedList<>(); // 빨간구슬과 파란구슬의 위치가 모두 들어감
		visit[RED[0]][RED[1]][BLUE[0]][BLUE[1]] = 1;
		q.offer(new Gooseul(RED[0], RED[1], BLUE[0], BLUE[1], 1));
		while(!q.isEmpty()) {
			Gooseul gs = q.poll();
			int cnt = gs.cnt+1;
			if(gs.cnt > 11 || gs.cnt > result) continue;
			if(map[gs.redR][gs.redC]=='O') {
				if(map[gs.blueR][gs.blueC]!='O') result = Math.min(gs.cnt, result);
				continue;
			}else if(map[gs.blueR][gs.blueC]=='O') continue;
			for (int i = 0; i < 4; i++) {
				//4방향으로 기울이기
				int[] chpos = move(gs.redR, gs.redC, gs.blueR, gs.blueC ,i);
				if(visit[chpos[0]][chpos[1]][chpos[2]][chpos[3]]==0 || visit[chpos[0]][chpos[1]][chpos[2]][chpos[3]]>cnt) {
					visit[chpos[0]][chpos[1]][chpos[2]][chpos[3]] = cnt;
					q.offer(new Gooseul(chpos[0], chpos[1], chpos[2], chpos[3], cnt));
				}
			}
		}
		if(result>11) {
			System.out.println("-1");
		}else 
			System.out.println(result-1);
	}
	
	private static int[] move(int redR, int redC, int blueR, int blueC, int mode) {
		int[] pos = {redR, redC, blueR, blueC};
		boolean toggle = false;
		if( (mode==0 && blueR<redR) || (mode==1 && blueR>redR) || (mode==2 && blueC<redC) || (mode==3 && blueC>redC)) {
			toggle = true;
			int[] tmp = {pos[0],pos[1]};
			pos[0] = pos[2];
			pos[1] = pos[3];
			pos[2] = tmp[0];
			pos[3] = tmp[1];
		}
		while(map[pos[0]][pos[1]]=='.') { //앞에 있는 것 먼저 , 가장자리에 #이 있으므로 경계선 검사 X
			pos[0] += dir[mode][0];
			pos[1] += dir[mode][1];
		}
		if(map[pos[0]][pos[1]]=='#') {
			pos[0] -= dir[mode][0];
			pos[1] -= dir[mode][1];
		}
		while(map[pos[2]][pos[3]]=='.') {
			pos[2] += dir[mode][0];
			pos[3] += dir[mode][1];
		}
		if(map[pos[2]][pos[3]]=='#') {
			pos[2] -= dir[mode][0];
			pos[3] -= dir[mode][1];
			if(pos[0]==pos[2] && pos[1]==pos[3]) {
				pos[2] -= dir[mode][0];
				pos[3] -= dir[mode][1];
			}
		}
		if(toggle) {
			int[] tmp = {pos[0],pos[1]};
			pos[0] = pos[2];
			pos[1] = pos[3];
			pos[2] = tmp[0];
			pos[3] = tmp[1];
		}
		return pos;
	}
}
class Gooseul{
	int redR,redC,blueR,blueC,cnt;
	public Gooseul(int redR, int redC, int blueR, int blueC, int cnt) {
		super();
		this.redR = redR;
		this.redC = redC;
		this.blueR = blueR;
		this.blueC = blueC;
		this.cnt = cnt;
	}
}
