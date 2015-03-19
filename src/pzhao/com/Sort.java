package pzhao.com;

import java.util.Random;

class Selection {
	public static void exch(Comparable[] a, int i, int j) {
		Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j].compareTo(a[min]) < 0)
					min = j;
			}
			exch(a, i, min);
		}

	}
}

class Insertion {
	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int j;
			Comparable tmp = a[i];
			for (j = i; j > 0 && (tmp.compareTo(a[j - 1]) < 0); j--) {
				a[j] = a[j - 1];
			}
			a[j] = tmp;
		}

	}
}

class Shell {
	public static void sort(Comparable[] a) {
		int n = a.length;
		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				int j;
				Comparable tmp = a[i];
				for (j = i; j >= h && (tmp.compareTo(a[j - h]) < 0); j -= h) {
					a[j] = a[j - h];
				}
				a[j] = tmp;
			}
			h = h / 3;
		}

	}
}

class Merge {
	private static Comparable[] aux;

	public static void sort(Comparable[] a) {
		int n = a.length;
		aux = new Comparable[n];
		sort(a, 0, n-1);

	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if(hi<=lo)return;
		int mid =lo+(hi-lo)/2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int n = a.length;
		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}
//		int i = lo, j = mid + 1;
//		for(int k=lo;k<=hi;k++){
//			if(i>mid) a[k]=aux[j++];
//			else if(j>hi)a[k]=aux[i++];
//			else if(aux[j].compareTo(aux[i])<0) a[k]=aux[j++];
//			else a[k]=aux[i++];
//		}
		
		int i = lo, j = mid + 1, k = lo;
		while (i <= mid && j <= hi) {
			if (aux[i].compareTo(aux[j]) < 0)
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
			k++;
		}
		while(i <= mid)a[k++]=aux[i++];
		while(j<=hi)a[k++]=aux[j++];
	}

}

class Quick{
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
	}
	public static void sort(Comparable[] a,int lo,int hi){
		if(hi<=lo)return;
		int j=partition(a, lo, hi);
		sort(a,lo,j-1);
		sort(a, j+1, hi);
	}
	public static int partition(Comparable[]a,int lo,int hi){
		Comparable v=a[lo];
		int i=lo,j=hi+1;
		while(true){
			while(a[++i].compareTo(v)<0)if(i==hi)break;
			while(a[--j].compareTo(v)>0);if(j==lo)break;
			if(i>=j)break;
			Selection.exch(a, i, j);
		}
		Selection.exch(a, lo, j);
		return j;
	}
}

public class Sort {
	public static Comparable[] genComparables(int n) {
		
		Random random = new Random(47);
		Comparable[] a = new Comparable[n];
		for (int i = 0; i < n; i++) {
			a[i] = random.nextInt(100);
		}
		return a;
	}

	public static void main(String[] argus) {
		Comparable[] b = genComparables(20);
		for (Comparable bi : b) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Selection.sort(b);
		for (Comparable bi : b) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Comparable[] b1 = genComparables(20);
		for (Comparable bi : b1) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Insertion.sort(b1);
		for (Comparable bi : b1) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Comparable[] b2 = genComparables(20);
		for (Comparable bi : b2) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Shell.sort(b2);
		for (Comparable bi : b2) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Comparable[] b3 = genComparables(20);
		for (Comparable bi : b3) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Merge.sort(b3);
		for (Comparable bi : b3) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Comparable[] b4 = genComparables(20);
		for (Comparable bi : b4) {
			System.out.print(bi + " ");
		}
		System.out.println();
		Quick.sort(b4);
		for (Comparable bi : b4) {
			System.out.print(bi + " ");
		}
	}

}
