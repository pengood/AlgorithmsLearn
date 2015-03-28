package pzhao.com;

public class TrieST<Value> {
	private  static int R=256;
	private Node root;

	private static class Node{
		private Object Val;
		private Node[] next=new Node[R];
	}
	public Value get(String key){
		Node xNode=get(root, key, 0);
		if(xNode==null)return null;
		else return (Value)xNode.Val;
	}
	private Node get(Node x,String key,int d){
		if(x==null) return null;
		if(d==key.length()) return x;
		char c=key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	
	public void put(String key,Value val){
		root=put(root, key,val,0);
	}
	private Node put(Node x,String key,Value value,int d){
		if(x==null) x=new Node();
		if(d==key.length()){
			x.Val=value;return x;
		}
		char c=key.charAt(d);
		x.next[c]=put(x.next[c], key, value, d+1);
		return x;
	}
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	
	//找出所有以pre为前缀的键
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String>queue=new Queue<>();
		collect(get(root, pre, 0), pre, queue); //找出以pre为前缀的子树
		return queue;
	}
	
	private void collect(Node x,String pre,Queue<String> q){
		if(x==null)return;
		if(x.Val!=null)q.enqueue(pre);
		for(char c=0;c<R;c++){
			collect(x.next[c], pre+c, q);
		}
	}
	
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> queue=new Queue<>();
		collect(root, "", pat, queue);
		return queue;
	}
	
	private void collect(Node x,String pre,String pat,Queue<String>q){
		int d=pre.length();
		if(x==null) return;
		if(d==pat.length()&&x.Val!=null)q.enqueue(pre);
		if(d==pat.length())return;
		char next=pat.charAt(d);
		for(char c=0;c<R;c++){
			if(next=='.'||next==c){
				collect(x.next[c], pre+c, pat, q);
			}
		}
				
	}
	
	//最长前缀
	public String longestPrefixOf(String s){
		int length=search(root, s, 0,0);
		return s.substring(0, length);
	}
	
	private int search(Node x,String s,int d,int lenght){
		if(x==null) return lenght;
		if(x.Val!=null) lenght=d;
		if(d==s.length()) return lenght;
		char c=s.charAt(d);
		return search(x.next[c], s, d+1, lenght);
	}
	
	
	public static void main(String[] args){
		String[] strings={"she","sells","sea","shells","by","the","sea","shore"};
	//	int[] a={0,1,2,3,4,5,6,7};
		TrieST<Integer> trieST=new TrieST<>();
		for(int i=0;i<strings.length;i++){
			trieST.put(strings[i], i);
		}
		System.out.println(trieST.longestPrefixOf("shell"));
		for(String s:trieST.keys()){
			System.out.print(s+" ");
		}
		System.out.println();
		for(String s:trieST.keysWithPrefix("sh")){
			System.out.print(s+" ");
		}
		System.out.println();
		for(String s:trieST.keysThatMatch("sh.")){
			System.out.print(s+" ");
		}
	}
	
}
