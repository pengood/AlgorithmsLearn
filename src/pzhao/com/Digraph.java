package pzhao.com;

import edu.princeton.cs.introcs.In;


class DirectedDFS{
	private boolean[] marked;
	private int count;
	public DirectedDFS(Digraph digraph,int s){
		marked=new boolean[digraph.V()];
		dfs(digraph, s);
	}
	
	public DirectedDFS(Digraph digraph,Iterable<Integer> bags){
		marked=new boolean[digraph.V()];
		for(int i:bags){
			if(!marked[i])
			dfs(digraph, i);
		}
	}
	
	private void dfs(Digraph digraph,int v){
		marked[v]=true;
		count++;
		for(int w:digraph.adj(v)){
			if(!marked[w]){
				dfs(digraph, w);
			}
		}
	}
	
	public boolean reachable(int v){
		return marked[v];
	}
	public int count(){
		return count;
	}
	
}

class DirectedCycle{
	private boolean[] marked;
	private boolean[] onSatck;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	
	public DirectedCycle(Digraph digraph){
		marked=new boolean[digraph.V()];
		onSatck=new boolean[digraph.V()];
		edgeTo=new int[digraph.V()];
		for(int i=0;i<digraph.V();i++){
			for(int s:digraph.adj(i)){
				if(!marked[s])
					dfs(digraph, s);
			}
		}
	}
	
	private void dfs(Digraph digraph,int s){
		marked[s]=true;
		onSatck[s]=true;
		for(int v:digraph.adj(s)){
			if(cycle!=null)return;
			else if(!marked[v]){
				edgeTo[v]=s;
				dfs(digraph, v);
			}
			else if(onSatck[v]){
				cycle=new Stack<>();
				for(int i=s;i!=v;i=edgeTo[i]){
					cycle.push(i);
				}
				cycle.push(v);
				cycle.push(s);
			}
		}
		onSatck[s]=false;
	}
	public boolean hasCycle(){
		return cycle!=null;
	}
	public Iterable<Integer> cycle(){
		return cycle;
	}
}

public class Digraph {
	private Bag<Integer>[] adj;
	private int V;
	private int E;
	
	public Digraph(int v){
		if(v<0) throw new IllegalArgumentException();
		this.V=v;
		adj=(Bag<Integer>[])new Bag[v];
		for(int i=0;i<V;i++){
			adj[i]=new Bag<>();
		}
	}
	public Digraph(In in){
		this(in.readInt());
		int E=in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			addEdge(v, w);
		}
	}

	public void addEdge(int v,int w){
		adj[v].add(w);
		E++;
	}
	
	public int E(){
		return E;
	}
	public int V(){
		return V;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public Digraph reserve(){
		Digraph digraph=new Digraph(V);
		for(int i=0;i<V;i++)
		for(int w:adj[i]){
			digraph.addEdge(w, i);
		}
		return digraph;
	}
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(V + " vertices, " + E + " edges\n");
		for(int i=0;i<V;i++){
			sb.append(i+": ");
			for(int v:adj[i]){
				sb.append(v+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		In in=new In("tinyDG.txt");
		Digraph digraph=new Digraph(in);
		Digraph digraph2=digraph.reserve();
		System.out.println(digraph2);
		DirectedDFS dfs=new DirectedDFS(digraph, 2);
		for(int i=0;i<digraph.V();i++){
			if(dfs.reachable(i))
			System.out.print(i+" ");
		}
		System.out.println();
		DirectedCycle directedCycle=new DirectedCycle(digraph);
		if(directedCycle.hasCycle()){
			System.out.print(directedCycle.cycle());
		}
	}
	
}
