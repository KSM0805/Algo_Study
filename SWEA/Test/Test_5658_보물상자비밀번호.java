package swTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Test_5658_보물상자비밀번호 {
	private static int N;
	private static int K;
	private static char[] num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken()); // 8~28 -> 최대 42억
			K = Integer.parseInt(st.nextToken()); //K개 이하
			num = bf.readLine().toCharArray();
			//4개로 나누어서 0회차 : 0~N/4-1 = N/4~N/2-1 = N/2~3N/4-1 = 3N/4~N-1
			// 1회차 : -1~N/4-2 = N/4-1~N/2-2 = N/2-1~3N/4-2 = 3N/4-1~N-2
			int len = N/4;
			int start = 0;
			HashSet<Long> numbers = new HashSet<>();
			while(start != len*3) { // start가 한바퀴를 돌았을때
				int ns = start;
				StringBuilder sb = new StringBuilder();
				for (int i = 0, idx = start; i < len; i++) {
					sb.append(num[idx]);
					idx++;
					if(idx>N-1) idx -= N;
				}
				numbers.add(Long.valueOf(sb.toString(),16)); //16진수를 정수형으로 변환
				for (int i = 1; i < 4; i++) {
					sb.delete(0, sb.length());
					ns += len;
					if(ns>N-1) ns -= N;
					for (int j = 0, idx = ns; j < len; j++) {
						sb.append(num[idx]);
						idx++;
						if(idx>N-1) idx -= N;
					}
					numbers.add(Long.valueOf(sb.toString(),16));
				}
				start -= 1;
				if(start<0) start += N;
			}
//			Iterator<String> iter = numbers.iterator();
//			String[] arr = new String[numbers.size()];
//			int pos = 0;
//			while(iter.hasNext()) {
//				arr[pos] = iter.next();
//				pos++;
//			}
//			Arrays.sort(arr, new Comparator<String>() {
//
//				@Override
//				public int compare(String o1, String o2) {
//					for (int i = 0; i < o1.length(); i++) {
//						if((int)o1.charAt(i) < (int)o2.charAt(i)) {
//							return 1;
//						}else if((int)o1.charAt(i) == (int)o2.charAt(i)) {
//							continue;
//						}else {
//							return -1;
//						}
//					}
//					return 0;
//				}
//			});
			Object[] arr = numbers.toArray(); // object로 바로 정렬하기!
			Arrays.sort(arr);
			System.out.println("#"+test_case+" "+arr[arr.length-K]);
		}//for end
	}
}
