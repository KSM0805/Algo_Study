package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_1229 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		LinkedList<Object> list = new LinkedList<>(); // ArraysList는 배열의 크기를 지정해주는게 좋음

		for (int test_case = 1; test_case <= T; test_case++) {
			list.clear();
			int num = Integer.parseInt(bf.readLine().trim());
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < num; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			int instNum= Integer.parseInt(bf.readLine().trim());
			StringTokenizer st2 = new StringTokenizer(bf.readLine().trim()," ");
			int i = 0;
			
			String inst = "";
			int a = 0, b = 0;
			while(i<instNum) {
				inst = st2.nextToken();
				if(inst.equals("I")) {
					a = Integer.parseInt(st2.nextToken());
					b = Integer.parseInt(st2.nextToken());
					for (int j=0; j <b; j++) {
						list.add(a+j, Integer.parseInt(st2.nextToken()));
					}
				}
				else {
					a = Integer.parseInt(st2.nextToken());
					b = Integer.parseInt(st2.nextToken());
					for (int j=0; j <b; j++) {
						list.remove(a);
					}
				}
				i++;
			}
			System.out.print("#"+test_case+" ");
			for (int j = 0; j < 10; j++) {
				System.out.print(list.get(j)+ " ");
			}
			System.out.println();
		}//for end
	}
}
