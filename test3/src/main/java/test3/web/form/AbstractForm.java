package test3.web.form;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractForm implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int incrementSeq = 128;
	protected int sequence = LocalDateTime.now().hashCode();
	protected String token = "";

	public int getSequence() {
		return this.sequence;
	}

	public int nextSequence() {
		this.sequence += incrementSeq;
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setIncrementSeq(int inc) {
		this.incrementSeq = inc;
	}

	public int getIncrementSeq() {
		return this.incrementSeq;
	}

	public String getToken() {
		return token;
	}

	public String createToken() {
		this.token = UUID.randomUUID().toString();
		return this.token;
	}

	/**
	 * @param postedToken
	 * @return 保持しているtokenと、postedTokenが一致する場合、true
	 */
	public boolean checkToken(String postedToken) {
		if (this.token.equals(postedToken)) {
			return true;
		}
		return false;
	}
}
