package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.OrderService;
import de.schulte.wicketcompact.services.ServiceRegistry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

public class DashboardTreeModel extends DefaultTreeModel {

    public DashboardTreeModel() {
        super(null);
        setRoot(getTreeRoot());
    }

    private TreeNode getTreeRoot() {
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Kategorien");
        final Collection<Category> categories = ServiceRegistry.get(CategoryService.class).listAll();
        final ArticleService articleService = ServiceRegistry.get(ArticleService.class);
        final OrderService orderService = ServiceRegistry.get(OrderService.class);
        categories.forEach(category -> {
            final DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category.getName());
            root.add(categoryNode);
            articleService.listByCategory(category).forEach(article -> {
                final BigDecimal salesForArticle = orderService.salesFor(article);
                final String salesForArticleAsString = NumberFormat.getCurrencyInstance(Locale.GERMANY).format(salesForArticle);
                final String nodeDescription = String.format("%s (Umsatz %s)", article.getName(), salesForArticleAsString);
                categoryNode.add(new DefaultMutableTreeNode(nodeDescription));
            });
        });
        return root;
    }

}
