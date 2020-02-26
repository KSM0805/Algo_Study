package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6900 {
	private static int result;
	private static int N;
	private static int M;
	private static char[][] number;
	private static int[] money;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			number = new char[N][8];
			money = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				number[i] = st.nextToken().toCharArray();
				money[i] = Integer.parseInt(st.nextToken());
			}
			String str;
			for (int i = 0; i < M; i++) {
				str = bf.readLine().trim();
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < 8; k++) {
						if(number[j][k] != '*' && number[j][k] != str.charAt(k)) {
							break;
						}
						if(k==7) result +=money[j];
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//for end
	}
}
