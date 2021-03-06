package de.schulte.wicketcompact;

import de.schulte.wicketcompact.articles.ArticlesPage;
import de.schulte.wicketcompact.articles.CreateArticlePage;
import de.schulte.wicketcompact.articles.ModifyArticlePage;
import de.schulte.wicketcompact.categories.CategoriesPage;
import de.schulte.wicketcompact.categories.CreateCategoryPage;
import de.schulte.wicketcompact.categories.ModifyCategoryPage;
import de.schulte.wicketcompact.converter.BooleanConverter;
import de.schulte.wicketcompact.converter.CurrencyConverter;
import de.schulte.wicketcompact.converter.LocalDateConverter;
import de.schulte.wicketcompact.exceptions.UnhandledExceptionPage;
import de.schulte.wicketcompact.login.Login;
import de.schulte.wicketcompact.orders.Menu;
import de.schulte.wicketcompact.tables.CreateTablePage;
import de.schulte.wicketcompact.tables.ModifyTablePage;
import de.schulte.wicketcompact.tables.TablesPage;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Session;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.ExceptionSettings;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see de.schulte.wicketcompact.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
        new BeanValidationConfiguration().configure(this);

        mountPage("/articles", ArticlesPage.class);
        mountPage("/categories", CategoriesPage.class);
        mountPage("/tables", TablesPage.class);

        mountPage("/article/${id}", ModifyArticlePage.class);
		mountPage("/article/new", CreateArticlePage.class);

		mountPage("/category/${id}", ModifyCategoryPage.class);
		mountPage("/category/new", CreateCategoryPage.class);

		mountPage("/table/${id}", ModifyTablePage.class);
		mountPage("/table/new", CreateTablePage.class);

        mountPage("/menu", Menu.class);

        mountPage("/login", Login.class);

        getApplicationSettings().setInternalErrorPage(UnhandledExceptionPage.class);
        getExceptionSettings().setAjaxErrorHandlingStrategy(ExceptionSettings.AjaxErrorStrategy.INVOKE_FAILURE_HANDLER);
        setUpRequestCycleListeners();
    }

    protected void setUpRequestCycleListeners() {
        getRequestCycleListeners().add(new LoginAssertingRequestcycleListener());

    }

    @Override
    public Session newSession(Request request, Response response) {
        return new SgSession(request);
    }

    @Override
    protected IConverterLocator newConverterLocator() {
        final ConverterLocator defaultConverterLocator = new ConverterLocator();
        defaultConverterLocator.set(Boolean.class, new BooleanConverter());
        defaultConverterLocator.set(LocalDate.class, new LocalDateConverter());
        defaultConverterLocator.set(BigDecimal.class, new CurrencyConverter());
        return defaultConverterLocator;
    }
}
