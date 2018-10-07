
public class Complexity {
	private int nPower;
	private int logPower;
	
	public Complexity(int nPower, int logPower){
		this.nPower = nPower;
		this.logPower = logPower;
	}
	
	public String toString() {
		if(nPower==1 && logPower==0){
			return("O(n)");
		}else if(nPower==0 && logPower==1) {
			return("O(log(n))");
		}else if(nPower==1 && logPower==1){
			return("O(n"+" * log(n))");
		}else if(nPower==0 && logPower==0){
			return("O(1)");
		}else {
			return("O(n^"+nPower+" * log(n)^"+logPower+")");
		}
	}

	public int getnPower() {
		return nPower;
	}
	public void setnPower(int nPower) {
		this.nPower = nPower;
	}

	public int getLogPower() {
		return logPower;
	}
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
}
