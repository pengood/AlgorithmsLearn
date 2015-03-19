package pzhao.com;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;

public class Learn1_1 {
	public static void main(String[] argus){
		System.out.println((0+15)/2);
		String string="";
		for(int i=100;i>0;i/=2){
			string=(i%2)+string;
		}
		System.out.println(string);
		System.out.println((char)('a'+1));
		
		
		double xlo=0.2;
		double ylo=0.5;
		double xhi=0.5; 
		double yhi=0.6;
		int T=10000;
		
		Interval1D xInterval1d=new Interval1D(xlo, xhi);
		Interval1D yInterval1d=new Interval1D(ylo, yhi);
		Interval2D boxInterval2d=new Interval2D(xInterval1d, yInterval1d);
		boxInterval2d.draw();
		
		
	}
}
