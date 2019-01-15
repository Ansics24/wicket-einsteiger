package de.schulte.wicketcompact.exceptions;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import java.util.UUID;

public class UnhandledExceptionPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("ticketNummer", UUID.randomUUID().toString()));
    }
}
