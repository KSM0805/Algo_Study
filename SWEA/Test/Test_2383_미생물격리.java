package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_2383_미생물격리 {
	private static int result;
	private static int N;
	private static int M;
	private static int K;
	private static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); // 5~100 구역의 크기이며 가장자리에는 약품처리
			M = Integer.parseInt(st.nextToken()); // 1~1000 시간
			K = Integer.parseInt(st.nextToken()); // 5~1000 군집의 갯수, 미생물 수는 1~10000
			LinkedList<int[]> mi = new LinkedList<>();// 행 위치, 열 위치, 미생물 수, 이동 방향
			int r,c,num,d;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				num = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				mi.add(new int[] {r,c,num,d});
			}
			int time = 0;
			while(time<M) {
				//1. 각자 위치 이동
				int size = mi.size();
				for (int i = 0; i < size; i++) {
					int[] cur = mi.get(i);
					cur[0] = cur[0] + dir[cur[3]][0];
					cur[1] = cur[1] + dir[cur[3]][1];
					if(cur[0]==0 || cur[1]==0 || cur[0]==N-1 || cur[1]==N-1) {
						cur[2] = cur[2]/2;
						cur[3] = (cur[3]%2==0) ? cur[3]-1 : cur[3]+1;
					}
					mi.set(i, cur);
				}
				//2. 다하고 정렬
				mi.sort(new Compare());
				//3. 같은 위치에 있으면 합치고 미생물수가 많은 방향으로 전환
				for (int i = 0, j = 0; j < size-1; i++, j++) {
					int[] cur = mi.get(i);
					int[] next = mi.get(i+1);
					if(cur[0]==next[0] && cur[1]==next[1]) { // 위치가 같으면
						int[] max = new int[3]; //최대값인 군집 방향, 최대값, 미생물총수
						max[0] = cur[3];
						max[1] = cur[2];
						max[2] = cur[2];
						while(cur[0]==next[0] && cur[1]==next[1]) {
							if(max[1] > next[2]) {
								max[2] += next[2];
							}else {
								max[0] = next[3];
								max[1] = next[2];
								max[2] += next[2];
							}
							mi.remove(i+1);
							j++;
							if(mi.size() > i+1) next = mi.get(i+1); //다음 값이 있으면 계속 비교
							else break;
						} //같은 값 정렬완료
						mi.set(i,new int[] {cur[0],cur[1],max[2],max[0]});
					}
				}
				time++;
			}
			result = 0;
			for (int i = 0, idx = mi.size(); i < idx; i++) {
				int[] arr = mi.get(i);
				result += arr[2];
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}

class Compare implements Comparator<int[]>{

	@Override
	public int compare(int[] o1, int[] o2) {
		if(o1[0] == o2[0]) return o1[1] - o2[1];
		return o1[0]-o2[0];
	}
	
}