package com.zhk.jvm.gc;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Set;

import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;
import sun.tools.jps.Arguments;

public class Test {

	public static void main(String[] args) throws MonitorException, URISyntaxException {
		Arguments arguments = new Arguments(new String[] { "-l" });
		HostIdentifier hostidentifier = arguments.hostId();
		MonitoredHost monitoredhost = MonitoredHost
				.getMonitoredHost(hostidentifier);
		Set<?> set = monitoredhost.activeVms();
		Iterator<?> iterator = set.iterator();
		String s = null;
		while (iterator.hasNext()) {
			int i = ((Integer) iterator.next()).intValue();
			s = (new StringBuilder()).append("//").append(i).append("?mode=r")
					.toString();
			VmIdentifier vmidentifier = new VmIdentifier(s);
			MonitoredVm monitoredvm = monitoredhost.getMonitoredVm(
					vmidentifier, 0);
			String s1 = MonitoredVmUtil.mainClass(monitoredvm, true);
			System.out.println(s1);
		}
	}
}
