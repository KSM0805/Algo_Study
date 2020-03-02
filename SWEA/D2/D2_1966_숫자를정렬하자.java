package swea.d2;

import java.util.Scanner;

public class D2_1966 {
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int size = sc.nextInt();
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = sc.nextInt();
			}
			for (int i = 0; i < size-1; i++) {
				int min = arr[i];
				int pos=i;
				for (int j = i+1; j <size; j++) {
					if(min > arr[j]) {
						min = arr[j];
						pos = j;
					}
				}
				int tmp = arr[i];
				arr[i] = arr[pos];
				arr[pos] = tmp;
			}
			System.out.print("#"+test_case+" ");
			for (int i : arr) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
}
