package pzhao.com;

import java.io.File;
import java.util.Scanner;

class UF_quickUnion{
	int a[];
	int count;
	public UF_quickUnion(int n){
		a=new int[n];
		for(int i=0;i<a.length;i++){
			a[i]=i;
		}
		count=n;
	}
	public int find(int q){
	while(q!=a[q]){
		q=a[q];
	}
		return a[q];
	}
	public void union(int p,int q){
		int qId=find(q);
		int pId=find(p);
		if(pId==qId)return;
		else {
			a[pId]=qId;
	//		System.out.println(p+":"+q);
			count--;
		}
		
	}
}


class UF_quick{
	int[] sz; 
	int[] a;
	int count;
	
	public UF_quick(int n){
		a=new int[n];
		sz=new int[n];
		for(int i=0;i<a.length;i++){
			a[i]=i;
			sz[i]=1;
		}
		count=n;
	}
	
	public int find(int p){
		while(p!=a[p]){
			p=a[p];
		}
		return p;
	}
	
	public void union(int p,int q){
		int pId=find(p);
		int qId=find(q);
		if(pId==qId)return;
		else {
			if(sz[pId]>sz[qId]){
				a[qId]=pId;
				sz[pId]+=sz[qId];
			}
			else {
				a[pId]=qId;
				sz[qId]+=sz[qId];
			}
			count--;
		//	System.out.println(p+":"+q);
		}
				
	}
	
}


public class UF {
	int[] a;
	int count;

	public UF(int r) {
		a = new int[r];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		count = r;
	}

	void union(int p, int q) {
//		for(int i=0;i<a.length;i++){
//			System.out.print(" "+i);
//		}
//		System.out.println();
//		for(int i=0;i<a.length;i++){
//			System.out.print(" "+a[i]);
//		}
//		System.out.println();
		if (find(p) == find(q))
			return;
		else {
			int t=a[p];
			for (int i = 0; i < a.length; i++) {
				if (a[i] == t)
					a[i] = a[q];
			}
			count--;
		//	System.out.println(p+":"+q);
		}
	}

	int find(int p) {
		return a[p];

	}

	boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	int count() {
		return count;
	}

	public static void main(String[] argus) throws Exception {
		
		Scanner scanner=new Scanner(new File("mediumUF.txt"));
		int N=scanner.nextInt();
		UF uf = new UF(N);
		System.out.println("UF:");
		Long startLong=System.currentTimeMillis();
		while(scanner.hasNext())
		{
			uf.union(scanner.nextInt(), scanner.nextInt());
		}
		System.out.println("UF cost time:"+(System.currentTimeMillis()-startLong));
		//	System.out.println(b[i]+":"+b[i+1]);
		Scanner scanner2=new Scanner(new File("mediumUF.txt"));
		N=scanner2.nextInt();
		UF_quickUnion uf_quickUnion=new UF_quickUnion(N);
		System.out.println("UF_quickUnion:");
		Long startLong1=System.currentTimeMillis();
		while(scanner2.hasNext())
		{
			uf_quickUnion.union(scanner2.nextInt(), scanner2.nextInt());
		}
		System.out.println("UF_quickunoin cost time:"+(System.currentTimeMillis()-startLong1));
		
		Scanner scanner3=new Scanner(new File("mediumUF.txt"));
		N=scanner3.nextInt();
		UF_quick uf_quick=new UF_quick(N);
		System.out.println("UF_quick:");
		Long startLong2=System.currentTimeMillis();
		while(scanner3.hasNext())
		{
			uf_quick.union(scanner3.nextInt(), scanner3.nextInt());
		}
		System.out.println("UF_quick cost time:"+(System.currentTimeMillis()-startLong2));
		scanner.close();
		scanner2.close();
		scanner3.close();
	}

}
