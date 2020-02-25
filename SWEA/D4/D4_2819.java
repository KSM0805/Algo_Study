package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;

public class D4_2819 {
	private static String[] str;
	private static HashSet<String> list;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			str = new String[4];
			for (int i = 0; i < 4; i++) {
				str[i] = bf.readLine().trim();
			}
			bfs();
			System.out.println("#"+test_case+" "+list.size());
		}//for end
	}

	private static void bfs() {
		list = new HashSet<>();
		LinkedList<Pan> q = new LinkedList<>();
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				q.offer(new Pan(i,j,0,str[i].substring(j<<1, (j<<1) + 1)));
			}
		}
		while(!q.isEmpty()) {
			Pan p = q.poll();
			int r = p.r;
			int c = p.c;
			int moveCnt = p.moveCnt;
			if(moveCnt == 6) {
				list.add(p.str);
			}else {
				for (int i = 0; i < 4; i++) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					if(nr>-1 && nc>-1 && nr<4 & nc<4) {
						String s = p.str + str[nr].charAt(nc<<1);
						q.offer(new Pan(nr, nc, moveCnt + 1, s));
					}
				}
			}
		}
	}
}

class Pan{
	int r;
	int c;
	int moveCnt;
	String str;
	public Pan(int r, int c, int moveCnt, String str) {
		super();
		this.r = r;
		this.c = c;
		this.moveCnt = moveCnt;
		this.str = str;
	}
}
