package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class D4_1808_지희의고장난계산기2 {
	private static int result;
	private static int X;
	private static ArrayList<Integer> num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		num = new ArrayList<>();
		for (int test_case = 1; test_case <= T; test_case++) {
			num.clear();
			String str = bf.readLine().trim();
			for (int i = 0; i < 10; i++) {
				if(str.charAt(i<<1) == '1') num.add(i); //사용가능한 숫자 배열
			}
			X = Integer.parseInt(bf.readLine().trim());
			int size = num.size();
			result = Integer.MAX_VALUE;
			//bfs
			// 한자릿수부터 X의 자릿수 반만큼 따져보기
			LinkedList<int[]> q = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				if(num.get(i) > 0) {
					q.offer(new int[] {num.get(i), X, 1});//한자릿수
				}
			}
			while(!q.isEmpty()) {
				int[] cur = q.poll();
//				System.out.println(cur.toString());
				int re = cur[1] % cur[0];
				int dv = cur[1] / cur[0];
				if(re == 0 && dv == 1) {
					result = Math.min(result, cur[2]);
					continue;
				}
				if(cur[2] > result) {
					continue;
				}
				//자릿수를 늘리거나 다시 한자릿수부터거나
				//1. 자릿수늘리기
				if(cur[0] < X/2) {
					for (int i = 0; i < size; i++) {
						int next = cur[0]*10 + num.get(i);
						q.offer(new int[] {next, cur[1], cur[2]+1});
					}
				}
				//2. 나눠지면 한자릿수부터
				if(cur[0] != 1 && re==0) {
					for (int i = 0; i < size; i++) {
						if(num.get(i) > 0) {
							q.offer(new int[] {num.get(i), dv, cur[2]+2}); //곱하기와 숫자 -> 두 개
						}
					}
				}
			}
			System.out.print("#"+test_case+" ");
			if(result==Integer.MAX_VALUE) System.out.println("-1");
			else System.out.println(result+1);
		}//testcase end
	}//main end
}