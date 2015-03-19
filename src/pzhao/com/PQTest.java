package pzhao.com;

import java.util.NoSuchElementException;
import java.util.Random;

class MinPQ<Key> {
	private Key[] Keys;
	private int N;

	public MinPQ(int n) {
		Keys = (Key[]) new Object[n + 1];
		N = 0;
	}

	public MinPQ() {
		this(1);
	}

	private void reSize(int n) {
		Key[] tmp = (Key[]) new Object[n];
		for (int i = 1; i <= N; i++) {
			tmp[i] = Keys[i];
		}
		Keys = tmp;
	}

	public void insert(Key x) {
		if (N == Keys.length - 1)
			reSize(Keys.length * 2);
		Keys[++N] = x;
		swim(N);
	}

	public Key delMin() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		exch(1, N);
		Key minKey = Keys[N--];
		sink(1);
		Keys[N + 1] = null;
		if ((N > 0) && (N < (Keys.length - 1) / 4))
			reSize(Keys.length / 2);
		return minKey;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	private void exch(int i, int j) {
		Key tmpKey = Keys[i];
		Keys[i] = Keys[j];
		Keys[j] = tmpKey;
	}

	private void swim(int k) {
		int k1 = k;
		Key tmp = Keys[k];
		while (k > 1 && less(k / 2, tmp)) {
			Keys[k] = Keys[k / 2];
			k = k / 2;
		}
		Keys[k] = tmp;

	}

	private void sink(int k) {
		Key tmp = Keys[k];
		int j;
		while (2 * k <= N) {
			j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (less(j, tmp))
				break;
			Keys[k] = Keys[j];
			k = j;
		}
		Keys[k] = tmp;
	}

	private boolean less(int i, Key j) {
		return ((Comparable) Keys[i]).compareTo(j) < 0;
	}

	private boolean less(int i, int j) {
		return ((Comparable) Keys[i]).compareTo(Keys[j]) < 0;
	}
}

public class PQTest {
	public static void main(String[] argus) {
		MinPQ<Integer> pq = new MinPQ<>();
		Random random = new Random();
		for (int i = 0; i < 18; i++) {
			pq.insert(random.nextInt(1000));
		}
		for (int i = 0; i < 18; i++) {
			System.out.print(pq.delMin() + " ");
		}
	}
}
