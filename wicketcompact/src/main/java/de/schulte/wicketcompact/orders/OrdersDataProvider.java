package de.schulte.wicketcompact.orders;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.entities.Order;
import de.schulte.wicketcompact.services.OrderService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class OrdersDataProvider extends SortableDataProvider<Order, Void> {

    @Override
    public Iterator<? extends Order> iterator(long first, long count) {
        return ServiceRegistry.get(OrderService.class).listByDateDesc().iterator();
    }

    @Override
    public long size() {
        return ServiceRegistry.get(OrderService.class).listByDateDesc().size();
    }

    @Override
    public IModel<Order> model(Order order) {
        return new CompoundPropertyModel<>(new EntityModel<>(order, OrderService.class));
    }

}
