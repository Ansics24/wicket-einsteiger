package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.entities.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleService extends BaseService<Article> {

    public ArticleService() {
        saveDrinks();
        saveCoffee();
    }

    private void saveDrinks() {
        final Category drinks = ServiceRegistry.get(CategoryService.class).get(3L);
        final Article cola = new Article(drinks, "Cola", "https://images.freeimages.com/images/large-previews/9c2/coca-cola-1189757.jpg",
                new BigDecimal("2.10"), "Die klassische, gekühlt mit kleiner Zitrone.");
        final Article water = new Article(drinks, "Mineralwasser", "https://images.freeimages.com/images/large-previews/089/glass-2-filling-with-water-1507886.jpg",
                new BigDecimal("1.80"), "Der Durstlöscher, spritzig im Geschmack.");
        final Article schorle = new Article(drinks, "Saft Schorle", "https://images.freeimages.com/images/large-previews/4a7/juice-1-1328451.jpg",
                new BigDecimal("2.80"), "Verschiedene Saftschorlen, z.B. Apfel, Holunder, Mango, Himbeer, Birne und viele mehr.");
        final Article cocktails = new Article(drinks, "Alkoholfreie Cocktails", "https://images.freeimages.com/images/large-previews/6d7/chapman-1322856.jpg",
                new BigDecimal("4.20"), "Verschiedene Cocktails zum Einheitspreis, z.B. Melon Crush, Eiskaffee mit Tonic ...");
        save(cola);
        save(water);
        save(schorle);
        save(cocktails);
    }

    private void saveCoffee() {
        final Category coffeeCategory = ServiceRegistry.get(CategoryService.class).get(1L);
        final Article cappuccino = new Article(coffeeCategory, "Cappuccino", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
                new BigDecimal("3.20"), "Italienische Kaffeespezialität, die aus einem Espresso und heißem Milchschaum zubereitet wird.");

        final Article coffee = new Article(coffeeCategory, "Kaffee", "https://images.freeimages.com/images/large-previews/9d4/kawa-1-1325674.jpg",
                new BigDecimal("2.20"), "Kaffee aus biologischem Anbau.");

        final Article espresso = new Article(coffeeCategory, "Espresso", "https://images.freeimages.com/images/large-previews/67e/espresso-cup-1310394.jpg",
                new BigDecimal("3.00"), "Espresso aus biologischem Anbau");

        save(cappuccino);
        save(coffee);
        save(espresso);
    }

    public List<Article> listByCategory(final Category category) {
        return listAll().stream().filter(article -> article.getCategory().equals(category)).collect(Collectors.toList());
    }

    public List<Article> listValidByCategory(final Category category) {
        return listAll().stream().filter(article -> article.getCategory().equals(category)).filter(article -> isValid(article)).collect(Collectors.toList());
    }

    private boolean isValid(final Article article) {
        final LocalDate now = LocalDate.now();
        if (now.isBefore(article.getValidFrom())) {
            return false;
        }
        if (now.isAfter(article.getValidTo())) {
            return false;
        }
        return true;
    }

}
