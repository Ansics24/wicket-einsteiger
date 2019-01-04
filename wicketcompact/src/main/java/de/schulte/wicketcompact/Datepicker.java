package de.schulte.wicketcompact;

import de.schulte.wicketcompact.resources.JQueryUiJsResourceReference;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;

import java.time.LocalDate;

public class Datepicker extends TextField<LocalDate> {

    public Datepicker(String id) {
        super(id);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(JavaScriptHeaderItem.forReference(JQueryUiJsResourceReference.get()));
        response.render(OnDomReadyHeaderItem.forScript(String.format("$('#%s').datepicker({dateFormat:'dd.mm.yy'});", getMarkupId())));
    }
}
