package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.services.ServiceRegistry;
import de.schulte.wicketcompact.services.TableService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;

public class TablesPage extends BaseEntitiesPage {

    private final DataView<Table> tables;

    public TablesPage(PageParameters parameters) {
        super(parameters);
        IDataProvider<Table> dataProvider = new ListDataProvider<>(new ArrayList<>(ServiceRegistry.get(TableService.class).listAll()));
        this.tables = new DataView<Table>("tables", dataProvider) {

            @Override
            protected void populateItem(Item<Table> item) {
                Table table = item.getModelObject();
                item.setModel(new CompoundPropertyModel<>(table));
                item.add(new Label("name"));
                item.add(new Label("seatCount"));
                item.add(new Label("orderableElectronically"));
            }
        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.tables);
    }

    @Override
    protected IPageable getPageable() {
        return this.tables;
    }
}
