package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class D4_7701_염라대왕의이름정렬 {
	private static int N;
//	private static HashSet<String> names = new HashSet<>();
	private static TreeSet<String> names = new TreeSet<>(new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			int a = o1.length();
			int b = o2.length();
			if(a - b != 0){
				return a-b;
			}else return o1.compareTo(o2);
		}
	});
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			names.clear();
			N = Integer.parseInt(bf.readLine().trim());
			for (int i = 0; i < N; i++) {
				names.add(bf.readLine().trim());
			}
//			Object[] name = names.toArray();
//			Arrays.sort(name, new Comparator<Object>() {
//
//				@Override
//				public int compare(Object o1, Object o2) {
//					int x=0;
//					int a = ((String) o1).length();
//					int b = ((String) o2).length();
//					if(a > b){
//						return 1;
//					}else if(a < b){
//						return -1;
//					}
//					for (int i = 0; i < a; i++) {
//						if(((String) o1).charAt(i) > ((String) o2).charAt(i)) {
//							x = 1;
//							break;
//						}else if(((String) o1).charAt(i) < ((String) o2).charAt(i)) {
//							x = -1;
//							break;
//						}
//					}
//					return x;
//				}
//			});
			sb.delete(0, sb.length());
			Iterator<String> iter = names.iterator();
			while(iter.hasNext()) {
				sb.append(iter.next()).append("\n");
			}
//			for (int i = 0; i < name.length; i++) {
//				sb.append(name[i]).append("\n");
//			}
			System.out.print("#"+test_case+"\n"+sb.toString());
		}//testcase end
	}//main end
}
