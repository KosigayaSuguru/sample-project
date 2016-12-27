package test3.Exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	private EnumExceptionLevel level = EnumExceptionLevel.ERROR;

	public BaseException(Throwable t) {
		super(t);
	}

	public BaseException(Throwable t, EnumExceptionLevel level) {
		super(t);
		setLevel(level);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, EnumExceptionLevel level) {
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
