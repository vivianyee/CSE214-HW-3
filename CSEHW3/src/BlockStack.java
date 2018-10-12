/**
 * The <code>BlockStack</code> class replaces the java API
 * and uses the arraylist as a stack
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class BlockStack{
	
	/**
	 * pushes the CodeBlock into the arraylist
	 * 
	 * @param block
	 * 		the block that's being pushed into the arraylist
	 */
	public static void push(CodeBlock block) {
		PythonTracer.dude.add(0,block);
	}
	
	/**
	 * removes the top CodeBlock of the arraylist
	 * 
	 * @return
	 * 		the block that's being removed from the arraylist
	 */
	public static CodeBlock pop() {
		CodeBlock x = PythonTracer.dude.get(0);
		PythonTracer.dude.remove(0);
		return x;
	}
	
	/**
	 * shows the top CodeBlock of the arraylist
	 * 
	 * @return
	 * 		the block that's on top of the stack (index 0)
	 */
	public static CodeBlock peek() {
		return PythonTracer.dude.get(0);
	}
	
	/**
	 * shows the size of the arraylist or stack
	 * 
	 * @return
	 * 		the number of items in arraylist
	 */
	public static int size() {
		return PythonTracer.dude.size();
	}
	
	/**
	 * determines if the arraylist is empty or not
	 * 
	 * @return
	 * 		true or false if arraylist is empty
	 */
	public static boolean isEmpty() {
		return (PythonTracer.dude.size()==0);
	}

}
