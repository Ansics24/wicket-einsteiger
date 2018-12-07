package de.schulte.wicketcompact;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.swing.tree.DefaultMutableTreeNode;

public class HomePage extends BaseWebPage {

    private static final long serialVersionUID = 1L;

    private final NestedTree<DefaultMutableTreeNode> dashboardTree;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        this.dashboardTree = new NestedTree<DefaultMutableTreeNode>("dashboardTree", new DashboardTreeProvider()) {
            @Override
            protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model) {
                return new Folder<>(id, this, model);
            }
        };
        this.dashboardTree.add(new WindowsTheme());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.dashboardTree);
    }
}
