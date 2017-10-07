package test3.app.logger;

import java.util.StringJoiner;

public class LogMessage {

	public String[] str;

	public LogMessage(String... a) {
		this.str = a;
	}

	@Override
	public String toString() {
		StringJoiner a = new StringJoiner("\t");
		for (String string : str) {
			a.add(string);
		}
		return a.toString();
	}
}
