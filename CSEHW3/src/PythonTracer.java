import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PythonTracer {
	public static final int SPACE_COUNT = 4;
	static ArrayList<CodeBlock> dude = new ArrayList<CodeBlock>();
	static String count = "1";
	
	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file name (or 'quit' to quit): ");
		String x = scan.readLine();
		if(x.equalsIgnoreCase("quit")) {
			System.exit(0);
		}else {
			traceFile(x);
		}
	}
	
	public static Complexity traceFile(String filename) {
		CodeBlock oldTop = null;
		Complexity oldTopComplexity = null;
		Complexity highestComplexity = null;
		try {
			File file = new File(filename);
			BufferedReader reader = null;
		    BufferedReader scan = new BufferedReader(new FileReader(file));
		    String z = null;
		    while ((z = scan.readLine()) != null) {
		        System.out.print(z+"\n");
		        if(!(z.substring(0,1).equals("#"))||(z.length()==0)) {
		        	int spaces = 0;
		        	while(z.substring(0,4).equals("    ")) {
			        	spaces+=4;
			        }
		        	int indents = spaces / SPACE_COUNT;
		        	while(indents<dude.size()) { //while indents is less than size of stack
		        		if(indents == 0) {
		        			return BlockStack.peek().getBlockComplexity();
		        		}else {
		        			oldTop = BlockStack.pop();
		        			oldTopComplexity = BlockStack.peek().getBlockComplexity();
		        			compare(oldTopComplexity,BlockStack.peek().getHighestSubComplexity());
		        		}
		        	}
		        	String keyword = "";
		        	for(int i = 0; i<dude.size(); i++) {
		        		if(z.equals(CodeBlock.BLOCK_TYPES[i])) {
		        			 keyword = z;
		        		}
		        	}//if line contains a keyword
		        	if(keyword.equals("for ")) {
		        		String[] x = keyword.split(" ");
		        		for(int y = 0; y < x.length; y++) {
		        			if(x[y].equals("N:")){//if keyword is "for"
		        				CodeBlock block = new CodeBlock(count);
		        				Complexity comp1 = new Complexity(1,0);
		        				BlockStack.peek().setBlockComplexity(comp1);
		        			}else if(x[y].equals("log_N:")) {
		        				CodeBlock block = new CodeBlock(count);
		        				Complexity comp2 = new Complexity(0,1);
		        				BlockStack.peek().setBlockComplexity(comp2);
		        			}
		        		}
		        	}else if(keyword.equals("while")) {//else if keyword is "while"
		        		BlockStack.peek().setLoopVariable(loopVariable);
		        	} else {//Create new O(1) CodeBlock and push onto the stack.
		        		CodeBlock block = new CodeBlock();
		        		BlockStack.push(block);
		        	}
		        }else {
		        	if(BlockStack.peek().getLoopVariable().equals("while")) {
		        		
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
	}
	
	public static void compare(Complexity x, Complexity y) {
		if(x.getnPower()>y.getnPower()) {
			if(x.getLogPower()<y.getLogPower()) {
				BlockStack.peek().getHighestSubComplexity().setnPower(x.getnPower());
			}
		}
		if(x.getLogPower()>y.getLogPower()) {
			if(x.getnPower()<y.getnPower()) {
				BlockStack.peek().getHighestSubComplexity().setLogPower(x.getLogPower());
			}
		}
		if(x.getLogPower()>y.getLogPower()) {
			if(x.getnPower()>y.getnPower()) {
				BlockStack.peek().setHighestSubComplexity(x);
			}
		}
	}
}
