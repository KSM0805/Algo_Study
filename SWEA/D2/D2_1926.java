package swea.d2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D2_1926 {
	public static void main(String[] args) throws FileNotFoundException { 
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int num = sc.nextInt();
			for (int i = 1; i <= num; i++) {
				String str = Integer.toString(i);
				if(str.contains("3") || str.contains("6") || str.contains("9")) {
					for (int j = 0; j < str.length(); j++) {
						if(str.charAt(j)=='3' || str.charAt(j)=='6' || str.charAt(j)=='9') {
							System.out.print("-");
						}
					}
					System.out.print(" ");
				}
				else {
					System.out.print(i+" ");
				}
			}
			System.out.println();
		}//for end
	}

}
