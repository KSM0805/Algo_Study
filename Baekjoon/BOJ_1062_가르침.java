package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_1062_가르침 {
	private static int result;
	private static int K;
	private static int N;
	private static String[] str;
	private static int[] num;
	private static String need = "antic";
	private static HashSet<Character> hs = new HashSet<>();
	private static String alph = "";
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		str = new String[N];
		num = new int[N];
		/*for (int i = 0; i < N; i++) {
			str[i] = "";
			char[] ch = bf.readLine().trim().toCharArray();
			for (int j = 4, size = ch.length - 4; j < size; j++) {
				if(need.indexOf(ch[j]) == -1) {
					str[i] += ch[j];
					hs.add(ch[j]);
				}
			}
		}
		Iterator<Character>iter = hs.iterator();
		while(iter.hasNext()) {
			alph += iter.next();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0, size = str[i].length(); j < size; j++) {
				num[i] = num[i] | (1<<alph.indexOf(str[i].charAt(j)));
			}
		}*/
		for (int i = 0; i < N; i++) {
			str[i] = bf.readLine().trim();
			for (int j = 0, size = str[i].length(); j < size; j++) {
				num[i] = num[i] | (1<<str[i].charAt(j)-'a');
			}
		}
		// anta 로 시작하고 tica로 끝난다 -> a, n, t, i, c 최소 5개를 안다.
		if(K >= 5) {
			K -= 5;
			select(0, 532741, 0);
//			for (int i = 0, size = 1<<alph.length(); i < size; i++) {
//				int cnt = 0;
//				for (int j = 0; j < alph.length(); j++) {
//					if((i & (1<<j)) != 0) cnt++;
//				}
//				if(cnt <= K) {
//					int check = 0;
//					for (int j = 0; j < N; j++) {
//						if((i & num[j]) == num[j]) check++;
//					}
//					result = Math.max(result, check);
//				}
//			}
			/*for (int i = 1<<'t'-'a', size = 1<<26; i < size; i++) {
				int cnt = 0;
				if((i & 532741) != 532741) continue;
				for (int j = 0; j < 26; j++) {
					if((i & (1<<j)) != 0) cnt++;
				}
				if(cnt <= K) {
					int check = 0;
					for (int j = 0; j < N; j++) {
						if((i & num[j]) == num[j]) check++;
					}
					result = Math.max(result, check);
				}
			}*/
		}
//		System.out.println(alph);
		System.out.println(result);
	}

	private static void select(int cnt, int n, int idx) {
		if(cnt <= K) {
			int check = 0;
			for (int i = 0; i < N; i++) {
				if((n & num[i]) == num[i]) check++;
			}
			result = Math.max(result, check);
			if(cnt == K) return;
		}
		for (int i = idx + 1; i < 26; i++) {
			if((n & 1 << i) == 1<<i) continue;
			select(cnt + 1, n | 1<<i, i);
		}
	}
}
