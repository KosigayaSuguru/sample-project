package test3.Exception;

public class TestRuntimeException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	private static String defaultMessage = "defaultMessage";

	public TestRuntimeException() {
		super(defaultMessage);
	}

	public TestRuntimeException(Throwable t) {
		super(t);
	}

	public TestRuntimeException(EnumExceptionLevel level) {
		super(defaultMessage, level);
	}

	public TestRuntimeException(String message) {
		super(message);
	}

	public TestRuntimeException(String message, EnumExceptionLevel level) {
		super(message, level);
	}
}
