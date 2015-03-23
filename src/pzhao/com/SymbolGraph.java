package pzhao.com;

import edu.princeton.cs.introcs.In;

public class SymbolGraph {
	private SequentialSearchST<String, Integer> st;
	private String[] keys;
	private Graph graph;

	public SymbolGraph(String filename, String delimiter) {
		st=new SequentialSearchST();
	
		In in = new In(filename);
		while (!in.isEmpty()) {
			String[] a = in.readLine().split(delimiter);
			for (int i = 0; i < a.length; i++) {
				if (!st.contains(a[i])) {
					st.put(a[i], st.size());
				}
			}
		}
		keys=new String[st.size()];
		for (String a : st) {
			keys[st.get(a)] = a;
		}

		graph = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] b = in.readLine().split(delimiter);
			int v = st.get(b[0]);
			for (int i = 1; i < b.length; i++) {
				graph.addEdge(v, st.get(b[i]));
			}

		}
		
	}
	
	public boolean contains(String key){
		return st.contains(key);
	}
	public int index(String name){
		return st.get(name);
	}
	public String name(int v){
		return keys[v];
	}
	
	
	public static void main(String [] args){
		String filename="routes.txt";
		SymbolGraph symbolGraph=new SymbolGraph(filename, " ");
		Graph g=symbolGraph.graph;
		String[] aStrings=new String[g.V()];
		for(int v=0;v<g.V();v++){
			aStrings[v]=symbolGraph.name(v);
			System.out.print(aStrings[v]+" ");
		}
		System.out.println();
		for(int v=0;v<g.V();v++){
			int index=symbolGraph.index(aStrings[v]);
			System.out.print(symbolGraph.name(index)+": ");
			for(int w:g.adj(index))
				System.out.print(symbolGraph.name(w)+" ");
			System.out.println();
		}
		
		int s=symbolGraph.index(aStrings[0]);
		BreadthFirstPath bfp=new BreadthFirstPath(g, s);
		int t=symbolGraph.index(aStrings[9]);
		for(int i:bfp.pathTo(t))
			System.out.print(symbolGraph.name(i)+" ");
		
	}
	
}
