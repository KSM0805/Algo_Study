package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class D3_5986_새샘이와세소수_mem {
	private static int result;
	private static boolean[] num;
	private static int[] map;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		num = new boolean[1000];
		ArrayList<Integer> prime = new ArrayList<>(); //소수를 담을 배열
		for (int i = 2; i < 1000; i++) {
			if(!num[i]) {
				prime.add(i);
				int x = i*2;
				while(x<1000) {
					num[x] = true;
					x += i;
				}
			}
		}
		int size = prime.size();
		map = new int[1000]; // N
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				for (int k = j; k < size; k++) {
					int sum = prime.get(i) + prime.get(j) + prime.get(k);
					if(sum<1000) {
						map[sum]++;
					}
				}
			}
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.parseInt(bf.readLine());
			System.out.println("#"+test_case+" "+map[result]);
		}//testcase end
	}//main end
}
