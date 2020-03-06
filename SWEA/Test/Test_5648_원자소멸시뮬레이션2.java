package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Test_5648_원자소멸시뮬레이션2 {
	private static int result;
	private static int N;
	private static int[] dr = {0,0,-1,1};
	private static int[] dc = {1,-1,0,0};
	private static int[][] map;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		map = new int[4001][4001]; // field로 하는게 접근이 더 빠르다.
		ArrayList<int[]> atom = new ArrayList<>();
		HashSet<Integer> list = new HashSet<>();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			atom.clear();
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				atom.add(new int[] {Integer.parseInt(st.nextToken())*2+2000,Integer.parseInt(st.nextToken())*2+2000,
						Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
			}
			result = 0;
			int time = 0;
			while(time<4001) {
				list.clear();
				int size = atom.size();
				int nr,nc;
				for (int i = 0,j=0; j < size; i++,j++) {
					int[] cur = atom.get(i);
					map[cur[0]][cur[1]] = 0;
					nr = cur[0] + dr[cur[2]];
					nc = cur[1] + dc[cur[2]];
					if(nr<0 || nc<0 || nr>4000 || nc>4000) {
						atom.remove(i);
						i--;
					}else {
						if(map[nr][nc]==0) {
							map[nr][nc] = i+1;
							cur[0] = nr;
							cur[1] = nc;
						}else {
							result += cur[3];
							list.add(map[nr][nc]-1);
							atom.remove(i);
							i--;
						}
					}
				}
				Object[] a = list.toArray(); //iterator가 조금 더 빠름.
				for (int i = a.length-1; i > -1; i--) {
					int[] cur = atom.get((int)a[i]);
					map[cur[0]][cur[1]] = 0;
					result += cur[3];
					atom.remove((int)a[i]);
				}
//				Iterator<Integer> a = list.iterator();
//				int p = 0;
//				while(a.hasNext()) {
//					int num = a.next();
//					int[] cur = atom.get(num-p);
//					result += cur[3];
//					map[cur[0]][cur[1]] = 0;
//					atom.remove(num-p);
//					p++;
//				}
				time++;
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
