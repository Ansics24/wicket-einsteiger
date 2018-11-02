package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Category;

public class CategoryService extends BaseService<Category> {

    public CategoryService() {
        final Category coffee = new Category();
        coffee.setName("Kaffee");
        coffee.setImageUrl("https://images.freeimages.com/images/large-previews/5a3/raw-espresso-2-1177689.jpg");
        final Category drinks = new Category();
        drinks.setName("Alkoholfreie Getränke");
        drinks.setImageUrl("https://images.freeimages.com/images/large-previews/9c2/coca-cola-1189757.jpg");
        final Category beer = new Category();
        beer.setName("Biere");
        beer.setImageUrl("https://images.freeimages.com/images/large-previews/dff/three-champion-1328253.jpg");
        save(coffee);
        save(drinks);
        save(beer);
    }
}
