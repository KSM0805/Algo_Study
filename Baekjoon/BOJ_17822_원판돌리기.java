package algo_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17822_���ǵ����� {
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
			boolean isDelete = false;	// ������ ���� �ִ��� Ȯ��
			st = new StringTokenizer(bf.readLine().trim(), " ");
			int x = Integer.parseInt(st.nextToken());	// ���� ���� ��ȣ x�� ���
			int d = Integer.parseInt(st.nextToken());	// ���� 0: �ð� ����, 1: �ݽð� ����
			int k = Integer.parseInt(st.nextToken());	// ȸ�� ĭ ��
			// ������
			for (int j = x; j <= N; j+= x) {
				int target = j - 1; // ���� ������ �ε���
				if (d == 0) {	// �ð����
					for (int l = 0; l < k; l++) {
						int tmp = disk[target].pollLast();
						disk[target].addFirst(tmp);
					}
				} else {	// �ݽð����
					for (int l = 0; l < k; l++) {
						int tmp = disk[target].poll();
						disk[target].addLast(tmp);
					}
				}
			}
			int total = 0;
			int count = 0;
			// ������ �� ����� (���� ���ʿ��� ���� ���� �����ʸ� ���)
			// ���� ��ġ ����
			LinkedList<int[]> deleteList = new LinkedList<int[]>();
			for (int j = 0; j < N; j++) {
				for (int l = 0; l < M; l++) {
					int targetNum = disk[j].get(l);
					if (targetNum == 0) continue;
					total += targetNum;
					count++;
					// �� Ž��
					int nextDisk = j + 1;
					int nextPosition = l;
					if(nextDisk < N) {
						if(targetNum == disk[nextDisk].get(nextPosition)) {
							deleteList.add(new int[] {j, l});
							deleteList.add(new int[] {nextDisk, nextPosition});
						}
					}
					
					// ������ Ž��
					nextDisk = j;
					nextPosition = l + 1 >= M ? 0 : l + 1;
					if(targetNum == disk[nextDisk].get(nextPosition)) {
						deleteList.add(new int[] {j, l});
						deleteList.add(new int[] {nextDisk, nextPosition});
					}
				}
			} // for
			if (deleteList.isEmpty()) {
				// ��� ���� ����� ���ϰ� 
				double avg = (double) total / count;
				// ū ���� -1, ���� ����  + 1
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
		// �� ���ϱ�
		int total = 0;
		for (LinkedList<Integer> list : disk) {
			while (!list.isEmpty()) {
				total += list.poll();
			}
		}
		System.out.println(total);
	}
}
