package test3.app.logger;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class LogMessages {

	public static class Message1 {

		public static LogMessage LOG1 = new LogMessage("001", "appType", "log_message1", "log_message_discription1");
		public static LogMessage LOG2 = new LogMessage("002", "appType", "log_message2", "log_message_discription2");
		public static LogMessage LOG3 = new LogMessage("003", "appType", "log_message3", "log_message_discription3");
		public static LogMessage LOG4 = new LogMessage("004", "appType", "log_message4", "log_message_discription4");
	}

	public static void main(String[] args) throws Exception {

		for (Class<?> class1 : LogMessages.class.getClasses()) {

			for (Field field : class1.getDeclaredFields()) {
				field.getName();
				LogMessage t = (LogMessage) field.get(null);
				String[] str = t.str;

				StringJoiner join = new StringJoiner("\t");
				for (int c = 0; c < str.length; c++) {
					join.add("%s");
				}
				System.out.println(String.format("%s\t%s\t%s",
						class1.getSimpleName(), field.getName(), t.toString()));
			}
		}
	}
}
