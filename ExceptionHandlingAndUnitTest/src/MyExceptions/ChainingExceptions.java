package MyExceptions;

public class ChainingExceptions {
	private String mess; 
	private Exception ex;
	
	public void chainingExceptions(Exception e, String mess) {
        try { 
        	e.initCause(new Exception("This is the cause of the exception: " + mess)); 
  
            // Throwing an exception with cause. 
            throw e;  
	   } catch (Exception ex) { 
	       	setMess(e.getMessage());
	       	setEx(e);
	       	
        	// displaying the exception 
            System.out.println(ex); 
  
            // Getting the actual cause of the exception 
        	System.out.println(ex.getCause()); 
        }
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public boolean messIsNull() {
		return mess == null;
	}
}
