package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class D3_7510
{
    private static int N;
    private static int pos;
    private static int cnt;
	private static int result;
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			if( N==1 ) {
				System.out.println("#" + test_case + " 1");
				continue;
			}
			pos = 2;
			cnt = 1;
			if( (N-1) % pos == 0) {
				cnt++;
			}
			pos++;
			int x = (pos+1)*pos/2;
			while(x<N){
				x = (pos+1)*pos/2;
				if( pos%2==0 && (N-x)%pos == 0) {
					cnt++;
				}
				else if( pos%2==1 && (N-x)%pos == 0 ) {
					cnt++;
				}
				pos++;
			}
			System.out.println("#"+test_case+" "+cnt);
		}//for end
	}
}