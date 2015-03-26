package pzhao.com;

import java.io.File;


import edu.princeton.cs.introcs.In;
class AcyclicSP{
	private DirectedEdge[] edgeTo;
	private double[] disTo;
	public AcyclicSP(EdgeWeightedDigraph G,int s){
		edgeTo=new DirectedEdge[G.v()];
		disTo=new double[G.v()];
		for(int i=0;i<G.v();i++){
			disTo[i]=Double.POSITIVE_INFINITY;
		}
		disTo[s]=0.0;
		//Topological topological=new Topological(G);
	}
	private void relax(DirectedEdge e){
		int v=e.from();
		int w=e.to();
		if(disTo[w]>disTo[v]+e.weight()){
			disTo[w]=disTo[v]+e.weight();
			edgeTo[w]=e;
		}
	}
}



class DirectedEdge{
	private int v,w;
	private double weight;
	public DirectedEdge(int v,int w,double weight){
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	public double weight(){
		return weight;
	}
	public int from(){
		return v;
	}
	public int to(){
		return w;
	}
	public String toString(){
		return String.format("%d->%d %.2f", v,w,weight);
	}
}
public class EdgeWeightedDigraph {

	private int V;
	private int E;
	private Bag<DirectedEdge>[] adj;
	public EdgeWeightedDigraph(int v){
		this.V=v;
		adj=(Bag<DirectedEdge>[])new Bag[v];
		for(int i=0;i<v;i++)
			adj[i]=new Bag<>();
	}
	public EdgeWeightedDigraph(In in){
		this(in.readInt());
		int e=in.readInt();
		for(int i=0;i<e;i++){
			int v=in.readInt();
			int w=in.readInt();
			double weight=in.readDouble();
			DirectedEdge edge=new DirectedEdge(v, w, weight);
			addEdge(edge);
		}
		
	}
	public int v(){
		return V;
	}
	private void addEdge(DirectedEdge e){
		int v=e.from();
		adj[v].add(e);
		E++;
	}
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> bags=new Bag<>();
		for(int i=0;i<V;i++)
			for(DirectedEdge e:adj[i])
				bags.add(e);
		return bags;
	}
	
	public static void main(String[] args){
		EdgeWeightedDigraph edgeG=new EdgeWeightedDigraph(new In(new File("tinyEWD.txt")));
		for(DirectedEdge e:edgeG.edges()){
			System.out.println(e);
		}
	}
	
}
