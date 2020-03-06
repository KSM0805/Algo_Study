package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_5648_원자소멸시뮬레이션 {
	private static int result;
	private static int N;
	private static int[] dr = {0,0,-1,1};
	private static int[] dc = {1,-1,0,0};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		// testcase 안에 선언하면 map이 계속 생성되어 메모리 부족이 일어날 수도 있으므로 밖에 선언한다.
		int[][] map = new int[4001][4001];
//		LinkedList<int[]> atom = new LinkedList<>();
		ArrayList<int[]> atom = new ArrayList<>(); //메모리가 부족하기 때문에 ArrayList써야함
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
							map[nr][nc]=-1;
							atom.remove(i);
							i--;
						}
					}
				}
				size = atom.size();
				for (int i = 0,j=0; j < size; i++,j++) {
					int[] cur = atom.get(i);
					if(map[cur[0]][cur[1]]==-1) {
						result += cur[3];
						map[cur[0]][cur[1]] = 0;
						atom.remove(i);
						i--;
					}
				}
				time++;
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
