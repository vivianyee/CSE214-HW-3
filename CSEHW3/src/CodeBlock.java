/**
 * The <code>CodeBlock</code> class are the blocks of code
 * that can influence the order of complexity in the stack
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class CodeBlock{
	// Block types that can influence the order of complexity
	public static final String[] BLOCK_TYPES = {"def ", "for ", "while ", "if ", "else ", "elif "};
	public static final int DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELIF = 4, ELSE = 5;
	
	private Complexity blockComplexity; // the block's order of complexity
	private Complexity highestSubComplexity; // the block's highest sub complexity
	private String name; // the block's name
	private String loopVariable; // the block's loop variable if it were to be a while loop
	
	/**
	 * Constructor for the CodeBlock class
	 * 
	 * @param blockComplexity
	 * 		the block's order of complexity
	 */
	public CodeBlock(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
		loopVariable = null;
	}

	/**
	 * Getter for blockComplexity
	 * @return
	 * 		block's order of complexity
	 */
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}
	/**
	 * Setter for blockComplexity
	 * @param blockComplexity
	 * 		block's order of complexity
	 */
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}

	/**
	 * Getter for highestSubComplexity
	 * @return
	 * 		block's highest sub complexity
	 */
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	/**
	 * Setter for highestSubComplexity
	 * @param highestSubComplexity
	 * 		block's highest sub complexity
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}

	/**
	 * Getter for name
	 * @return
	 * 		block's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter for name
	 * @param name
	 * 		block's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for loopVariable
	 * @return
	 * 		loop variable
	 */
	public String getLoopVariable() {
		return loopVariable;
	}
	/**
	 * Setter for loopVariable
	 * @param loopVariable
	 * 		loop variable
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
}
