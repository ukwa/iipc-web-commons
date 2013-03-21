package org.archive.url;

import java.net.URISyntaxException;

import junit.framework.TestCase;

public class AggressiveIAURLCanonicalizerTest extends TestCase {
	static AggressiveIAURLCanonicalizer ia = new AggressiveIAURLCanonicalizer();
	public void testCanonicalize() throws URISyntaxException {
		// FULL end-to-end tests:
		check("http://www.alexa.com/","http://alexa.com/");
		check("http://archive.org/index.html","http://archive.org/index.html");
		check("http://archive.org/index.html?","http://archive.org/index.html");
		check("http://archive.org/index.html?a=b","http://archive.org/index.html?a=b");
		check("http://archive.org/index.html?b=b&a=b","http://archive.org/index.html?a=b&b=b");
		check("http://archive.org/index.html?b=a&b=b&a=b","http://archive.org/index.html?a=b&b=a&b=b");
		check("http://www34.archive.org/index.html?b=a&b=b&a=b","http://archive.org/index.html?a=b&b=a&b=b");
	}

	private static void check(String orig, String want) throws URISyntaxException {
		HandyURL u = URLParser.parse(orig);
		ia.canonicalize(u);
		String got = u.getURLString();
		assertEquals(want,got);
		
		HandyURL u2 = URLParser.parse(got);
		ia.canonicalize(u2);
		String got2 = u2.getURLString();
		assertEquals("Second passs changed!",got,got2);
	}
}
