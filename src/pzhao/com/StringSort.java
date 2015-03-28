package pzhao.com;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class LSD{
	public static void sort(String[] a,int w){
		int n=a.length;
		int R=256;
		String [] aux=new String[n];
		for(int d=w-1;d>=0;d--){
			int[] count=new int[R+1];
			for(int i=0;i<n;i++){
				count[a[i].charAt(d)+1]++;
			}
			for(int r=0;r<R;r++){
				count[r+1]+=count[r];
			}
			for(int i=0;i<n;i++){
				aux[count[a[i].charAt(d)]++]=a[i];
			}
			for(int i=0;i<n;i++){
				a[i]=aux[i];
			}
		}
	}
}


public class StringSort {
	public static void main(String[] args)throws IOException{
		Scanner scanner=new Scanner(new File("words3.txt"));
		String[] bStrings=new String[35];
		int i=0;
		while(scanner.hasNext()){
			bStrings[i]=scanner.next();
			i++;
		}
		for(int j=0;j<35;j++){
			System.out.print(bStrings[j]+" ");
			if(j%5==4)
				System.out.println();
		}
		System.out.println("After sort:");
		LSD.sort(bStrings, 3);
		for(int j=0;j<35;j++){
			System.out.print(bStrings[j]+" ");
			if(j%5==4)
				System.out.println();
		}
		
	}
	
	
}
