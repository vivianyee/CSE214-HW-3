import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class PythonTracer {
	public static final int SPACE_COUNT = 4;
	static ArrayList<CodeBlock> dude = new ArrayList<CodeBlock>();
	static String count = "1";
	static boolean isithigher = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file name (or 'quit' to quit): ");
		String x = scan.readLine();
		if(x.equalsIgnoreCase("quit")) {
			System.exit(0);
		}else {
			System.out.println("Overall complexity of matrix_multiply: " + traceFile(x).toString());
		}
	}
	
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
	        	
	        	System.out.println(x[spaces]);
	        	// FINDS THE FIRST DANG THING - WORKS!
		        if(!((x[spaces].equals("#"))||(z.trim().equals("")))) {
		        	int indents = spaces / SPACE_COUNT;
		        	while(indents<dude.size()) { //while indents is less than size of stack
		        		if(indents == 0) {
		        			return highestofthehigh;
		        		}else if(firsttime){ // what happens in other times
		        			oldTop = BlockStack.pop();
		        			System.out.println(dude.size());
		        			System.out.println(BlockStack.peek().getName());
		        			System.out.println(oldTop.getName());
		        			oldTopComplexity = oldTop.getHighestSubComplexity();
		        			System.out.println("nani");
		        			System.out.println(oldTopComplexity.toString()+"?");
		        			System.out.println(BlockStack.peek().getBlockComplexity().toString());
		        			compare(oldTopComplexity,BlockStack.peek().getBlockComplexity());
		        			if(nani(highestofthehigh,BlockStack.peek().getHighestSubComplexity())) {
		        				highestofthehigh = BlockStack.peek().getHighestSubComplexity();
		        			}
		        			System.out.println(BlockStack.peek().getHighestSubComplexity().toString());
		        		}else { // what happens in the first time
		        			oldTop = BlockStack.pop();
		        			System.out.println(dude.size());
		        			System.out.println(BlockStack.peek().getName());
		        			System.out.println(oldTop.getName());
		        			oldTopComplexity = oldTop.getBlockComplexity();
		        			System.out.println("nani 1");
		        			System.out.println(oldTopComplexity.toString()+"?");
		        			System.out.println(BlockStack.peek().getBlockComplexity().toString());
		        			BlockStack.peek().setHighestSubComplexity(oldTopComplexity);
		        			System.out.println(BlockStack.peek().getHighestSubComplexity().toString());
		        			compare(oldTopComplexity,BlockStack.peek().getBlockComplexity());
		        			if(nani(highestofthehigh,BlockStack.peek().getHighestSubComplexity())) {
		        				highestofthehigh = BlockStack.peek().getHighestSubComplexity();
		        			}
		        			System.out.println(BlockStack.peek().getHighestSubComplexity().toString());
		        		}
		        		whatintarnation = true;
		        		firsttime=true;
		        	}
		        	firsttime=false;
		        	// MAKES KEYWORD THE KEYWORD IN BLOCK_TYPES - WORKS!
		        	String keyword = "yo";
		        	String huh = x[spaces]+" ";
		        	for(int i = 0; i<CodeBlock.BLOCK_TYPES.length; i++) {
		        		if(huh.equals(CodeBlock.BLOCK_TYPES[i])) {
		        			 keyword = huh;
		        		}
		        	}
		        	System.out.println(keyword+"keyword");
		        	
		        	// IF KEYWORD DOES NOT EQUAL NOTHING - WORKS!
		        	if(!keyword.equals("yo")) {
		        		
		        		// IF KEYWORD IS FOR - WORKS!
			        	if(keyword.equals("for ")) {
			        		System.out.println(" inside for");
			        		for(int y = 0; y < x.length; y++) {
			        			if(x[y].equals("N:")){//if keyword is "for"
			        				Complexity comp1 = new Complexity(1,0);
			        				CodeBlock block = new CodeBlock(comp1);
			        				BlockStack.push(block);
			        				if(whatintarnation) {
			        					highest = BlockStack.peek().getBlockComplexity();
			        					whatintarnation = false;
			        				}
			        				BlockStack.peek().setHighestSubComplexity(highest);
			        				System.out.println(BlockStack.peek().getBlockComplexity().toString());
			        				dude.get(0).setName("for hate this class N");
			        			}else if(x[y].equals("log_N:")) {
			        				Complexity comp2 = new Complexity(0,1);
			        				CodeBlock block = new CodeBlock(comp2);
			        				BlockStack.push(block);
			        				if(whatintarnation) {
			        					highest = BlockStack.peek().getBlockComplexity();
			        					whatintarnation = false;
			        				}
			        				BlockStack.peek().setHighestSubComplexity(highest);
			        				System.out.println(BlockStack.peek().getBlockComplexity().toString());
			        				dude.get(0).setName("for hate this class LOG");
			        			}
			        		}
			        		top = "for ";
			        		System.out.println(BlockStack.peek().getName());
			        	
			        	// IF KEYWORD IS WHILE - NO WORK 
			        	}else if(keyword.equals("while ")) {//else if keyword is "while"
			        		System.out.println(" inside while");
			        		String loops = x[spaces+1];
			        		Complexity temps = new Complexity(0,0);
			        		CodeBlock block = new CodeBlock(temps);
			        		BlockStack.push(block);
			        		dude.get(0).setName("while hate this class");
			        		BlockStack.peek().setLoopVariable(loops);
			        		top = "while";
			        		if(whatintarnation) {
	        					highest = BlockStack.peek().getBlockComplexity();
	        					whatintarnation = false;
			        		}
			        		BlockStack.peek().setHighestSubComplexity(highest);
			        		System.out.println(BlockStack.peek().getBlockComplexity().toString());
			        		System.out.println(BlockStack.peek().getName());;
			        	
			        	// IF KEYWORD IS NEITHER - NO WORK
			        	} else {
			        		System.out.println(" inside something ");
			        		Complexity temps = new Complexity(0,0);
			        		CodeBlock block = new CodeBlock(temps);
			        		BlockStack.push(block);
			        		dude.get(0).setName("i hate this class");
			        		top = "something";
			        		if(whatintarnation) {
	        					highest = BlockStack.peek().getBlockComplexity();
	        					whatintarnation = false;
			        		}
			        		BlockStack.peek().setHighestSubComplexity(highest);
			        		System.out.println(BlockStack.peek().getBlockComplexity().toString());
			        		System.out.println(BlockStack.peek().getName());
			        	}
			        	
			        // IF KEYWORD IS AFFECTING WHILE LOOP - NO WORK
		        	}else {
		        		System.out.println(" useless");
		        		System.out.println(BlockStack.peek().getLoopVariable());
			        	String keyword1 = "yo";
			        	for(int i = 0; i<x.length; i++) {
			        		if(x[i].equals(BlockStack.peek().getLoopVariable())) {
			        			 keyword1 = x[i];
			        		}
			        	}
			        	System.out.println(keyword1);
			        	
			        	// why this not work??? WHAT THE FUCK
			        	Complexity ah = null;
		        		if(top.equals("while")) {
		        			System.out.println("while????");
		        			if(BlockStack.peek().getLoopVariable()!=null) {
			        			if(BlockStack.peek().getLoopVariable().equals(keyword1)) { // fix
			        				System.out.print(" maybe not useless");
			        				System.out.println(x[spaces+2]);
			        				for(int i = 0; i<x.length; i++) {
						        		if((x[i].equals("/="))||(x[i].equals("+="))) {
						        			 keyword1 = x[i];
						        		}
						        	}
			        				System.out.println(keyword1);
			        				if(keyword1.equals("/=")) {
			        					System.out.println(" maybe not useless1");
			        					ah = new Complexity(0,1);
			        				}else if(keyword1.equals("+=")) {
			        					System.out.println(" maybe not useles2s");
			        					ah = new Complexity(1,0);
			        				}
			        				BlockStack.peek().setBlockComplexity(ah);
			        				System.out.println(BlockStack.peek().getBlockComplexity().toString());
			        				top = "nothing fool";
			        			}
		        			}
			        	}
		        	}
		        	System.out.println(top);
		        	System.out.println(BlockStack.peek().getHighestSubComplexity().toString());
		        	System.out.print(dude.size()+"\n\n");
		        	
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
		// idk about this
	    return highestofthehigh;
	}
	
	// old top compared to now top
	public static void compare(Complexity x, Complexity y) {
//		if(x.getnPower()>y.getnPower()) {
//			if(x.getLogPower()<y.getLogPower()) {
//				System.out.println("1 lmfao");
//				BlockStack.peek().getHighestSubComplexity().setnPower(x.getnPower()+y.getnPower());
//			}
//		}else if(x.getLogPower()>y.getLogPower()) {
//			if(x.getnPower()<y.getnPower()) {
//				System.out.println("2 lmfao");
//				BlockStack.peek().getHighestSubComplexity().setLogPower(x.getLogPower()+y.getLogPower());
//			}
//		}else if(x.getLogPower()>y.getLogPower()) {
//			if(x.getnPower()>y.getnPower()) {
//				System.out.println("3 lmfao");
//				BlockStack.peek().setHighestSubComplexity(x);
//			}
//		}else if((x.getnPower()>=highest.getnPower())&&(y.getLogPower()>=highest.getLogPower())){
//			System.out.println("4 lmfao");
//			BlockStack.peek().setHighestSubComplexity(x);
//			System.out.println(highest.toString());
//		}
		Complexity yeo = new Complexity(x.getnPower()+y.getnPower(),x.getLogPower()+y.getLogPower());
		BlockStack.peek().setHighestSubComplexity(yeo);
	}
	
//	YO IM NOT FUKING DONE WITH THIS	
	// VERY FUKING IMPORTATN
	// WORK TOMORROW PLEASE
	// THIS MAD GAY
	// DONT FAIL
	public static boolean nani(Complexity x, Complexity y) {
			if(x.getnPower()<=y.getnPower()) {
				if(x.getLogPower()<=y.getLogPower()) {
					System.out.println("it is indeed t");
					return true;
				}
			}
		System.out.println("it is indeed f");
		return false;
	}
}
