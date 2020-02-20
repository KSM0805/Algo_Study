package swea.d2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class D2_2005 {
	public static void main(String[] args) throws FileNotFoundException { 
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//1. N을 입력받는다.
			int n = sc.nextInt();
			//함수 호출
			System.out.println("#"+test_case);
			pascal(n);
		}//for end
	}

	private static int[] pascal(int n) {
		//2. N은 행이고 열은 행의 숫자와 같다.
		int[] arr = new int [n];
		//4. (1,1),(2,1)(2,2)는 모두 1을 출력한다.
		if(n<=2) {
			Arrays.fill(arr, 1);
			System.out.println("1");
			if(n==2) {
				System.out.println("1 1");
			}
			return arr;
		}
		//3.  i,j는 (i-1, j-1)+(i-1,j)의 합을 더한 것과 같다.
		int[] beforArray = pascal(n-1);
		for (int i = 0; i < n; i++) {
			if(i==0 || i==n-1) {
				arr[i] = 1;
				System.out.print("1 ");
			}
			else {
				arr[i] = beforArray[i-1]+beforArray[i];
				System.out.print(arr[i]+" ");
			}
		}
		System.out.println();
		return arr;
	}

}
