package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Test_5658_보물상자비밀번호 {
	private static int N;
	private static int K;
//	private static StringBuilder num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); // 8~28 ->최대 FFFFFFF -> 2억 6천
			K = Integer.parseInt(st.nextToken()); //K개 이하
			/*num = new StringBuilder(bf.readLine());
			HashSet<Integer> set = new HashSet<>();
			int cnt = N / 4;
			for (int i = 0; i < cnt; i++) {
				for (int j = 0; j < num.length(); j+=cnt) {
					set.add(Integer.valueOf(num.substring(j, j+cnt),16));
				}
				num.insert(0, num.charAt(num.length() - 1));
				num.delete(num.length() - 1, num.length());
			}
			Object[] list = set.toArray();
			Arrays.sort(list);
			
			System.out.println("#" + test_case + " " + list[list.length-K]);*/
			
			String num = bf.readLine().trim();
			int cnt = N/4;
			num = num + num.substring(0, cnt);
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(num.substring(i, i+cnt), 16);
				if(!list.contains(n)) list.add(n);
			}
			Collections.sort(list);
			System.out.println("#" + test_case + " " + list.get(list.size()-K));
		}//for end
	}
}
