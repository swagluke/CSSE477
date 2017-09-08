package edu.rosehulman.broadcast.protocol;

public class ProtocolException extends Exception {
	private static final long serialVersionUID = 742540479605343333L;

	public ProtocolException() {
		this("Unexpected protocol error.");
	}

	public ProtocolException(String m) {
		super(m);
	}

	public ProtocolException(Throwable t) {
		super(t);
	}

	public ProtocolException(String m, Throwable t) {
		super(m, t);
	}

	public ProtocolException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
