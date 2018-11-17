package de.schulte.wicketcompact.tables;

import de.schulte.wicketcompact.BaseWebPage;
import de.schulte.wicketcompact.services.ServiceRegistry;
import de.schulte.wicketcompact.services.TableService;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ModifyTablePage extends BaseWebPage {

    public ModifyTablePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final EditTable tableEditPanel = new EditTable("editTable");
        final Long tableId = getPageParameters().get("id").to(Long.class);
        tableEditPanel.setTable(ServiceRegistry.get(TableService.class).get(tableId));
        add(tableEditPanel);
    }

}
