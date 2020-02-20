package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1244_dfs {
	
	private static int Change;
	private static int len;
	private static int result;
	private static StringBuilder N;

	public static void main(String[] args) throws Exception { 
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = new StringBuilder(st.nextToken());
			Change = Integer.parseInt(st.nextToken());
			len = N.length();
			result = 0;
			boolean sort = true;
			for (int i = 0; i < len-1; i++) {
				if(N.charAt(i)<=N.charAt(i+1)) { //정렬이 안되있을 경우
					sort = false;
					break;
				}
			}
			if(!sort) {
				dfs(0,0); // 0번째부터 0번교환
			}else { //정렬이 되있는 경우
				if(Change%2 != 0) { //교환횟수가 홀수이면
					int x = N.charAt(len-1)-'0';
					N.replace(len-1, len, Character.toString(N.charAt(len-2)));
					N.replace(len-2, len-1, Integer.toString(x));
					result = Integer.parseInt(N.toString());
				}else {
					result = Integer.parseInt(N.toString());
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}

	private static void dfs(int num, int cnt) {
		if(cnt == Change) {
			result = Math.max(result, Integer.parseInt(N.toString()));
			return ;
		}
		for (int i = num; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if(N.charAt(i) <= N.charAt(j)) {
					int x = N.charAt(j)-'0';
					N.replace(j, j+1, Character.toString(N.charAt(i)));
					N.replace(i, i+1, Integer.toString(x));
					dfs(i,cnt+1);
					x = N.charAt(i) - '0';
					N.replace(i, i+1, Character.toString(N.charAt(j)));
					N.replace(j, j+1, Integer.toString(x));
				}
			}
		}
	}

}