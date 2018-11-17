package de.schulte.wicketcompact.tables;

import de.schulte.wicketcompact.BaseWebPage;
import de.schulte.wicketcompact.entities.Table;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CreateTablePage extends BaseWebPage {

    public CreateTablePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final EditTable editTable = new EditTable("editTable");
        editTable.setTable(new Table());
        add(editTable);
    }

}
