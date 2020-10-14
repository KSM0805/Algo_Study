package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {
	private static int[][] A;
	private static int[][] cnt;
	private static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		int time = 0;
		
		A = new int[3][3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = new int[101][2];
		for (int i = 0; i < 101; i++) {
			cnt[i][0] = i;
		}
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
		
		ArrayList<int[]> temp = new ArrayList<>();
		ArrayList<Integer> next = new ArrayList<>();
		while(time <= 100) {
			if(r < A.length && c < A[0].length && A[r][c] == k) break;
			if(A.length >= A[0].length) {	// R 연산
				// 행을 정렬한다.
				for (int i = 0; i < A.length; i++) {
					// 행마다
					for (int j = 0; j < 101; j++) {
						cnt[j][1] = 0;
						pq.add(cnt[j]);
					}
					// 그리고 하나씩 센다.
					for (int j = 0; j < A[i].length; j++) {
						if(A[i][j] != 0) {
							cnt[A[i][j]][1]++;
							pq.remove(cnt[A[i][j]]);
							pq.add(cnt[A[i][j]]);
						}
					}
					while(!pq.isEmpty()) {
						int[] cur = pq.poll();
						if(cur[1] == 0) continue;
						next.add(cur[0]);
						next.add(cur[1]);
					}
					int[] swap = new int[next.size()];
					for (int j = 0; j < swap.length; j++) {
						swap[j] = next.get(j);
					}
					temp.add(swap);
					next.clear();
				}
				// 모든 행을 한 다음에 가장 큰 크기를 찾는다.
				int max = 0;
				for (int i = 0; i < temp.size(); i++) {
					max = Math.max(max, temp.get(i).length);
				}
				if(max > 100) max = 100;
				A = new int[A.length][max];
				for (int i = 0; i < temp.size(); i++) {
					for (int j = 0; j < temp.get(i).length; j++) {
						A[i][j] = temp.get(i)[j];
					}
					for (int j = temp.get(i).length; j < max; j++) {
						A[i][j] = 0;
					}
				}
				temp.clear();
			} else {	// C 연산
				for (int i = 0; i < A[0].length; i++) {
					// 열마다
					for (int j = 0; j < 101; j++) {
						cnt[j][1] = 0;
						pq.add(cnt[j]);
					}
					// 그리고 하나씩 센다.
					for (int j = 0; j < A.length; j++) {
						if(A[j][i] != 0) {
							cnt[A[j][i]][1]++;
							pq.remove(cnt[A[j][i]]);
							pq.add(cnt[A[j][i]]);
						}
					}
					while(!pq.isEmpty()) {
						int[] cur = pq.poll();
						if(cur[1] == 0) continue;
						next.add(cur[0]);
						next.add(cur[1]);
					}
					int[] swap = new int[next.size()];
					for (int j = 0; j < swap.length; j++) {
						swap[j] = next.get(j);
					}
					temp.add(swap);
					next.clear();
				}
				// 모든 열을 한 다음에 가장 큰 크기를 찾는다.
				int max = 0;
				for (int i = 0; i < temp.size(); i++) {
					max = Math.max(max, temp.get(i).length);
				}
				if(max > 100) max = 100;
				A = new int[max][A[0].length];
				for (int i = 0; i < temp.size(); i++) {
					for (int j = 0; j < temp.get(i).length; j++) {
						A[j][i] = temp.get(i)[j];
					}
					for (int j = temp.get(i).length; j < max; j++) {
						A[j][i] = 0;
					}
				}
				temp.clear();
			}
			time++;
			
		}
		System.out.println(time > 100 ? "-1" : time);
	}
}
