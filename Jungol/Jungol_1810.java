package jungol;

import java.util.Scanner;

public class Jungol_1810 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[9];
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			num[i] = sc.nextInt();
			sum += num[i];
		}
		sum = sum - 100;
		for (int i = 0; i < 9; i++) {
			for (int j = i+1; j < 9; j++) {
				if(num[j] == sum-num[i]) {
					num[i] = 0;
					num[j] = 0;
				}
			}
		}
		for (int i : num) {
			System.out.print(i == 0 ? "" : (i+"\n"));
		}
	}

}

/*조합을 이용하여 직접 짜보기
 * public class jungol_1810_combi {
	static int[] num;
	static int[] re;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = new int[9];
		re = new int[7];
		
		for (int i = 0; i < 9; i++) {
			num[i] = sc.nextInt();
		}
		combination(0,1);
	}

	private static void combination(int cnt, int start) {
		if(cnt == 7) {
			
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += re[i];
			}
			if(sum==100) {
				System.out.println(Arrays.toString(re));
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			re[cnt] = num[i-1];
			combination(cnt+1, i+1);
		}
		
	}

}*/


/*강사님이 알려준 방식
 * public class jungod_1810_Main {
	static int target = 100;
	static int n = 9;
	static int r = 7;
	static int[] numbers; //난쟁이 값을 저장할 배열
	static int[] result;  //난쟁이 7명에 대한 조합을 저장할 배열

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/jungol/bank/1810.txt"));
		Scanner sc = new Scanner(System.in);
		
		numbers = new int[n];
		result = new int[r];
		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}
		combination(0,0,0);
	}
	/**
	 * 난쟁이 조합을 구하는 함수
	 * @param cnt 	배열번호
	 * @param start 조합의 시작 수
	 * @param sum   cnt번째 까지의 난쟁이 모자 숫자 합
	 */
	/*private static void combination(int cnt, int start, int sum) {
		if(cnt == r) {
			if(sum==target) { //구하고 싶은 조합
				for (int s : result) {
					System.out.println(s);
				}
			}
			return;
		}
		for (int i = start; i < n; i++) {
			result[cnt] = numbers[i];
			combination(cnt+1, i+1, sum+result[cnt]);
		}
	}
}*/

