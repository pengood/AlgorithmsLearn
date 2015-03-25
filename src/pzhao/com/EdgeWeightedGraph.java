package pzhao.com;

import edu.princeton.cs.introcs.In;

class Edge implements Comparable<Edge>{

	private int v,w;
	private double weight;
	
	public Edge(int v,int w,double weight){
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	
	@Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if(weight>that.weight) return 1;
		else if(weight<that.weight)return-1;
		else return 0;
	}
	
	public int erther(){
		return v;
	}
	
	public int other(int x){
		if(x==v)return w;
		if(x==w)return v;
		else throw new RuntimeException();
	}
	public double weight(){
		return weight;
	}
	
	public String toString(){
		return v+"-"+w+": "+weight;
	}
}
public class EdgeWeightedGraph {

	private int V,E;
	private Bag<Edge>[] adj;
	public EdgeWeightedGraph(int n){
		this.V=n;
		adj=(Bag<Edge>[])new Bag[n];
		for(int i=0;i<V;i++){
			adj[i]=new Bag<Edge>();
		}
	}
	public EdgeWeightedGraph(In in){
		this(in.readInt());
		int e=in.readInt();
		for(int i=0;i<e;i++){
			int v=in.readInt();
			int w=in.readInt();
			double weight=in.readDouble();
			addEdge(v, w, weight);
		}
	}
	
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	private void addEdge(int v,int w,double weight){
		Edge edge=new Edge(v, w, weight);
		adj[v].add(edge);
		adj[w].add(edge);
		E++;
	}
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	public Iterable<Edge> edges(){
		Bag<Edge> bag=new Bag<>();
		for(int i=0;i<V;i++){
			for(Edge e:adj[i]){
				if(e.other(i)>i)
					bag.add(e);
			}
		}
		return bag;
	}
	
}
