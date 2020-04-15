package pa06;

import java.util.*;

/**
 * A Sample represents a vector of doubles to be used in a clustering algorithm...
 * @author Yinghan Lin
 *
 */

public class Sample {
	ArrayList<Double> Sample;
	
	public Sample(double[] values) {
		for(int i=0; i<values.length; i++) {
			Sample.add(values[i]);
		}
	}
	
	public String toString(){ 
		String s = "";
		for(double d: Sample) {
			s = s+d+" ";
		}
		return "[ "+s+"]";
	}
	
	public double get(int i) {
		return Sample.get(i);
	}
	
	public int size() {
		return Sample.size();
	}
	
	public static double Distance(Sample s1, Sample s2) {
		double sum = 0;
		for(int i=0; i<s1.size(); i++) {
			sum = sum + Math.pow((s1.get(i)-s2.get(i)),2);   
		}
		return Math.sqrt(sum); 
	}
	
    public static void main(String[] args) {
    	System.out.println("Testing for the sample class.");
    	double[] p1 = {1d, 2d, 3.14, 2.71};
    	double[] p2 = {0.1, 1.1, 2.1, 3.1};
    	Sample s1 = new Sample(p1);
    	Sample s2 = new Sample(p2); 
    	System.out.println("s1="+s1.toString()); 
    	System.out.println("s2="+s2.toString());
    	System.out.println("The distance between two samples is "+Distance(s1,s2));
    	
    }
}
