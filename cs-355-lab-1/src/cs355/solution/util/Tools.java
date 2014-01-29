package cs355.solution.util;

import java.util.*;

public class Tools
{
	static
	{
		startLockChecks();
	}

	public static void printStackTrace()
	{
		Log.v("Logging stack trace of thread '%s'", Thread.currentThread().getName());
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (int i = 2; i < trace.length; i++)
		{
			StackTraceElement e = trace[i];
			Log.v("\tat %s.%s(%s:%d)", e.getClassName(), e.getMethodName(), e.getFileName(), e.getLineNumber());
		}
	}

	public static void printStackTrace(int length)
	{
		Log.v("Logging stack trace of thread '%s'", Thread.currentThread().getName());
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (int i = 2; i < 2 + length; i++)
		{
			StackTraceElement e = trace[i];
			Log.v("\tat %s.%s(%s:%d)", e.getClassName(), e.getMethodName(), e.getFileName(), e.getLineNumber());
		}
	}

	public static void printStackTrace(String untilClass)
	{
		Log.v("Logging stack trace of thread '%s'", Thread.currentThread().getName());
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (int i = 2; i < trace.length; i++)
		{
			StackTraceElement e = trace[i];
			Log.v("\tat %s.%s(%s:%d)", e.getClassName(), e.getMethodName(), e.getFileName(), e.getLineNumber());

			if (e.getClassName().contains(untilClass))
				return;
		}
	}

	public static void printStackTraceAtLogLevel(int logLevel)
	{
		Log.log(logLevel, "Logging stack trace of thread '%s'", Thread.currentThread().getName());
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (int i = 2; i < trace.length; i++)
		{
			StackTraceElement e = trace[i];
			Log.log(logLevel, "\tat %s.%s(%s:%d)", e.getClassName(), e.getMethodName(), e.getFileName(), e.getLineNumber());
		}
	}

	public static void printStackTraceAtLogLevel(int logLevel, int length)
	{
		Log.log(logLevel, "Logging stack trace of thread '%s'", Thread.currentThread().getName());
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (int i = 2; i < 2 + length; i++)
		{
			StackTraceElement e = trace[i];
			Log.log(logLevel, "\tat %s.%s(%s:%d)", e.getClassName(), e.getMethodName(), e.getFileName(), e.getLineNumber());
		}
	}

	public static void printStackTraceAtLogLevel(int logLevel, String untilClass)
	{
		Log.log(logLevel, "Logging stack trace of thread '%s'", Thread.currentThread().getName());
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (int i = 2; i < trace.length; i++)
		{
			StackTraceElement e = trace[i];
			Log.log(logLevel, "\tat %s.%s(%s:%d)", e.getClassName(), e.getMethodName(), e.getFileName(), e.getLineNumber());

			if (e.getClassName().contains(untilClass))
				return;
		}
	}

	public static String getLink()
	{
		StackTraceElement element = Thread.currentThread().getStackTrace()[2];
		return String.format("%s.%s(%s:%s)", element.getClassName(), element.getMethodName(), element.getFileName(),
				element.getLineNumber());
	}

	public static String getCallerLink()
	{
		StackTraceElement element = Thread.currentThread().getStackTrace()[3];
		return String.format("%s.%s(%s:%s)", element.getClassName(), element.getMethodName(), element.getFileName(),
				element.getLineNumber());
	}

	public static boolean traceContainsMethodName(String name)
	{
		for (StackTraceElement element : Thread.currentThread().getStackTrace())
		{
			if (element.getMethodName().contains(name))
				return true;
		}
		return false;
	}

	public static boolean traceContainsClassName(String name)
	{
		for (StackTraceElement element : Thread.currentThread().getStackTrace())
		{
			if (element.getClassName().contains(name))
				return true;
		}
		return false;
	}

	public static StackTraceElement getFirstStackTraceElementWithClassName(String name)
	{
		for (StackTraceElement element : Thread.currentThread().getStackTrace())
		{
			if (element.getClassName().contains(name))
				return element;
		}
		return null;
	}

	public static StackTraceElement getFirstStackTraceElementWithClassName(String name, StackTraceElement[] trace)
	{
		for (StackTraceElement element : trace)
		{
			if (element.getClassName().contains(name))
				return element;
		}
		return null;
	}

	public static String getTimeStamp()
	{
		Calendar time = Calendar.getInstance();
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int min = time.get(Calendar.MINUTE);
		int sec = time.get(Calendar.SECOND);
		int ms = time.get(Calendar.MILLISECOND);

		String timeStamp = String.format("%02d:%02d:%02d.%03d", hour, min, sec, ms);

		return timeStamp;
	}

	public static String getLongTimeStamp()
	{
		Calendar time = Calendar.getInstance();
		int day = time.get(Calendar.DAY_OF_MONTH);
		String month = time.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
		int year = time.get(Calendar.YEAR);
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int min = time.get(Calendar.MINUTE);
		int sec = time.get(Calendar.SECOND);
		int ms = time.get(Calendar.MILLISECOND);

		String timeStamp = String.format("%02d %3s %04d %02d:%02d:%02d.%03d", day, month, year, hour, min, sec, ms);

		return timeStamp;
	}

	// BEGIN LOCK CHECK CODE ***************************************************************************

	private static final long					LOCK_CHECK_UPDATE_INTERVAL	= 1000l;

	private static int							lockID						= 0;
	private static Hashtable<Integer, String>	locks						= new Hashtable<Integer, String>();

	private static Set<Thread>					marks						= new HashSet<Thread>();

	public static int getNextLockID()
	{
		return lockID++;
	}

	public static void lockGrabbed(int lockID)
	{
		String owner = String.format("'%s' at %s", Thread.currentThread().getName(), getCallerLink());
		locks.put(lockID, owner);
	}

	public static void lockReleased(int lockID)
	{
		locks.remove(lockID);
	}

	private static void startLockChecks()
	{
		final Set<String> threadsToCheck = new HashSet<String>();
		threadsToCheck.add("AWT-EventQueue-0");
		threadsToCheck.add("WorkerThread (1)");
		threadsToCheck.add("WorkerThread removal manager thread");
		threadsToCheck.add("WorkerThread sorter thread");

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask()
		{
			@Override
			public void run()
			{
				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
				for (Thread thread : threadSet)
				{
					if (threadsToCheck.contains(thread.getName()) && thread.getState().equals(Thread.State.BLOCKED))
					{
						if (marks.contains(thread))
						{
							dumpCurrentLocks();
							break;
						}
						else
							marks.add(thread);
					}
				}
			}
		}, LOCK_CHECK_UPDATE_INTERVAL, LOCK_CHECK_UPDATE_INTERVAL);

		Log.v("Lock checks started (in util.Tools)");
	}

	private static void dumpCurrentLocks()
	{
		StringBuilder message = new StringBuilder();
		message.append("==================================== Locks currently held at ");
		message.append(Tools.getTimeStamp());
		message.append(" (");
		message.append(locks.size());
		message.append("): ====================================\n");
		for (Map.Entry<Integer, String> lock : locks.entrySet())
		{
			message.append(String.format("Lock ID: %6d Thread: ", lock.getKey()));
			message.append(lock.getValue());
			message.append('\n');
		}

		Log.w(message.toString());
	}

	// END LOCK CHECK CODE *****************************************************************************

}
