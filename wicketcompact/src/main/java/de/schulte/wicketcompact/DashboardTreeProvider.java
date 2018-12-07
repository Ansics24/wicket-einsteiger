package de.schulte.wicketcompact;

import org.apache.wicket.extensions.markup.html.repeater.util.TreeModelProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.swing.tree.DefaultMutableTreeNode;

public class DashboardTreeProvider extends TreeModelProvider<DefaultMutableTreeNode> {

    public DashboardTreeProvider() {
        super(new DashboardTreeModel(), false);

    }

    @Override
    public IModel<DefaultMutableTreeNode> model(DefaultMutableTreeNode node) {
        return Model.of(node);
    }

}
