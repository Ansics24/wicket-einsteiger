package de.schulte.wicketcompact;

import de.schulte.wicketcompact.login.Login;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
        final WicketApplication application = new WicketApplication();
        tester = new WicketTester(application);
	}

	@Test
    public void givenHomepageRequestedWithoutLoginLoginRenders()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
        tester.assertRenderedPage(Login.class);
	}
}
