package pzhao.com;


/**
 * 二分查找
 * @author zhaopeng
 *
 */
public class BinarySearch {
	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (hi >= lo) {
			int tmp = (lo + hi) / 2;
			if (key > a[tmp]) {
				lo = tmp + 1;
				// System.out.println("key > a[tmp] "+key+">"+a[tmp]);
			}

			else if (key < a[tmp]) {
				hi = tmp - 1;
				// System.out.println("key < a[tmp] "+key+"<"+a[tmp]);
			}

			else {
				// System.out.println("key = a[tmp] "+key+"="+a[tmp]+"return key");
				return key;
			}

		}
		return -1;
	}

//	public static void main(String[] argus) {
		// StringBuilder stringBuilder=new StringBuilder();
		// try {
		// BufferedReader inBufferedReader=new BufferedReader(new FileReader(new
		// File("tinyW.txt")));
		// try{
		// String string;
		// while((string=inBufferedReader.readLine())!=null){
		// stringBuilder.append(string+" ");
		// }
		// }finally{
		// inBufferedReader.close();
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// throw new RuntimeException();
		// }
		// System.out.println(stringBuilder.toString());
		// String[] a=stringBuilder.toString().split(" ");
		// int[] writeList=new int[a.length];
		// for(int i=0;i<a.length;i++){
		// writeList[i]=Integer.parseInt(a[i]);
		// }
		// for(int i=0;i<writeList.length;i++)
		// System.out.print(writeList[i]+" ");
		// System.out.println();
//		int[] b = In.readInts("tinyW.txt");
//		Arrays.sort(b);
//		for (int i = 0; i < b.length; i++)
//			System.out.print(b[i] + " ");
//		System.out.println("Length: " + b.length);
//		ReadIn in = new ReadIn("tinyT.txt");
//		while (in.isEmpty()) {
//			System.out.print(in.getInt() + " ");
//		}
//		System.out.println();
//		in.reset();
//		while (in.isEmpty()) {
//			int key = in.getInt();
//			// rank(key, b);
//			if (rank(key, b) < 0)
//				System.out.print(key + " ");
//		}

//	}

}
