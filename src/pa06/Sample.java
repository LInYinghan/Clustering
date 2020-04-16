package pa06;

import java.util.*;

/**
 * A Sample represents a vector of doubles to be used in a clustering algorithm...
 * @author Yinghan Lin
 *
 */

public class Sample {
	ArrayList<Double> Sample;
	int ClusterId;
	
	public Sample(double[] values) {
		this.Sample = new ArrayList<Double>();
		for(int i=0; i<values.length; i++) {
			Sample.add(values[i]);
		}
		this.ClusterId = -1;
	}
	
	public String toString(){ 
		String s = "";
		for(double d: Sample) {
			s = s+d+" ";
		}
		return "ClusterId: "+ClusterId+"[ "+s+"]";
	}
	
	public double get(int i) {
		return Sample.get(i);
	}
	
	public void setClusterId(int i) {
		this.ClusterId = i;
	}
	
	public int size() {
		return Sample.size();
	}
	
/*	
    public static void main(String[] args) {
    	System.out.println("Testing for the sample class.");
    	double[] p1 = {1d, 2d, 3.14, 2.71};
    	double[] p2 = {0.1, 1.1, 2.1, 3.1};
    	Sample s1 = new Sample(p1);
    	Sample s2 = new Sample(p2); 
    	System.out.println("s1="+s1.toString()); 
    	System.out.println("s2="+s2.toString());
    	
    }
*/
	
}

