package algo_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17822_원판돌리기 {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer>[] disk = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			disk[i] = new LinkedList<Integer>();
			st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int j = 0; j < M; j++) {
				disk[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < T; i++) {
			boolean isDelete = false;	// 삭제된 수가 있는지 확인
			st = new StringTokenizer(bf.readLine().trim(), " ");
			int x = Integer.parseInt(st.nextToken());	// 돌릴 원판 번호 x의 배수
			int d = Integer.parseInt(st.nextToken());	// 방향 0: 시계 방향, 1: 반시계 방향
			int k = Integer.parseInt(st.nextToken());	// 회전 칸 수
			// 돌리기
			for (int j = x; j <= N; j+= x) {
				int target = j - 1; // 돌릴 원판의 인덱스
				if (d == 0) {	// 시계방향
					for (int l = 0; l < k; l++) {
						int tmp = disk[target].pollLast();
						disk[target].addFirst(tmp);
					}
				} else {	// 반시계방향
					for (int l = 0; l < k; l++) {
						int tmp = disk[target].poll();
						disk[target].addLast(tmp);
					}
				}
			}
			int total = 0;
			int count = 0;
			// 인접한 수 지우기 (가장 안쪽에서 부터 위와 오른쪽만 고려)
			// 지울 위치 저장
			LinkedList<int[]> deleteList = new LinkedList<int[]>();
			for (int j = 0; j < N; j++) {
				for (int l = 0; l < M; l++) {
					int targetNum = disk[j].get(l);
					if (targetNum == 0) continue;
					total += targetNum;
					count++;
					// 위 탐색
					int nextDisk = j + 1;
					int nextPosition = l;
					if(nextDisk < N) {
						if(targetNum == disk[nextDisk].get(nextPosition)) {
							deleteList.add(new int[] {j, l});
							deleteList.add(new int[] {nextDisk, nextPosition});
						}
					}
					
					// 오른쪽 탐색
					nextDisk = j;
					nextPosition = l + 1 >= M ? 0 : l + 1;
					if(targetNum == disk[nextDisk].get(nextPosition)) {
						deleteList.add(new int[] {j, l});
						deleteList.add(new int[] {nextDisk, nextPosition});
					}
				}
			} // for
			if (deleteList.isEmpty()) {
				// 모든 수의 평균을 구하고 
				double avg = (double) total / count;
				// 큰 수는 -1, 작은 수는  + 1
				for (LinkedList<Integer> list : disk) {
					for (int o = 0; o < M; o++) {
						int num = list.get(o);
						if (num == 0) continue;
						if(num > avg) {
							list.set(o, num - 1);
						} else if (num < avg) {
							list.set(o, num + 1);
						}
					}
				}
			} else {
				while(!deleteList.isEmpty()) {
					int[] cur = deleteList.poll();
					disk[cur[0]].set(cur[1], 0);
				}
			}
		} // for T
		// 수 합하기
		int total = 0;
		for (LinkedList<Integer> list : disk) {
			while (!list.isEmpty()) {
				total += list.poll();
			}
		}
		System.out.println(total);
	}
}
