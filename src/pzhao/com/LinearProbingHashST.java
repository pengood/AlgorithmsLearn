package pzhao.com;

import java.util.Random;

public class LinearProbingHashST<Key, Value> {
	private int N;
	private int M;
	private Key[] keys;
	private Value[] values;

	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int m) {
		this.M = m;
		keys = (Key[]) new Object[m];
		values = (Value[]) new Object[m];
	}

	private void resize(int n) {
		LinearProbingHashST<Key, Value> tmpHashST = new LinearProbingHashST<>(n);
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				tmpHashST.put(keys[i], values[i]);
		keys = tmpHashST.keys;
		values = tmpHashST.values;
		M = tmpHashST.M;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void put(Key key, Value value) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) {
				values[i] = value;
				return;
			}
		keys[i] = key;
		values[i] = value;
		if (N == M / 2)
			resize(M * 2);
		N++;
	}

	public Value get(Key key) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (key.equals(keys[i]))
				return values[i];
		}
		return null;
	}

	public void delete(Key key) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (key.equals(keys[i])) {
				keys[i] = null;
				for (int j = (i + 1)%M; keys[j] != null; j = (j + 1) % M) {
					Key tmpKey=keys[j];
					Value tmpValue=values[j];
					keys[j]=null;
					values[j]=null;		
					N--;
					put(tmpKey, tmpValue);
				}
			}
		N--;
		if(N<M/8)resize(M/2);
		return;
	}

	public static void main(String[] argus){
		Random random=new Random();
		LinearProbingHashST<String, Integer> linearProbingHashST=new LinearProbingHashST<>(10);
		String[] intString=new String[10];
		for(int i=0;i<10;i++){
			intString[i]="a"+random.nextInt(100);
			linearProbingHashST.put(intString[i], i);
		}
		for(int i=0;i<10;i++)
		{
			linearProbingHashST.delete(intString[i]);
		}
		System.out.print(linearProbingHashST.get("a2"));
	}
}
