
public class BlockStack{
	
	public static void push(CodeBlock block) {
		PythonTracer.dude.add(0,block);
	}
	
	public static CodeBlock pop() {
		CodeBlock x = PythonTracer.dude.get(0);
		PythonTracer.dude.remove(0);
		return x;
	}
	
	public static CodeBlock peek() {
		return PythonTracer.dude.get(0);
	}
	
	public static int size() {
		return PythonTracer.dude.size();
	}
	
	public static boolean isEmpty() {
		return (PythonTracer.dude.size()==0);
	}

}
