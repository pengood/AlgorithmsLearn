package pzhao.com;

import java.util.LinkedList;

class Stack<T>{
	private LinkedList<T> linkedList=new LinkedList<>();
	public void push(T t){
		linkedList.addFirst(t);
	}
	public T peek(){
		return  linkedList.getFirst();
	}
	public T pop(){
		return linkedList.removeFirst();
	}
	public boolean empty(){
		return linkedList.isEmpty();
	}
}

class MyQueue<T>{
	private T[] aTs=(T[])new Object[1];
	private int N=0,head=0,tail=0;
	
}

public class Evaluate {

}
