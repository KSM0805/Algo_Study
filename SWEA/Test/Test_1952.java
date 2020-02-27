package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test_1952 {
	private static int result;
	private static int[] price;
	private static int[] plan;
	private static int[] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			price = new int[4];
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			plan = new int[12];
			st = new StringTokenizer(bf.readLine().trim(), " ");
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			bfs();
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

	private static void bfs() {
		LinkedList<int[]> q = new LinkedList<>();
		visit = new int[12];
		if(plan[0] == 0) { // 1월이 0이면
			visit[0] = 0;
			q.offer(new int[] {0,0});
		}else { //0이 아니면
			q.offer(new int[] {0,plan[0]*price[0]}); //day가격
			q.offer(new int[] {0, price[1]}); //month 가격
			q.offer(new int[] {2,price[2]}); //3month 가격
			visit[0] = Math.min(plan[0]*price[0], price[1]);
			visit[2] = price[2];
			result = price[3]; //year 가격
		}
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int mon = arr[0];
			int totalP = arr[1];
			if(mon == 11) {
				result = Math.min(result, totalP);
				continue;
			}
			if(totalP > result) continue;
			if(plan[mon+1] == 0 && (visit[mon+1] > totalP || visit[mon+1]==0)) {
				visit[mon+1] = totalP;
				q.offer(new int[] {mon+1, totalP});
			}else {
				int nextP = totalP + (plan[mon+1] * price[0]);
				if(visit[mon+1] > nextP || visit[mon+1] == 0) {
					visit[mon+1] = nextP;
					q.offer(new int[] {mon+1, nextP});
				}
				nextP = totalP + price[1];
				if(visit[mon+1] > nextP || visit[mon+1] == 0) {
					visit[mon+1] = nextP;
					q.offer(new int[] {mon+1, nextP});
				}
				nextP = totalP + price[2];
				if(mon+3<12 && (visit[mon+3] > nextP || visit[mon+3] == 0)) {
					visit[mon+3] = nextP;
					q.offer(new int[] {mon+3, nextP});
				}else if(mon + 2 < 12 && (visit[mon+2] > nextP || visit[mon+2] == 0)){
					visit[mon+2] = nextP;
					q.offer(new int[] {mon+2, nextP});
				}else if(visit[mon+1] > nextP || visit[mon+1] == 0){
					visit[mon+1] = nextP;
					q.offer(new int[] {mon+1, nextP});
				}
			}
		}
	}
}
