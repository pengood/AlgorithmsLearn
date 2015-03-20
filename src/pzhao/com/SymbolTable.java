package pzhao.com;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

class SequentialSearchST<Key, Value> implements Iterable<Key> {
	private class Node {
	private	Key key;
		Value value;
		Node next;

		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	private Node first;
	private int N;

	public void put(Key key, Value value) {
		for (Node xNode = first; xNode != null; xNode = xNode.next) {
			if (key.equals(xNode.key)) {
				xNode.value = value;
				return;
			}
		}
		first = new Node(key, value, first);
		N++;
	}

	public Value get(Key key) {
		for (Node xNode = first; xNode != null; xNode = xNode.next) {
			if (key.equals(xNode.key))
				return xNode.value;
		}
		return null;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	@Override
	public Iterator<Key> iterator() {

		// TODO Auto-generated method stub
		return new Iterator<Key>() {
			Node first1 = first;
			Node tmpNode;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return first1 != null;
			}

			@Override
			public Key next() {
				// TODO Auto-generated method stub
				tmpNode = first1;
				first1 = first1.next;
				return tmpNode.key;
			}
		};
	}

}

class BinarySearchST<Key extends Comparable<Key>, Value> implements
		Iterable<Key> {
	private Key[] keys;
	private Value[] values;
	private int N;

	public BinarySearchST() {
		this(1);
	}

	public BinarySearchST(int n) {
		keys = (Key[]) new Comparable[n];
		values = (Value[]) new Object[n];
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private void reSize(int n) {
		Key[] tmpKeys = (Key[]) new Comparable[n];
		Value[] tmpValues = (Value[]) new Object[n];
		for (int i = 0; i < N; i++) {
			tmpKeys[i] = keys[i];
			tmpValues[i] = values[i];
		}
		keys = tmpKeys;
		values = tmpValues;
	}

	public void put(Key key, Value value) {
		if (value == null) {
			delete(key);
			return;
		}

		int i = rank(key);
		if (i < N && key.equals(keys[i])) {
			values[i] = value;
			return;
		}
		if (N == keys.length - 1)
			reSize(keys.length * 2);
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].equals(key))
			return values[i];
		return null;
	}

	public void delete(Key key) {
		if (isEmpty())
			return;
		int i = rank(key);
		if (!keys[i].equals(key))
			return;
		for (int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			values[j] = values[j + 1];
		}
		N--;
		keys[N] = null;
		values[N] = null;
		if (N > 0 && N < keys.length / 4)
			reSize(keys.length / 2);

	}

	public boolean contains(Key key) {
		if (isEmpty())
			return false;
		int i = rank(key);
		if (i < N && keys[i].equals(key))
			return true;
		return false;
	}

	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key.compareTo(keys[mid]) < 0)
				hi = mid - 1;
			else if (key.compareTo(keys[mid]) > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public Key min() {
		if (isEmpty())
			return null;
		return keys[0];
	}

	public Key max() {
		if (isEmpty())
			return null;
		return keys[N - 1];
	}

	public Key select(int k) {
		if (k < 0 || k >= N)
			return null;
		return keys[k];
	}

	public Key ceilling(Key key) {
		int i = rank(key);
		if (i == N)
			return null;
		return keys[i];
	}

	public Key floor(Key key) {
		int i = rank(key);
		if (i < N && key.equals(keys[i]))
			return keys[i - 1];
		if (i == 0)
			return null;
		return keys[i];
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Key>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return i < N;
			}

			@Override
			public Key next() {
				// TODO Auto-generated method stub
				return keys[i++];
			}
		};
	}
}

public class SymbolTable {
	@SuppressWarnings("resource")
	public static void main(String[] argus) throws IOException {
		// int MinLen = 0;
		// SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
		Scanner scanner = new Scanner(new File("tinyTale.txt"));
		// while (scanner.hasNext()) {
		// String word = scanner.next();
		// if (word.length() < MinLen)
		// continue;
		// if (!st.contains(word))
		// st.put(word, 1);
		// else {
		// st.put(word, st.get(word) + 1);
		// }
		// }
		// String max = "";
		// st.put(max, 0);
		// Iterator<String> iterator = st.iterator();
		// while (iterator.hasNext()) {
		// String wordString = iterator.next();
		// int i = st.get(wordString);
		// if (i > st.get(max)) {
		// max = wordString;
		// st.put(max, i);
		// }
		// }
		//
		// System.out.print(max + " " + st.get(max));
		// System.out.println();
		BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>();
		while (scanner.hasNext()) {
			String word = scanner.next();
			if (binarySearchST.contains(word))
				binarySearchST.put(word, binarySearchST.get(word) + 1);
			else {
				binarySearchST.put(word, 1);
			}
			
		}
		
		String max="";
		binarySearchST.put(max, 0);
		Iterator<String> iterator=binarySearchST.iterator();
		while(iterator.hasNext()){
			String wordString = iterator.next();
			if(binarySearchST.get(wordString)>binarySearchST.get(max))
				max=wordString;
		}
		System.out.print("max: "+max+": "+binarySearchST.get(max));
	}
}
