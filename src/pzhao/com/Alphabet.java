package pzhao.com;

public class Alphabet {

	private char[] chars;
	private int[] indexs;
	private int R;
	
	public Alphabet(String s){
		R=s.length();
		chars=new char[s.length()];
		indexs=new int[Character.MAX_VALUE];
		for(int i=0;i<Character.MAX_VALUE;i++){
			indexs[i]=-1;
		}
		for(int i=0;i<s.length();i++){
			chars[i]=s.charAt(i);
			indexs[chars[i]]=i+1;
		}
		
	}
	
	public char toChar(int index){
		return chars[index];
	}
	
	public int toIndex(char c){
		return indexs[c]-1;
	}
	
	public boolean contains(char c){
		return indexs[c]!=-1;
	}
	
	public int R(){
		return R;
	}
	public int lgR() {
        int lgR = 0;
        for (int t = R-1; t >= 1; t /= 2)
            lgR++;
        return lgR;
    }
	public int[] toIndices(String s){
		char[] source=s.toCharArray();
		int [] source1=new int[source.length];
		for(int i=0;i<source.length;i++){
			source1[i]=indexs[source[i]];
		}
		return source1;
	}
	public String toChars(int [] index){
		StringBuilder s=new StringBuilder();
		for(int i=0;i<index.length;i++){
			s.append(chars[index[i]]);
		}
		return s.toString();
	}
	
	public static void main(String[] args){
		String string="ABCDR";
		Alphabet alphabet=new Alphabet(string);
		System.out.println("R="+alphabet.R());
		System.out.println("lgR="+alphabet.lgR());
		for(int i=0;i<5;i++){
			System.out.print(alphabet.toChar(i)+" ");
		}
		System.out.println();
		for(int i=0;i<5;i++)
			System.out.print(alphabet.toIndex(string.charAt(i))+" ");
		System.out.println();
		int [] b=alphabet.toIndices("ACD");
		System.out.print("ACD to int[]: ");
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+" ");
		System.out.println();
		int[] a={1,2,3,0,2,1};
		System.out.print("int[] a to chars: "+alphabet.toChars(a));
	}
	
}
