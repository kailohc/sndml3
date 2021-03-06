package servicenow.datamart;

import static org.junit.Assert.*;
import java.util.Properties;
import org.junit.Test;

import servicenow.api.*;
import servicenow.datamart.DateTimeFactory;

public class DateTimeFactoryTest {

	DateTime myStart = new DateTime("2017-06-15 10:15:00");
	
	Properties getProperties() {
		Properties props = new Properties();
		props.setProperty("start",  "2017-06-14 17:35:35");
		props.setProperty("incident.start",  "2017-06-14 17:35:37");
		props.setProperty("incident.finish",  "2017-06-14 18:20:20");
		return props;
	}
	
	DateTimeFactory getFactory() {
		return new DateTimeFactory(myStart, getProperties());
	}
	
	@Test
	public void testDateTimeFactory() throws Exception {
		DateTimeFactory factory = getFactory();
		assertEquals(myStart, factory.getDate("2017-06-15 10:15:00"));
		assertEquals(myStart, factory.getDate("start"));
		assertEquals(myStart, factory.getDate("START"));
		assertEquals(new DateTime("2017-06-15"), factory.getDate("today"));
		assertEquals(new DateTime("2017-06-14"), factory.getDate("today-1d"));
		assertEquals(new DateTime("2017-06-14"), factory.getDate("Today-1D"));
		assertEquals(new DateTime("2017-06-15 10:10:00"), factory.getDate("start-5m"));
		assertEquals(new DateTime("2017-06-15 10:10:00"), factory.getDate("-5m"));
		assertEquals(new DateTime("2017-06-14 10:15:00"), factory.getDate("-1d"));
		assertEquals(new DateTime("2017-06-14 11:15:00"), factory.getDate("-23h"));
		assertEquals(new DateTime("2017-06-14 17:35:35"), factory.getDate("last"));
		assertEquals(new DateTime("2017-06-14 17:35:35"), factory.getDate("last.start"));
		assertEquals(new DateTime("2017-06-14 17:35:37"), factory.getDate("incident.start"));
		assertEquals(new DateTime("2017-06-14 17:35:37"), factory.getDate("last.incident.start"));
	}

}
