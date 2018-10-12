public class IllegalElementException extends Exception{
	 public IllegalElementException()
	    {  //Default message
	        super("Invalid element used as method parameter.");
	    }

	    public IllegalElementException(String message)
	    {   //Passed message
	        super(message);
	    }	
}