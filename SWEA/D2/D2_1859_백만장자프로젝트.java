package swea.d2;

import java.util.Scanner;

public class D2_1859 {
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int day = sc.nextInt();
			int[] price = new int[day];
			for (int i = 0; i < day; ++i) {
				price[i] = sc.nextInt();
			}
			System.out.print("#"+test_case+" ");
			System.out.println(millionProject(price,0,day)); // 가격 배열과 시작위치 날짜의 길이 
		}//for end
	}

	private static long millionProject(int[] price, int start, int day) {
		int max = -1, pos = -1, sum = 0;
		if(start > day-1) {
			return 0;
		}
		//1. 배열의 max값을 찾는다.
		for (int i = start; i < day; i++) {
			if(max<price[i]) {
				max = price[i];
				pos = i;
			}
		}
		if(pos==0) return 0;
		//2. start부터 max까지의 갯수를 max에 곱하고 원소의 합을 뺴준다.
		else{
			for (int i = start; i < pos; i++) {
				sum += price[i];
			}
			//3. max값의 위치부터 끝까지 다시 max 서치
			return max*(pos-start) - sum + millionProject(price, pos+1, day);
		}
	}
}
