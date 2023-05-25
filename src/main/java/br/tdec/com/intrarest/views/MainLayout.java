package br.tdec.com.intrarest.views;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import br.tdec.com.intrarest.components.appnav.AppNav;
import br.tdec.com.intrarest.components.appnav.AppNavItem;
import br.tdec.com.intrarest.security.SecurityService;
import br.tdec.com.intrarest.view.empresas.VerticaisView;
import br.tdec.com.intrarest.views.about.AboutView;
import br.tdec.com.intrarest.views.helloworld.HelloWorldView;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The main view is a top-level placeholder for other views.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MainLayout extends AppLayout {

	private static final long serialVersionUID = 1L;

	private final SecurityService securityService;

	private H2 viewTitle;

	public MainLayout(SecurityService securityService) {
		this.securityService = securityService;
		setPrimarySection(Section.DRAWER);
		addDrawerContent();
		addHeaderContent();
	}

	private void addHeaderContent() {
		DrawerToggle toggle = new DrawerToggle();
		toggle.getElement().setAttribute("aria-label", "Menu toggle");

		viewTitle = new H2("Intra RESTAPI");
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		Button logout = new Button("Log out", e -> securityService.logout());

		HorizontalLayout header = new HorizontalLayout(toggle, viewTitle, logout);
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(viewTitle);
		header.setWidth("100%");
		header.addClassNames("py-0", "px-m");

		addToNavbar(header);
	}

	private void addDrawerContent() {
		H1 appName = new H1("Intra Rest");
		appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		Header header = new Header(appName);

		Scroller scroller = new Scroller(createNavigation());

		addToDrawer(header, scroller, createFooter());
	}

	private AppNav createNavigation() {
		// AppNav is not yet an official component.
		// For documentation, visit https://github.com/vaadin/vcf-nav#readme
		AppNav nav = new AppNav();

		nav.addItem(new AppNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
		nav.addItem(new AppNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));
		nav.addItem(new AppNavItem("Verticais", VerticaisView.class, LineAwesomeIcon.APP_STORE.create()));

		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();

		return layout;
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		viewTitle.setText(getCurrentPageTitle());
	}

	private String getCurrentPageTitle() {
		PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
		return title == null ? "" : title.value();
	}
}
