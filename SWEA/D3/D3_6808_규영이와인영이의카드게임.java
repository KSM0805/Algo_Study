package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임 {
	private static int result;
	private static int[] youCards;
	private static int[] myCards;
	private static boolean[] visit;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			youCards = new int[9]; //9개
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < 9; i++) {
				youCards[i] = Integer.parseInt(st.nextToken());
			}
			myCards = new int[9];
			int pos = 0;
			for (int i = 1; i < 19; i++) {
				for (int j = 0; j < 9; j++) {
					if(i == youCards[j]) break;
					if(j==8) {
						myCards[pos] = i;
						pos++;
					}
				}
			} //분류끝
			int score;
			for (int i = 0; i < 9; i++) {
				score = (myCards[i]>youCards[0]) ? (myCards[i]+youCards[0]) : -(myCards[i]+youCards[0]) ;
				visit = new boolean[9];
				visit[i] = true;
				dfs(i,1,score); //현재 숫자와 대결라운드, 점수 
			}
			System.out.println("#"+test_case+" "+ (362880-result) + " " + result );
		}//for end
	}//main end

	private static void dfs(int pos, int cnt, int score) {
		if(cnt==9) {
			if(score>=0) {
				result++;
			}
			return;
		}
		for (int i = 0; i < 9; i++) {
			if(!visit[i]) {
				visit[i] = true;
				if(myCards[i] > youCards[cnt]) {
					dfs(i, cnt+1, score + (myCards[i]+youCards[cnt]));
				}else{
					dfs(i, cnt+1, score - (myCards[i]+youCards[cnt]));
				}
				visit[i] = false;
			}
		}
	}
}
