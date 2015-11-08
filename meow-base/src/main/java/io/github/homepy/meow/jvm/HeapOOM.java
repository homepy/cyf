package io.github.homepy.meow.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError Java堆内存溢出异常演示
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

	static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}
}
