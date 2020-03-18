package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1251_하나로 {
	private static int N;
	private static int[][] island;
	private static double E;
	private static boolean[] visit;
	private static double min;
	private static double[][] mem;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim()); //1~1000
			island = new int[N][2]; //0~1,000,000
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");;
			for (int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine().trim()," ");;
			for (int i = 0; i < N; i++) {
				island[i][1] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(bf.readLine().trim());
			visit = new boolean[N];
			min = 0.0;
			//bfs
			mem = new double[N][N];
			visit[0] = true;
			int cnt = 1;
			while(cnt<N) {
				double m = Double.MAX_VALUE;
				int x = 0;
				for (int i = 0; i < N; i++) {
					//지금 연결되어 있는 노드들 중
					if(visit[i]) {
						for (int j = 0; j < N; j++) {
							//연결되어있지 않은 노드와 이었을때 가장 짧은 것
							if(!visit[j]) {
								double d;
								if(mem[i][j] == 0) {
									d = Math.pow(Math.abs(island[j][0]-island[i][0]),2) + Math.pow(Math.abs(island[j][1]-island[i][1]),2);
									mem[i][j] = d;
								}else {
									d = mem[i][j];
								}
								if(m > d) {
									m = d;
									x = j;
								}
							}
						}
					}
				}
				visit[x] = true;
				min += m;
				cnt++;
			}
			min *= E;
			min += 0.5;
			System.out.println("#"+test_case+" " + (long)min);
		}//testcase end
	}//main end
}
