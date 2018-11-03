package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Category;

public class CategoryService extends BaseService<Category> {

    public CategoryService() {
        final Category coffee = new Category();
        coffee.setName("Kaffee");
        coffee.setImageUrl("https://images.freeimages.com/images/large-previews/5a3/raw-espresso-2-1177689.jpg");

        final Category tea = new Category();
        tea.setName("Tee");
        tea.setImageUrl("https://images.freeimages.com/images/large-previews/ff1/tea-1327515.jpg");

        final Category drinks = new Category();
        drinks.setName("Alkoholfreie Getränke");
        drinks.setImageUrl("https://images.freeimages.com/images/large-previews/9c2/coca-cola-1189757.jpg");

        final Category beer = new Category();
        beer.setName("Biere");
        beer.setImageUrl("https://images.freeimages.com/images/large-previews/dff/three-champion-1328253.jpg");

        final Category breakfast = new Category();
        breakfast.setName("Frühstück");
        breakfast.setImageUrl("https://images.freeimages.com/images/large-previews/def/sandwich-4-1525938.jpg");

        final Category snacks = new Category();
        snacks.setName("Snacks");
        snacks.setImageUrl("https://images.freeimages.com/images/large-previews/0a6/butter-biscuits-1329768.jpg");

        final Category burger = new Category();
        burger.setName("Burger");
        burger.setImageUrl("https://images.freeimages.com/images/large-previews/dbe/burger-on-a-plate-1328009.jpg");

        save(coffee);
        save(tea);
        save(drinks);
        save(beer);
        save(breakfast);
        save(snacks);
        save(burger);
    }
}
