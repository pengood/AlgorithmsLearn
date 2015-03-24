package pzhao.com;

import edu.princeton.cs.introcs.In;

public class KosarajuSCC {

	private boolean[] marked;
	private int [] id;
	private int count;
	
	public KosarajuSCC(Digraph digraph){
		marked=new boolean[digraph.V()];
		id=new int[digraph.V()];
		DepthFirstOrder depthFirstOrder=new DepthFirstOrder(digraph.reserve());
		for(int s:depthFirstOrder.reversePost()){
			if(!marked[s])
				{
				dfs(digraph, s);
				count++;
				}
		}
		
	}
	
	private void dfs(Digraph digraph,int v){
		marked[v]=true;
		id[v]=count;
		for(int w:digraph.adj(v)){
			if(!marked[w])
				dfs(digraph, w);
		}
	}
	
	public boolean stronglyConnected(int v,int w){
		return id[v]==id[w];
	}
	public int count(){
		return count;
	}
	public int id(int v){
		return id[v];
	}
	
	public static void main(String [] args){
		Digraph digraph=new Digraph(new In("tinyDG.txt"));
		KosarajuSCC kosarajuSCC=new KosarajuSCC(digraph);
		int count=kosarajuSCC.count();
		Bag<Integer>[] bags=(Bag<Integer>[])new Bag[count];
		for(int i=0;i<digraph.V();i++){
			int v=kosarajuSCC.id(i);
			for(int w=0;w<count;w++)
				{
				
				if(v==w){
					if(bags[w]==null)
					bags[w]=new Bag<>();
					bags[w].add(i);
					break;
				}
				}
				
		}
		for(int i=0;i<count;i++)
			{
			for(int w:bags[i])
				System.out.print(w+" ");
			System.out.println();
			}
		System.out.println();	
	}
}
