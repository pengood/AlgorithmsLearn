package pzhao.com;

import java.io.File;

import edu.princeton.cs.introcs.In;

class DepthFirstSearch {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public DepthFirstSearch(Graph graph, int s) {
		this.s = s;
		marked = new boolean[graph.V()];
		edgeTo = new int[graph.V()];
		dfs(graph, s);
	}

	private void dfs(Graph graph, int s) {
		marked[s] = true;
		for (int v : graph.adj(s)) {
			if (!marked[v]) {
				edgeTo[v] = s;
				dfs(graph, v);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> stack = new Stack<>();
		for (int i = v; i != s; i = edgeTo[i]) {
			stack.push(i);
		}
		stack.push(s);
		return stack;
	}

}

class BreadthFirstPath {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public BreadthFirstPath(Graph graph, int s) {
		marked = new boolean[graph.V()];
		edgeTo = new int[graph.V()];
		this.s = s;
		bfp(graph, s);
	}

	private void bfp(Graph graph, int s) {

		Queue<Integer> queue = new Queue<>();
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int w : graph.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
			}
		}

	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> stack = new Stack<>();
		for (int i = v; i != s; i = edgeTo[i]) {
			stack.push(i);
		}
		stack.push(s);
		return stack;
	}

}

class CC{
	boolean[] marked;
	private int[] id;
	private int count;
	
	public CC(Graph graph){
		marked=new boolean[graph.V()];
		id=new int[graph.V()];
		for(int i=0;i<graph.V();i++){
			if(!marked[i]){
				count++;
				dfs(graph, i);
			}
		}
	}
	
	private void dfs(Graph graph,int s){
		marked[s]=true;
		id[s]=count;
		for(int v:graph.adj(s)){
			if(!marked[v])
				dfs(graph, v);
		}
	}
	
	public boolean connected(int v,int w){
		return id[v]==id[w];
	}
	public int id(int v){
		return id[v];
	}
	
	public int count(){
		return count;
				
	}
	
}

public class Graph {

	private Bag<Integer>[] adj;
	private int V;
	private int E;

	@SuppressWarnings("unchecked")
	public Graph(int v) {
		if (v < 0)
			throw new IllegalArgumentException();
		this.V = v;
		adj = (Bag<Integer>[]) new Bag[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new Bag<>();
		}
	};

	/**
	 * @param in
	 */
	public Graph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int e = 0; e < E; e++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	int V() {
		return V;
	}

	int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public String toString() {
		String string = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			string += v + ": ";
			for (int w : this.adj(v)) {
				string += w + " ";
			}
			string += "\n";
		}
		return string;
	}

	public static void main(String[] args) {
		In in = new In(new File("tinyCG.txt"));
		Graph graph = new Graph(in);
		// System.out.print(graph);
		DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
		System.out.println(dfs.pathTo(4));
		BreadthFirstPath bfp=new BreadthFirstPath(graph, 0);
		System.out.println(bfp.pathTo(4));
		In in1 = new In(new File("tinyG.txt"));
		Graph graph1 = new Graph(in1);
		CC cc=new CC(graph1);
		System.out.print(cc.count());
		
	}
}
