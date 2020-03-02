package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_1859_Other {
	
	public static void main(String[] args) throws Exception{
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(in.readLine());
        for(int t=1;t<=T;t++) {
            int N=Integer.parseInt(in.readLine());
            int[] price=new int[N];
            
            StringTokenizer data=new StringTokenizer(in.readLine());
            for(int i=0;i<N;i++) {
                price[i]=Integer.parseInt(data.nextToken());
            }
             
            int start=0, count=0;
            long result=0;
             
            int maxindex;
            int i=start;
            do {
                 
                maxindex=maxidx(start,price);
                for(i=start;i<=maxindex;i++) {
                    if(maxindex==i) {
                        if(count>0) {
                            result=result+((long)count*price[i]);
                            count=0;
                        }
                    }
                    else if(i!=N-1) {
                        count++;
                        result-=(long)price[i];
                    }
                }
                start=maxindex+1;
            } while(i<price.length);
             
            System.out.printf("#"+t+" "+result);
            System.out.println();
             
        }
 
    }
     
    static int maxidx(int s, int value[]) {
        int maxidx=0;
        int max=0;
        for(int i=s;i<value.length;i++) {
            if(max<value[i]) {
                max=value[i];
                maxidx=i;
            }
        }
        return maxidx;
    }
 
}