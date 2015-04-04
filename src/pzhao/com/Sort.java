package pzhao.com;
import java.util.Random;
/*
 *选择排序
 *1.运行时间与输入无关
 *2.数据移动最少  N次交换。 
 * 
 */
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
/*
 * 插入排序
 * 对于部分有序的数组十分高效，也适合小规模数组
 * 需要交换次数与数组中倒置数量相等
 * 需要比较的次数>=倒置的数量，<=倒置数量+数组大小-1
 * 稳定
 */

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
/*
 * 希尔排序对中等大小的数组需要运行的时间可以接受的，并且不需要额外的空间
 * 
 */
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
/*
 * 归并排序是一种渐进最优的比较排序方法
 * 最坏情况下的比较次数为~NlgN
 * 需要额外空间
 * 稳定
 */
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

/*
 * 快速排序一般都比希尔排序和归并排序快，因为它内循环没有移动数据，比较次数少
 * 快速排序最好的情况是每次正好将数组对半分
 * 改进：三向切分快速排序
 */
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
