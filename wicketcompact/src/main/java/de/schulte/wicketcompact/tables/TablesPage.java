package de.schulte.wicketcompact.tables;

import de.schulte.wicketcompact.BaseEntitiesPage;
import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.services.ServiceRegistry;
import de.schulte.wicketcompact.services.TableService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Iterator;

public class TablesPage extends BaseEntitiesPage {

    private final DataView<Table> tables;

    private final AutoCompleteTextField<String> tableQuickSearch;

    private final Form<Void> tableQuickSearchForm;

    private final AjaxSubmitLink quickEditTable;

    public TablesPage(PageParameters parameters) {
        super(parameters);
        IDataProvider<Table> dataProvider = new TablesDataProvider();
        this.tables = new DataView<Table>("tables", dataProvider) {

            @Override
            protected void populateItem(Item<Table> item) {
                item.setModel(new CompoundPropertyModel<>(item.getModel()));
                item.add(new Label("name"));
                item.add(new Label("seatCount"));
                item.add(new Label("orderableElectronically"));
                item.add(new BookmarkablePageLink<>("modifyTable", ModifyTablePage.class, new PageParameters().add("id", item.getModelObject().getId())));
            }
        };

        final AutoCompleteSettings autoCompleteSettings = new AutoCompleteSettings();
        autoCompleteSettings.setCssClassName("autocomplete");

        tableQuickSearch = new AutoCompleteTextField<String>("tableQuickSearch", Model.of(), autoCompleteSettings) {
            @Override
            protected Iterator<String> getChoices(String input) {
                return ServiceRegistry.get(TableService.class).listTableNamesContaining(input).iterator();
            }

        };
        tableQuickSearchForm = new Form<>("tableQuickSearchForm");

        quickEditTable = new AjaxSubmitLink("quickEditTable") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {
                final Table table = ServiceRegistry.get(TableService.class).getByName(tableQuickSearch.getModelObject());
                if (table == null) {
                    return;
                }
                setResponsePage(ModifyTablePage.class, new PageParameters().add("id", table.getId()));
            }
        };

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.tables);
        add(tableQuickSearchForm);
        tableQuickSearchForm.add(tableQuickSearch);
        tableQuickSearchForm.add(quickEditTable);
    }

    @Override
    protected IPageable getPageable() {
        return this.tables;
    }
}
