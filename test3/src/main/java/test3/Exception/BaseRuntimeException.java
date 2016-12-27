package test3.Exception;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private EnumExceptionLevel level = EnumExceptionLevel.ERROR;

	public BaseRuntimeException(Throwable t) {
		super(t);
	}

	public BaseRuntimeException(Throwable t, EnumExceptionLevel level) {
		super(t);
		setLevel(level);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(String message, EnumExceptionLevel level) {
		super(message);
		setLevel(level);
	}

	public EnumExceptionLevel getLevel() {
		return level;
	}

	public void setLevel(EnumExceptionLevel level) {
		this.level = level;
	}
}
