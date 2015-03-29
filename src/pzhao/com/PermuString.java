package pzhao.com;

public class PermuString {
	private static void swap(StringBuilder s,int p,int q){
		char tmp=s.charAt(p);
		s.setCharAt(p, s.charAt(q));
		s.setCharAt(q, tmp);
	}
	
	public static void printStr(StringBuilder s,int start,int end){
		if(start==end-1)
			System.out.println(s);
		else{
			for(int i=start;i<=end-1;i++){
				swap(s, start, i);
				printStr(s, start+1, end);
				swap(s, i, start);
			}
		}
	}
	
	public static void main(String[] args){
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("abc");
		printStr(sBuilder, 0, sBuilder.length());
	}

}
