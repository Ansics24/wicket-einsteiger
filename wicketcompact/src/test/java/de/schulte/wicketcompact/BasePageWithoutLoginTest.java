package de.schulte.wicketcompact;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

public class BasePageWithoutLoginTest {

    protected WicketTester tester;

    @Before
    public void setUp() {
        this.tester = new WicketTester(new WicketApplication() {

            @Override
            protected void setUpRequestCycleListeners() {
                // no requestcycle listeners in tests
            }
        });
    }
}
