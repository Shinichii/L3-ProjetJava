package geometrie;

public class AireImageSuperieureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AireImageSuperieureException(String message)
	{
		super(message);
	}
	public AireImageSuperieureException(String message,
	         Throwable cause,
	         boolean enableSuppression,
	         boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
