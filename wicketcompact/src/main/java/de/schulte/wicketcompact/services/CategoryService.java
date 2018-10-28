package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Category;

public class CategoryService extends BaseService<Category> {

    public CategoryService() {
        final Category coffee = new Category();
        coffee.setName("Kaffee");
        final Category drinks = new Category();
        drinks.setName("Alkoholfreie Getränke");
        final Category beer = new Category();
        beer.setName("Biere");
        save(coffee);
        save(drinks);
        save(beer);
    }
}
