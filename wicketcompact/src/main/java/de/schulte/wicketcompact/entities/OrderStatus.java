package de.schulte.wicketcompact.entities;

public enum OrderStatus {

    NEW("Neu"),
    PREPARATION("In Bearbeitung"),
    DONE("Serviert");

    private final String text;

    OrderStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
