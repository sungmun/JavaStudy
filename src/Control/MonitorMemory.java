package Control;

import java.text.DecimalFormat;

public class MonitorMemory {
	static Runtime r = Runtime.getRuntime();

	public static void showMemory() {
		DecimalFormat format = new DecimalFormat("###,###,###.##");

		// JVM�� ���� �ý��ۿ� �䱸 ������ �ִ� �޸𸮷�, �� ���� ������ OutOfMemory ������ �߻� �մϴ�.
		long max = r.maxMemory();

		// JVM�� ���� �ý��ۿ� ��� �� �޸��� �ѷ�
		long total = r.totalMemory();

		// JVM�� ���� �ý��ۿ� û���Ͽ� ������� �ִ� �޸�(total)�߿��� ��� ������ �޸�
		long free = r.freeMemory();

		System.out.println(
				"Max:" + format.format(max) + ", Total:" + format.format(total) + ", Free:" + format.format(free));
	}
}
