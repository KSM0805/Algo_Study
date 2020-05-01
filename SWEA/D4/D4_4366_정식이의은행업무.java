package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class D4_4366_정식이의은행업무 {
	private static long result;
	private static String binary;
	private static String tri;
	private static HashSet<Long> list = new HashSet<>();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			binary = bf.readLine().trim();
			tri = bf.readLine().trim();
			list.clear();
			result = 0;
			// 1. 2진수를 한자리씩 바꿔가며 리스트에 넣는다.
			StringBuilder tmp = new StringBuilder();
			for (int i = 0; i < binary.length(); i++) {
				tmp.delete(0, tmp.length());
				tmp.append(binary);
				tmp.insert(i, binary.charAt(i) == '0'? '1' : '0');
				tmp.delete(i+1, i+2);
				list.add(Long.parseLong(tmp.toString(), 2));
			}
			// 2. 3진수를 한자리씩 바꿔가며 리스트에 있는지 검사한다.
			top:
			for (int i = 0; i < tri.length(); i++) {
				tmp.delete(0, tmp.length());
				tmp.append(tri);
				char ch = tmp.charAt(i);
				for (int j = 0; j < 3; j++) {
					if(ch-'0' != j) {
						tmp.insert(i, j+"");
						tmp.delete(i+1, i+2);
						long num = Long.parseLong(tmp.toString(),3);
						if(list.contains(num)) {
							result = num;
							break top;
						}
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end
}
