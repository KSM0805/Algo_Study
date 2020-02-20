package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_3456 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			LinkedList<String> list = new LinkedList<>();
			for (int i = 0; i < 3; i++) {
				String str = st.nextToken();
				if(list.contains(str)) list.remove(str);
				else list.add(str);
			}
			System.out.println("#"+test_case+" "+list.peek());
		}//for end
	}
}
