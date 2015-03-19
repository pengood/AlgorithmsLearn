package pzhao.com;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

class SequentialSearchST<Key,Value>implements Iterable<Key>{
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key key,Value value,Node next){
			this.key=key;
			this.value=value;
			this.next=next;
		}
	}
	private Node first;
	private int N;
	public void put(Key key,Value value){
		for(Node xNode=first;xNode!=null;xNode=xNode.next){
			if(key.equals(xNode.key)) {xNode.value=value; return;}
		}
		first=new Node(key, value, first);
		N++;
	}
	
	public Value get(Key key){
		for(Node xNode=first;xNode!=null;xNode=xNode.next){
			if(key.equals(xNode.key))return xNode.value;
		}
		return null;
	}
	
	public boolean contains(Key key){
		return get(key)!=null;
	}

	@Override
	public Iterator<Key> iterator() {
		
		// TODO Auto-generated method stub
		return new Iterator<Key>() {
			Node first1=first;
			Node tmpNode;
			@Override 
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return first1!=null;
			}

			@Override
			public Key next() {
				// TODO Auto-generated method stub
				tmpNode=first1;
				first1=first1.next;
				return tmpNode.key;
			}
		};
	}
	
	
	
	
}


public class SymbolTable {
	public static void main(String [] argus)throws IOException{
		int MinLen=0;
		SequentialSearchST<String, Integer> st=new SequentialSearchST<>();
		Scanner scanner=new Scanner(new File("tinyTale.txt"));
		while(scanner.hasNext()){
			String word=scanner.next();
			if(word.length()<MinLen)continue;
			if(!st.contains(word))st.put(word, 1);
			else {
				st.put(word, st.get(word)+1);
			}
		}
		String max="";
		st.put(max, 0);
		Iterator<String> iterator=st.iterator();
		while(iterator.hasNext()){
			String wordString=iterator.next();
			int i=st.get(wordString);
			if(i>st.get(max)){ 
				max=wordString;
				st.put(max, i);
			}
		}
		
		System.out.print(max+" "+st.get(max));

		
	}
}
