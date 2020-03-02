package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3142_영준이와신비한뿔의숲 {
	private static int result;
	private static int animals;
	private static int horn;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			horn = Integer.parseInt(st.nextToken());
			animals = Integer.parseInt(st.nextToken());
			result = horn - animals;
			System.out.println("#"+test_case+" "+ (animals - result) + " " + result);
		}//for end
	}
}
