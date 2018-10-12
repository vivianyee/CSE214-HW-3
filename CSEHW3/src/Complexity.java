/**
 * The <code>Complexity</code> class determines and prints
 * the order with the n and log power
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class Complexity {
	private int nPower; // n power in the order of complexity
	private int logPower; // log power in the order of complexity
	
	/**
	 * Constructor for the Complexity class
	 * 
	 * @param nPower
	 * 		n power in order of complexity
	 * @param logPower
	 * 		log power in order of complexity
	 */
	public Complexity(int nPower, int logPower){
		this.nPower = nPower;
		this.logPower = logPower;
	}
	
	/**
	 * returns the string in which the order of complexity would be 
	 * printed as
	 */
	public String toString() {
		if(nPower==1 && logPower==0){
			return("O(n)");
		}else if(nPower==0 && logPower==1) {
			return("O(log(n))");
		}else if(nPower==1 && logPower==1){
			return("O(n"+" * log(n))");
		}else if(nPower==0 && logPower==0){
			return("O(1)");
		}else if(logPower == 0){
			return("O(n^"+nPower+")");
		}else if(nPower == 0) {
			return("O(log(n)^"+logPower+")");
		}else {
			return("O(n^"+nPower+" * log(n)^"+logPower+")");
		}
	}

	/**
	 * Getter for nPower
	 * @return
	 * 		n Power of complexity
	 */
	public int getnPower() {
		return nPower;
	}
	/**
	 * Setter for nPower
	 * @param nPower
	 * 		n Power of complexity
	 */
	public void setnPower(int nPower) {
		this.nPower = nPower;
	}

	/**
	 * Getter for logPower
	 * @return
	 * 		log Power of complexity
	 */
	public int getLogPower() {
		return logPower;
	}
	/**
	 * Setter for logPower
	 * @param logPower
	 * 		log Power of complexity
	 */
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
}
