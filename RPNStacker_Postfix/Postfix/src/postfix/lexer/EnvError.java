package postfix.lexer;

public class EnvError extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EnvError(String msg) {
		super(msg);
	}
}

