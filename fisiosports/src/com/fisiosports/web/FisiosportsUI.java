package com.fisiosports.web;

import javax.servlet.annotation.WebServlet;

import com.fisiosports.web.ui.componentes.FisioSportsMenu;
import com.fisiosports.web.ui.componentes.FisioSportsTitulo;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("fisiosports")
public class FisiosportsUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FisiosportsUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private VerticalLayout content = new VerticalLayout();
	private VerticalLayout componentePrincipal = new VerticalLayout();
	

	@Override
	protected void init(VaadinRequest request) {
		content.setMargin(true);
		setContent(content);
		agregarTitulo();
		agregarCuerpo();
	}

	private void agregarTitulo() {
		FisioSportsTitulo titulo = new FisioSportsTitulo(); 
		content.addComponent(titulo);
	}

	private void agregarCuerpo() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(new FisioSportsMenu(this, componentePrincipal));
		layout.addComponent(componentePrincipal);
		content.addComponent(layout);
	}

}