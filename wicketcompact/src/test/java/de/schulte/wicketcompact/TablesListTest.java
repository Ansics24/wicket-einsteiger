package de.schulte.wicketcompact;

import de.schulte.wicketcompact.tables.TablesPage;
import org.junit.Test;

public class TablesListTest extends BasePageWithoutLoginTest {

    @Override
    public void setUp() {
        super.setUp();
        tester.startPage(TablesPage.class);
        tester.assertRenderedPage(TablesPage.class);
    }

    @Test
    public void tablesListRendersProperly() {
        tester.assertModelValue("tables:1:name", "Costa Rica");
        tester.assertModelValue("tables:3:seatCount", 6);
    }
}
