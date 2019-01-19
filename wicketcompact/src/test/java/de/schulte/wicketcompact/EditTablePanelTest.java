package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.tables.EditTable;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

public class EditTablePanelTest extends BasePageWithoutLoginTest {

    @Before
    public void setUpComponentUnderTest() {
        final EditTable componentUnderTest = new EditTable("editTable");
        componentUnderTest.setTable(new Table("Test-Tisch", 4));
        tester.startComponentInPage(componentUnderTest);
    }

    @Test
    public void nameIsEditable() {
        tester.assertEnabled("editTable:form:name");
        tester.assertVisible("editTable:form:name");
        tester.assertModelValue("editTable:form:name", "Test-Tisch");
    }


    @Test
    public void showsBarcodeWhenOrderableElectronically() {
        tester.assertInvisible("editTable:form:qrCode");
        final FormTester formTester = tester.newFormTester("editTable:form");
        formTester.setValue("orderableElectronically", true);
        tester.executeAjaxEvent("editTable:form:orderableElectronically", "click");
        tester.assertVisible("editTable:form:qrCode");
    }
}
