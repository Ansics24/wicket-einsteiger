package de.schulte.wicketcompact.entities;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Article extends BaseEntity {

    @NotNull
    private Category category;

    private LocalDate validFrom = LocalDate.now();

    private LocalDate validTo = LocalDate.MAX;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "http[s]?://.*\\.jpg", message = "{imageurl.invalid}")
    private String imageUrl;

    @NotNull
    @DecimalMin(value = "0.5", message = "{article.price.min}")
    private BigDecimal price;

    @NotNull
    private String description;

    public Article(Category category, String name, String imageUrl, BigDecimal price, String description) {
        this.category = category;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.setValidFrom(LocalDate.of(2018, 1, 1));
        this.setValidTo(LocalDate.of(9999, 12, 31));
    }

    public Article() {
        this(null, null, null, null, null);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
