package pzhao.com; 
import java.util.Iterator;

/**
 * Bag 是一种不支持删除元素的集合数据类型
 * 
 * @author zhaopeng
 *
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {
	
	private class Node{
		Item item;
		Node next;
		
		public Node(Item item){
			this.item=item;
		}
	}
		private  Node first;
		private int N;
		public int size(){
			return N;
		}
		public boolean isEmpty(){
			return N==0;
		}
		public void add(Item item){
			Node tmpNode=first;
			first=new Node(item);
			first.next=tmpNode;
			N++;
		}
		

	@Override
	public Iterator<Item> iterator() {
			
		// TODO Auto-generated method stub
		return new Iterator<Item>() {
			Node tmp=first;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
			
				return tmp!=null;
			}

			@Override
			public Item next() {
				// TODO Auto-generated method stub
				Item item=tmp.item;
				tmp=tmp.next;
				return item;
			}
		};
	}
	public static void main(String[] args){
		Bag<String> bag=new Bag<>();
		for(int i=0;i<10;i++){
			bag.add("a"+i);
		}
		System.out.println(bag.size());
		Iterator<String> iterator=bag.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+" ");
		}
	}

}
