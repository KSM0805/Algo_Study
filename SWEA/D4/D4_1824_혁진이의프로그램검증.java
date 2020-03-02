package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D4_1824 {
	private static int R;
	private static int C;
	private static String[] str;
	private static int[][] dir = { {-1,0},{1,0},{0,-1},{0,1} };
	private static boolean[][][][] visit;
	private static boolean exit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			str = new String[R];
			for (int i = 0; i < R; i++) {
				str[i] = bf.readLine().trim();
			}
			visit = new boolean[R][C][4][16];
			find();
			System.out.println("#"+test_case+" "+(exit ? "YES":"NO"));
		}//for end
	}

	private static void find() {
		LinkedList<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,3,0}); //현재 행, 열, 방향, 메모리숫자
		exit = false;
		while( !q.isEmpty() ) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			int d = arr[2];
			int mem = arr[3];
			char ch = str[r].charAt(c);
			if(ch == '@') {
				exit = true;
				return;
			}
			if(Character.isDigit(ch)) {
				mem = ch-'0';
			}else{ 
				switch (ch) {
				case '^':
					d = 0;
					break;
				case 'v':
					d = 1;
					break;
				case '<':
					d = 2;
					break;
				case '>':
					d = 3;
					break;
				case '_':
					d = mem==0 ? 3 : 2;
					break;
				case '|':
					d = mem==0 ? 1 : 0;
					break;
				case '+':
					mem = mem==15 ? 0 : mem+1;
					break;
				case '-':
					mem = mem==0 ? 15 : mem-1;
					break;
				}
			}
			int nr, nc;
			if(ch=='?') {
				for (int i = 0; i < 4; i++) {
					nr = r + dir[i][0];
					nc = c + dir[i][1];
					if(nr<0) nr = R-1;
					if(nc<0) nc = C-1;
					if(nr>R-1) nr = 0;
					if(nc>C-1) nc = 0;
					if(!visit[nr][nc][i][mem]) {
						visit[nr][nc][i][mem] = true;
						q.offer(new int[] {nr,nc,i,mem});
					}
				}
			}else {
				nr = r + dir[d][0];
				nc = c + dir[d][1];
				if(nr<0) nr = R-1;
				if(nc<0) nc = C-1;
				if(nr>R-1) nr = 0;
				if(nc>C-1) nc = 0;
				if(!visit[nr][nc][d][mem]) {
					visit[nr][nc][d][mem] = true;
					q.offer(new int[] {nr,nc,d,mem});
				}
			}
		}
		
	}
}
