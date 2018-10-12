import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
/**
 * The <code>PythonTracer</code> class goes through
 * the file and determines the order of complexity
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class PythonTracer {
	public static final int SPACE_COUNT = 4; // indents in the code for every statement
	static ArrayList<CodeBlock> dude = new ArrayList<CodeBlock>(); // stack of CodeBlocks
	static String count = "1"; // name of the CodeBlocks
	
	/**
	 * Asks user for the file
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file name (or 'quit' to quit): ");
		String x = scan.readLine();
		if(x.equalsIgnoreCase("quit")) {
			System.exit(0);
		}else{
			System.out.println("Overall complexity of matrix_multiply: " + traceFile(x).toString());
		}
	}
	
	/**
	 * determines complexity of code given
	 * 
	 * @param filename
	 * 		the name of the file provided by the user
	 * @return
	 * 		the total order of complexity of the file
	 */
	public static Complexity traceFile(String filename) {
		CodeBlock oldTop = null;
		Complexity oldTopComplexity = null;
		Complexity highest = null;
		Complexity highestofthehigh = new Complexity(0,0);
		Boolean whatintarnation = true;
		Boolean firsttime = false;
		String top = "";
		try {
			File file = new File(filename);
			BufferedReader reader = null;
		    BufferedReader scan = new BufferedReader(new FileReader(file));
		    String z = "";
		    while ((z = scan.readLine()) != null) {
	        	String[] x = z.split(" ");
	        	int spaces = 0;
	        	if(!z.trim().equals("")) {
		        	while(z.substring(spaces,spaces+4).equals("    ")) {
			        	spaces+=4;
			        }
	        	}
		        if(!((x[spaces].equals("#"))||(z.trim().equals("")))) {
		        	int indents = spaces / SPACE_COUNT;
		        	while(indents<dude.size()) { 
		        		if(indents == 0) {
		        			return highestofthehigh;
		        		}else if(firsttime){
		        			oldTop = BlockStack.pop();
		        			System.out.println("Leaving block "+oldTop.getName()+" updating block "+BlockStack.peek().getName());
		        			oldTopComplexity = oldTop.getHighestSubComplexity();
		        			System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
			        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
			        				+"   highest sub-complexity = "+oldTopComplexity.toString());
		        			compare(oldTopComplexity,BlockStack.peek().getBlockComplexity());
		        			if(compare2(highestofthehigh,BlockStack.peek().getHighestSubComplexity())) {
		        				highestofthehigh = BlockStack.peek().getHighestSubComplexity();
		        			}
		        			System.out.println("\n");
		        		}else {
		        			oldTop = BlockStack.pop();
		        			System.out.println("Leaving block "+oldTop.getName()+" updating block "+BlockStack.peek().getName());
		        			oldTopComplexity = oldTop.getBlockComplexity();
		        			BlockStack.peek().setHighestSubComplexity(oldTopComplexity);
		        			System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
			        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
			        				+"   highest sub-complexity = "+BlockStack.peek().getHighestSubComplexity().toString());
		        			compare(oldTopComplexity,BlockStack.peek().getBlockComplexity());
		        			if(compare2(highestofthehigh,BlockStack.peek().getHighestSubComplexity())) {
		        				highestofthehigh = BlockStack.peek().getHighestSubComplexity();
		        			}
		        			System.out.println("\n");
		        		}
		        		whatintarnation = true;
		        		firsttime=true;
		        	}
		        	firsttime=false;
		        	String keyword = "yo";
		        	String huh = x[spaces]+" ";
		        	for(int i = 0; i<CodeBlock.BLOCK_TYPES.length; i++) {
		        		if(huh.equals(CodeBlock.BLOCK_TYPES[i])) {
		        			 keyword = huh;
		        		}
		        	}
		        	if(!keyword.equals("yo")) {
			        	if(keyword.equals("for ")) {
			        		for(int y = 0; y < x.length; y++) {
			        			if(x[y].equals("N:")){
			        				Complexity comp1 = new Complexity(1,0);
			        				CodeBlock block = new CodeBlock(comp1);
			        				BlockStack.push(block);
			        				dude.get(0).setName(count);
			        				count = count+".1";
			        				if(whatintarnation) {
			        					highest = BlockStack.peek().getBlockComplexity();
			        					whatintarnation = false;
			        				}
			        				BlockStack.peek().setHighestSubComplexity(highest);
			        				System.out.println("Entering block "+BlockStack.peek().getName()+" "+
					        				x[spaces]+":");
					        		System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
					        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
					        				+"   highest sub-complexity = "+BlockStack.peek().getHighestSubComplexity().toString());
					        		System.out.print("\n");
			        			}else if(x[y].equals("log_N:")) {
			        				Complexity comp2 = new Complexity(0,1);
			        				CodeBlock block = new CodeBlock(comp2);
			        				BlockStack.push(block);
			        				dude.get(0).setName(count);
			        				count = count+".1";
			        				if(whatintarnation) {
			        					highest = BlockStack.peek().getBlockComplexity();
			        					whatintarnation = false;
			        				}
			        				BlockStack.peek().setHighestSubComplexity(highest);
			        				System.out.println("Entering block "+BlockStack.peek().getName()+" "+
					        				x[spaces]+":");
					        		System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
					        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
					        				+"   highest sub-complexity = "+BlockStack.peek().getHighestSubComplexity().toString());
					        		System.out.print("\n");
			        			}
			        		}
			        		top = "for ";
			        	}else if(keyword.equals("while ")) {
			        		String loops = x[spaces+1];
			        		Complexity temps = new Complexity(0,0);
			        		CodeBlock block = new CodeBlock(temps);
			        		BlockStack.push(block);
			        		dude.get(0).setName(count);
	        				count = count+".1";
			        		BlockStack.peek().setLoopVariable(loops);
			        		top = "while";
			        		if(whatintarnation) {
	        					highest = BlockStack.peek().getBlockComplexity();
	        					whatintarnation = false;
			        		}
			        		BlockStack.peek().setHighestSubComplexity(highest);
			        		System.out.println("Entering block "+BlockStack.peek().getName()+" "+
			        				x[spaces]+":");
			        		System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
			        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
			        				+"   highest sub-complexity = "+BlockStack.peek().getHighestSubComplexity().toString());
			        		System.out.print("\n");
			        	} else {
			        		Complexity temps = new Complexity(0,0);
			        		CodeBlock block = new CodeBlock(temps);
			        		BlockStack.push(block);
			        		dude.get(0).setName(count);
			        		count = count + ".1";
			        		top = "something";
			        		if(whatintarnation) {
	        					highest = BlockStack.peek().getBlockComplexity();
	        					whatintarnation = false;
			        		}
			        		BlockStack.peek().setHighestSubComplexity(highest);
			        		System.out.println("Entering block "+BlockStack.peek().getName()+" "+
			        				x[spaces]+":");
			        		System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
			        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
			        				+"   highest sub-complexity = "+BlockStack.peek().getHighestSubComplexity().toString());
			        		System.out.print("\n");
			        	}
			        	
		        	}else {
			        	String keyword1 = "yo";
			        	for(int i = 0; i<x.length; i++) {
			        		if(x[i].equals(BlockStack.peek().getLoopVariable())) {
			        			 keyword1 = x[i];
			        		}
			        	}
			        	Complexity ah = null;
		        		if(top.equals("while")) {
		        			if(BlockStack.peek().getLoopVariable()!=null) {
			        			if(BlockStack.peek().getLoopVariable().equals(keyword1)) {
			        				for(int i = 0; i<x.length; i++) {
						        		if((x[i].equals("/="))||(x[i].equals("+="))) {
						        			 keyword1 = x[i];
						        		}
						        	}
			        				if(keyword1.equals("/=")) {
			        					ah = new Complexity(0,1);
			        				}else if(keyword1.equals("+=")) {
			        					ah = new Complexity(1,0);
			        				}
			        				BlockStack.peek().setBlockComplexity(ah);
			        				System.out.println("Found update statement, updating block "+BlockStack.peek().getName());
			        				System.out.println("BLOCK "+BlockStack.peek().getName()+":   "
					        				+"block complexity = "+BlockStack.peek().getBlockComplexity().toString()
					        				+"   highest sub-complexity = "+BlockStack.peek().getHighestSubComplexity().toString());
					        		System.out.print("\n");
			        				top = "nothing fool";
			        			}
		        			}
			        	}
		        	}
		        	
		        }
		    }
		    while(dude.size()>1) {
		    	oldTop = BlockStack.pop();
		    	oldTopComplexity = oldTop.getBlockComplexity();
		    	compare(oldTopComplexity, BlockStack.peek().getHighestSubComplexity());
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    return highestofthehigh;
	}
	
	/**
	 * compares the complexity and replaces highest complexity with the combination of both
	 * 
	 * @param x
	 * 		oldTopComplexity
	 * @param y
	 * 		the now TopComplexity
	 */
	public static void compare(Complexity x, Complexity y) {
		Complexity yeo = new Complexity(x.getnPower()+y.getnPower(),x.getLogPower()+y.getLogPower());
		BlockStack.peek().setHighestSubComplexity(yeo);
	}
	
	/**
	 * Compare if the highest complexity is lower or higher than the top complexity
	 * @param x
	 * 		highest of the high complexity
	 * @param y
	 * 		top complexity of the array list
	 * @return
	 * 		true or false if the top complexity of the array list is higher
	 * 		than the highest of the high complexity
	 */
	public static boolean compare2(Complexity x, Complexity y) {
			if(x.getnPower()<=y.getnPower()) {
				if(x.getLogPower()<=y.getLogPower()) {
					return true;
				}
			}
		return false;
	}
}