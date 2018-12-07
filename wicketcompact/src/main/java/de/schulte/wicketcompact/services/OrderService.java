package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.entities.Order;

import java.math.BigDecimal;

public class OrderService extends BaseService<Order> {

    public BigDecimal salesFor(final Article article) {
        return listAll().stream().filter(order -> order.getArticle().equals(article)).map(order ->
                order.getArticle().getPrice().multiply(new BigDecimal(order.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
