package pzhao.com;

import edu.princeton.cs.introcs.In;

public class ReadIn {
	String fileString;
	int[]  readList;
	private int i=0;
	public ReadIn(String filePath){
		this.fileString=filePath;
		readList=In.readInts(filePath);
	}
	 
	public int getInt(){
		return readList[i++];
	}
	public boolean isEmpty(){
		return i<readList.length;
	}
	
	public void reset(){
		i=0;
	}
}
