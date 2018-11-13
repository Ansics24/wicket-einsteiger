package de.schulte.wicketcompact.entities;

import de.schulte.wicketcompact.categories.UniqueCategoryName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Category extends BaseEntity {

    @NotNull()
    @UniqueCategoryName
    private String name;

    @NotNull
    @Pattern(regexp = "http[s]?://[.]*\\.jpg", message = "{category.imageurl.invalid}")
    private String imageUrl;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Category() {

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
}
