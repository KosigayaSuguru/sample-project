package test3.Exception;

public class TestException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static String defaultMessage = "defaultMessage";

	public TestException() {
		super(defaultMessage);
	}

	public TestException(Throwable t) {
		super(t);
	}

	public TestException(EnumExceptionLevel level) {
		super(defaultMessage, level);
	}

	public TestException(String message) {
		super(message);
	}

	public TestException(String message, EnumExceptionLevel level) {
		super(message, level);
	}
}
