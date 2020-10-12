package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Challenge {
	public static void main(String[] args) {
//		System.out.println(solution(125));
//		int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
//		System.out.println(Arrays.toString(solution2(arr)));
		
		int[][] num = {{1,5},{2,5},{3,5},{4,5}};
		System.out.println(solution3(5, num));
	}
	public static int solution(int n) {
        char[] arr = Integer.toString(n, 3).toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
			char temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
        String str = "";
        for (int i = 0; i < arr.length; i++) {
			str += arr[i];
		}
//        System.out.println(str);
        return Integer.parseInt(str, 3);
    }
	public static int[] solution2(int[][] arr) {
        int[] answer = {0,0};	//[0]:0의 갯수,  [1]: 1의 갯수
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[] {0, arr.length, 0, arr[0].length});
        top:
        while(!q.isEmpty()) {
        	int[] cur = q.poll();
        	if(cur[0] + 1 == cur[1]) {
        		answer[arr[cur[0]][cur[2]]]++;
        	} else {	// 아닌 경우
        		// 처음 만나는 애를 기준으로 잡는다
        		int num = arr[cur[0]][cur[2]];
        		for (int i = cur[0]; i < cur[1]; i++) {
					for (int j = cur[2]; j < cur[3]; j++) {
						if(num != arr[i][j]) {
							// 분할 정복하기
							int midR = (cur[0] + cur[1]) / 2;
							int midC = (cur[2] + cur[3]) / 2;
							q.add(new int[] {cur[0], midR, cur[2], midC});
							q.add(new int[] {cur[0], midR, midC, cur[3]});
							q.add(new int[] {midR,cur[1], cur[2], midC});
							q.add(new int[] {midR,cur[1], midC,cur[3]});
							continue top;
						}
					}
				}
        		// 성공하면 합친다.
        		answer[num]++;
        	}
        }
        return answer;
    }
	public static int solution3(int n, int[][] edges) {
		// 말단 노드를 먼저 탐색하고
        int answer = 0;
        int[][] dis = new int[n+1][n+1];
        int[] last = new int[n+1];
        for (int i = 0; i < dis.length; i++) {
			Arrays.fill(dis[i], Integer.MAX_VALUE);
			dis[i][i] = 0;
		}
        for (int i = 0; i < edges.length; i++) {
			dis[edges[i][0]][edges[i][1]] = 1;
			dis[edges[i][1]][edges[i][0]] = 1;
			last[edges[i][0]]++;
			last[edges[i][1]]++;
		}
        // 말단노드 저장할것
        ArrayList<Integer> lastarr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
			if(last[i] == 1) {
				lastarr.add(i);
			}
		}
        // 말단 노드가 두개면 그 두개와 가운데 채택
        if(lastarr.size() <= 2) {
        	LinkedList<int[]> q = new LinkedList<>();
        	boolean[] visit = new boolean[n+1];
        	for (int i = 1; i <= n; i++) {
				if(dis[lastarr.get(0)][i] == 1) {
					visit[lastarr.get(0)] = true;
					visit[i] = true;
					q.add(new int[] {lastarr.get(0), i, 1});
					break;
				}
			}
        	while(!q.isEmpty()) {
        		int[] cur = q.poll();
        		if(cur[1] == lastarr.get(1)) {
        			return (cur[2] * 2) / 3;
        		}
        		for (int i = 1; i <= n; i++) {
					if(dis[cur[1]][i] == 1 && !visit[i]) {
						visit[i] = true;
						q.add(new int[] {cur[1], i, cur[2] + 1});
					}
				}
        	}
        	// 아니면 말단노드를 가중치로 정렬한다음에 큰거 선택
        } else {
        	
        }
        return answer;
    }
	
}
