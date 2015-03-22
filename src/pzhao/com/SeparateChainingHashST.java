package pzhao.com;


public class SeparateChainingHashST<Key, Value> {
	private SequentialSearchST<Key, Value>[] sts;
	private int N;
	private int M;

	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int m) {
		this.sts = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for(int i=0;i<m;i++){
			sts[i]=new SequentialSearchST<>();
		}
		this.M = m;
	}


	
	public boolean contains(Key key) {
		int i = hash(key);
		return sts[i].get(key) != null;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void put(Key key, Value value) {
		int i = hash(key);
		if (!contains(key))
			N++;
		sts[i].put(key, value);
	}

	public Value get(Key key) {
		int i = hash(key);
		return sts[i].get(key);
	}
	
	public void delete(Key key){
		if(!contains(key))return;
		int i=hash(key);
		sts[i].delete(key);
		N--;
	}
	
	public static void main(String[] argus){
		SeparateChainingHashST<String, Integer> separateChainingHashST=new SeparateChainingHashST<>(5);
		for(int i=0;i<10;i++){
			separateChainingHashST.put("a"+i, i);
		}
		separateChainingHashST.delete("a1");
		System.out.println();
		for(int i=0;i<10;i++){
			System.out.print(separateChainingHashST.get("a"+i)+" ");
		}
	}
}
