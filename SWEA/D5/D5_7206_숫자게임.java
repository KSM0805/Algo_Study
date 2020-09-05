package d5;

import java.io.FileInputStream;
import java.util.Scanner;

public class D5_7206_숫자게임 {
	private static int result;
	private static String num;

	static int[] mem = new int[100000];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			num = sc.next();
			result = dfs(num);
			System.out.println("#"+test_case+" "+result);
		}//testcase end
	}//main end

	private static int dfs(String n) {
//		System.out.println(n + " ");
		if(n.length() < 2) return 0;
	    if(mem[Integer.parseInt(n)] != 0) return mem[Integer.parseInt(n)];
	    int max = 0;
	    int idx = 0;
	    int len = n.length() - 1;
	    for (int i = 1, size = 1 << len; i < size; i++) {
	    	idx = 0;
	    	int a = 1;
			for (int j = 0; j < len; j++) {
				if((i & (1 << j)) != 0) {
					a *= Integer.parseInt(n.substring(idx, j + 1));
					idx = j + 1;
				}
			}
			a *= Integer.parseInt(n.substring(idx, len + 1));
			max = Math.max(max, dfs(Integer.toString(a)) + 1);
		}
		/*for (int i = 1; i < n.length(); i++) {
			int a = Integer.parseInt(n.substring(0, i));
			String b = n.substring(i, n.length());	// 최대 4자리
			if(b.length() > 1) {	// b가 쪼개질 수 있을 때
				for (int j = 1; j < b.length(); j++) {
					int c = Integer.parseInt(b.substring(0, j));
					String d = b.substring(j, b.length());
					if(d.length() > 1) { // 최대 3자리
						for (int k = 1; k < d.length(); k++) {
							int e = Integer.parseInt(d.substring(0,k));
							String f = d.substring(k, d.length());
							if(f.length() > 1) { // 최대 2자리
								int g = Integer.parseInt(f.substring(0, 1));
								int h = Integer.parseInt(f.substring(1, 2));
								max = Math.max(max, dfs(Integer.toString(a * c * e * g * h)) + 1);
							}
							max = Math.max(max, dfs(Integer.toString(a * c * e * Integer.parseInt(f))) + 1);
						}
					}
					max = Math.max(max, dfs(Integer.toString(a * c * Integer.parseInt(d))) + 1);
				}
			}
			max = Math.max(max, dfs(Integer.toString(a * Integer.parseInt(b))) + 1);
		}*/
	    mem[Integer.parseInt(n)] = max;
	    return mem[Integer.parseInt(n)];
	}
}
