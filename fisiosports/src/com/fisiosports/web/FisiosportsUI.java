package com.fisiosports.web;

import javax.servlet.annotation.WebServlet;

import com.fisiosports.web.ui.componentes.FisioSportsMenu;
import com.google.gwt.layout.client.Layout;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("FisioSports")
@SuppressWarnings("serial")
@Theme("fisiosports")
public class FisiosportsUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FisiosportsUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private VerticalLayout content = new VerticalLayout();
	private VerticalLayout layout = new VerticalLayout();
	private Component componentePrincipal = new Panel();
	

	@Override
	protected void init(VaadinRequest request) {
		
		//content.setStyleName("fondo");
		content.setMargin(true);
		setContent(content);
		this.setStyleName("fondo");
		agregarCuerpo();
		
	}

	private void agregarCuerpo() {
		layout.addComponent(new FisioSportsMenu(this));
		layout.addComponent(componentePrincipal);
		content.addComponent(layout);
	}

	public void setComponentePrincipal(Component component){
		if (this.componentePrincipal != null){
			layout.removeComponent(this.componentePrincipal);
		}
		this.componentePrincipal = component;
		layout.addComponent(this.componentePrincipal);
	}
	
}