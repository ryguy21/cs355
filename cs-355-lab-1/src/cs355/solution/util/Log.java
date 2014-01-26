package cs355.solution.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Log
{
	public static final int				VERBOSE				= 5;
	public static final int				DEBUG				= 4;
	public static final int				INFO				= 3;
	public static final int				WARNING				= 2;
	public static final int				ERROR				= 1;
	public static final int				FATAL				= 0;
	public static final int				TEST				= -1;
	public static final int				NONE				= Short.MIN_VALUE;

	private static int					level				= DEBUG;
	private static boolean				testLoggingEnabled	= false;

	private static List<PrintStream>	verbOuts			= new ArrayList<PrintStream>();
	private static List<PrintStream>	debgOuts			= new ArrayList<PrintStream>();
	private static List<PrintStream>	infoOuts			= new ArrayList<PrintStream>();
	private static List<PrintStream>	warnOuts			= new ArrayList<PrintStream>();
	private static List<PrintStream>	errorOuts			= new ArrayList<PrintStream>();
	private static List<PrintStream>	fatalOuts			= new ArrayList<PrintStream>();
	private static List<PrintStream>	testOuts			= new ArrayList<PrintStream>();

	private static final String			TIME_FORMAT_STR		= "%02d:%02d:%02d.%03d";
	private static final String			STAMP_FORMAT_STR	= "%5s [%%s] :: ";

	private static final String			VERB_STAMP			= String.format(STAMP_FORMAT_STR, "VERB ");
	private static final String			DEBUG_STAMP			= String.format(STAMP_FORMAT_STR, "DEBUG");
	private static final String			INFO_STAMP			= String.format(STAMP_FORMAT_STR, "INFO ");
	private static final String			WARN_STAMP			= String.format(STAMP_FORMAT_STR, "WARN ");
	private static final String			ERROR_STAMP			= String.format(STAMP_FORMAT_STR, "ERROR");
	private static final String			FATAL_STAMP			= String.format(STAMP_FORMAT_STR, "FATAL");
	private static final String			TEST_STAMP			= String.format(STAMP_FORMAT_STR, "TEST ");

	private static StackTraceStream		stream				= new StackTraceStream();

	static
	{
		verbOuts.add(System.out);
		debgOuts.add(System.out);
		infoOuts.add(System.out);
		warnOuts.add(System.err);
		errorOuts.add(System.err);
		fatalOuts.add(System.err);
		testOuts.add(System.out);
	}

	private static String getTimestamp()
	{
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int min = now.get(Calendar.MINUTE);
		int sec = now.get(Calendar.SECOND);
		int ms = now.get(Calendar.MILLISECOND);

		return String.format(TIME_FORMAT_STR, hour, min, sec, ms);
	}

	public synchronized static void setLogLevel(int level)
	{
		Log.level = level;
	}

	public synchronized static int getLogLevel()
	{
		return Log.level;
	}

	public synchronized static String getLogLevelAsString()
	{
		return getLogLevelAsString(level);
	}

	public synchronized static String getLogLevelAsString(int level)
	{
		switch (level)
		{
			case VERBOSE:
				return "VERBOSE";
			case DEBUG:
				return "DEBUG";
			case INFO:
				return "INFO";
			case WARNING:
				return "WARNING";
			case ERROR:
				return "ERROR";
			case FATAL:
				return "FATAL";
			case TEST:
				return "TEST";
			default:
				return "UNKNOWN";
		}
	}

	public synchronized static void enableTestLogging(boolean enable)
	{
		Log.testLoggingEnabled = enable;
	}

	public synchronized static boolean isTestLoggingEnabled()
	{
		return Log.testLoggingEnabled;
	}

	public synchronized static void addOutputStream(PrintStream stream, int level)
	{
		switch (level)
		{
			case VERBOSE:
				if (!verbOuts.contains(stream))
					verbOuts.add(stream);
				break;
			case DEBUG:
				if (!debgOuts.contains(stream))
					debgOuts.add(stream);
				break;
			case INFO:
				if (!infoOuts.contains(stream))
					infoOuts.add(stream);
				break;
			case WARNING:
				if (!warnOuts.contains(stream))
					warnOuts.add(stream);
				break;
			case ERROR:
				if (!errorOuts.contains(stream))
					errorOuts.add(stream);
				break;
			case FATAL:
				if (!fatalOuts.contains(stream))
					fatalOuts.add(stream);
				break;
			case TEST:
				if (!testOuts.contains(stream))
					testOuts.add(stream);
		}
	}

	public synchronized static void log(int level, String message, Object... params)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(message, params);
					break;
				case DEBUG:
					Log.d(message, params);
					break;
				case INFO:
					Log.i(message, params);
					break;
				case WARNING:
					Log.w(message, params);
					break;
				case ERROR:
					Log.e(message, params);
					break;
				case FATAL:
					Log.f(message, params);
					break;
				case TEST:
					Log.t(message, params);
					break;
			}
		}
	}

	public synchronized static void log(int level, boolean x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, byte x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, char x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, char[] x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, double x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, float x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, int x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, long x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, Object x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void log(int level, String x)
	{
		if (Log.level >= level)
		{
			switch (level)
			{
				case VERBOSE:
					Log.v(x);
					break;
				case DEBUG:
					Log.d(x);
					break;
				case INFO:
					Log.i(x);
					break;
				case WARNING:
					Log.w(x);
					break;
				case ERROR:
					Log.e(x);
					break;
				case FATAL:
					Log.f(x);
					break;
				case TEST:
					Log.t(x);
					break;
			}
		}
	}

	public synchronized static void v(String message, Object... params)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : verbOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void v(boolean x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%b\n", stamp, x);
		}
	}

	public synchronized static void v(byte x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void v(char x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public synchronized static void v(char[] x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : verbOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void v(double x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void v(float x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void v(int x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void v(long x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());

			for (PrintStream stream : verbOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void v(Object x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : verbOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void v(String x)
	{
		if (level >= VERBOSE)
		{
			String stamp = String.format(VERB_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : verbOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void d(String message, Object... params)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : debgOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void d(boolean x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%b\n", stamp, x);
		}
	}

	public synchronized static void d(byte x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void d(char x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public synchronized static void d(char[] x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : debgOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void d(double x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void d(float x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void d(int x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void d(long x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			for (PrintStream stream : debgOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void d(Object x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : debgOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void d(String x)
	{
		if (level >= DEBUG)
		{
			String stamp = String.format(DEBUG_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : debgOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void i(String message, Object... params)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : infoOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void i(boolean x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%b\n", stamp, x);
		}
	}

	public synchronized static void i(byte x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void i(char x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public synchronized static void i(char[] x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : infoOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void i(double x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void i(float x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void i(int x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void i(long x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			for (PrintStream stream : infoOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void i(Object x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : infoOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void i(String x)
	{
		if (level >= INFO)
		{
			String stamp = String.format(INFO_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : infoOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void w(String message, Object... params)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : warnOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void w(boolean x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%b\n", stamp, x);
		}
	}

	public synchronized static void w(byte x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void w(char x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public synchronized static void w(char[] x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : warnOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void w(double x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void w(float x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void w(int x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void w(long x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			for (PrintStream stream : warnOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void w(Object x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : warnOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void w(String x)
	{
		if (level >= WARNING)
		{
			String stamp = String.format(WARN_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : warnOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void e(String message, Object... params)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : errorOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void e(boolean x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%b\n", stamp, x);
		}
	}

	public synchronized static void e(byte x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void e(char x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public synchronized static void e(char[] x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : errorOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void e(double x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void e(float x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void e(int x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void e(long x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			for (PrintStream stream : errorOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void e(Object x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : errorOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void e(String x)
	{
		if (level >= ERROR)
		{
			String stamp = String.format(ERROR_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : errorOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public static void f(String message, Object... params)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());
			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : fatalOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public static void f(boolean x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public static void f(byte x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public static void f(char x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public static void f(char[] x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());
			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : fatalOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public static void f(double x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public static void f(float x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public static void f(int x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public static void f(long x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());

			for (PrintStream stream : fatalOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public static void f(Object x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : fatalOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public static void f(String x)
	{
		if (level >= FATAL)
		{
			String stamp = String.format(FATAL_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : fatalOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void t(String message, Object... params)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			message = String.format(message, params);
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : testOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void t(boolean x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%b\n", stamp, x);
		}
	}

	public synchronized static void t(byte x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void t(char x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%c\n", stamp, x);
		}
	}

	public synchronized static void t(char[] x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			String[] lines = new String(x).split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : testOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void t(double x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void t(float x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%s\n", stamp, x);
		}
	}

	public synchronized static void t(int x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void t(long x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			for (PrintStream stream : testOuts)
				stream.printf("%s%d\n", stamp, x);
		}
	}

	public synchronized static void t(Object x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			String message = x.toString();
			String[] lines = message.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : testOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void t(String x)
	{
		if (testLoggingEnabled)
		{
			String stamp = String.format(TEST_STAMP, getTimestamp());
			String[] lines = x.split("\n");

			for (String line : lines)
			{
				for (PrintStream stream : testOuts)
					stream.printf("%s%s\n", stamp, line);
			}
		}
	}

	public synchronized static void printStackTrace(Throwable t, int level)
	{
		if (Log.level >= level)
		{
			stream.clear();
			t.printStackTrace(stream);
			String message = stream.toString();

			switch (level)
			{
				case VERBOSE:
					Log.v(message);
					break;
				case DEBUG:
					Log.d(message);
					break;
				case INFO:
					Log.i(message);
					break;
				case WARNING:
					Log.w(message);
					break;
				case ERROR:
					Log.e(message);
					break;
				case FATAL:
					Log.f(message);
					break;
				case TEST:
					Log.t(message);
					break;
			}
		}
	}

	private static class StackTraceStream extends PrintStream
	{
		private static StringBuilder	string	= new StringBuilder();

		public StackTraceStream()
		{
			super(new StackTraceOutputStream());
		}

		public void clear()
		{
			string = new StringBuilder();
		}

		@Override
		public String toString()
		{
			return string.toString();
		}

		private static class StackTraceOutputStream extends OutputStream
		{
			@Override
			public void write(int i)
			{
				string.append((char) i);
			}
		}
	}
}
