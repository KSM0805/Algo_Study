package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class D4_1808_지희의고장난계산기 {
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
			LinkedList<Number> q = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				if(num.get(i) > 0) {
					q.offer(new Number(num.get(i), X, 1));//한자릿수
				}
			}
			while(!q.isEmpty()) {
				Number cur = q.poll();
//				System.out.println(cur.toString());
				int re = cur.target % cur.num;
				int dv = cur.target / cur.num;
				if(re == 0 && dv == 1) {
					result = Math.min(result, cur.cnt );
					continue;
				}
				if(cur.cnt > result) {
					continue;
				}
				//자릿수를 늘리거나 다시 한자릿수부터거나
				//1. 자릿수늘리기
				if(cur.num < X/2) {
					for (int i = 0; i < size; i++) {
						int next = cur.num*10 + num.get(i);
						q.offer(new Number(next, cur.target, cur.cnt+1));
					}
				}
				//2. 나눠지면 한자릿수부터
				if(cur.num != 1 && re==0) {
					for (int i = 0; i < size; i++) {
						if(num.get(i) > 0) {
							q.offer(new Number(num.get(i), dv, cur.cnt+2)); //곱하기와 숫자 -> 두 개
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

class Number{
	int num; //사용할 숫자
	int target; //나눠야할 숫자
	int cnt; //누른 횟수
	public Number(int num, int target, int cnt) {
		super();
		this.num = num;
		this.target = target;
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "Number [num=" + num + ", target=" + target + ", cnt=" + cnt + "]";
	}
	
}