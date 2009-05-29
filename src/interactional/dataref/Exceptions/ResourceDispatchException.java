package interactional.dataref.Exceptions;

public class ResourceDispatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4946284701391820046L;
	private String errorString ;
	
	public ResourceDispatchException() {
		super() ;
		errorString = "unknown" ;
	}

	public ResourceDispatchException(String err) {
		super(err);
		this.errorString = err ;
	}

	public String getMessage() {
		return errorString ;
	}
}
