package preformanceTesting;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class PerformanceTest {
	@BeforeClass
	public void setUp() throws Exception {
		//!! CATION THIS WILL ONLY WORK IN MY MACHINE
		File jmeterHome = new File("C:\\Users\\moath\\Desktop\\DEPI\\apache-jmeter-5.6.3");
		JMeterUtils.setJMeterHome(jmeterHome.getAbsolutePath());
		JMeterUtils.loadJMeterProperties(new File(jmeterHome, "bin/jmeter.properties").getAbsolutePath());
		JMeterUtils.initLocale();
		SaveService.loadProperties();
	}

	@Test
	public void testApiResponseTime() throws Exception {
		StandardJMeterEngine jmeter = new StandardJMeterEngine();
		File jmxFile = new File("C:\\Users\\moath\\IdeaProjects\\untitled1\\src\\test\\java\\preformanceTesting\\api_test.jmx");
		jmeter.configure(SaveService.loadTree(jmxFile));
		jmeter.run();

		List<Long> responseTimes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("results.jtl"))) {
			String line;
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {
				if (isHeader) {
					isHeader = false;
					continue;
				}
				String[] columns = line.split(",");
				if (columns.length > 2) {
					responseTimes.add(Long.parseLong(columns[1]));
				}
			}
		}

		double averageResponseTime = responseTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
		System.out.println("Average Response Time: " + averageResponseTime + " ms");

		assertTrue(averageResponseTime >= 450 && averageResponseTime <= 550,
				"Average response time " + averageResponseTime + " ms is not within 450-550 ms");
	}
}