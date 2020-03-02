package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_5789 {
	private static int result;
	private static int Q;
	private static int N;
	private static int[] boxes;
	private static int L;
	private static int R;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			boxes = new int[N];
			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				L = Integer.parseInt(st.nextToken());
				R = Integer.parseInt(st.nextToken());
				for (int j = L; j <= R; j++) {
					boxes[j-1] = i+1;
				}
			}
			System.out.print("#"+test_case+" ");
			for (int a : boxes) {
				System.out.print(a + " ");
			}System.out.println();
		}//for end
	}
}
