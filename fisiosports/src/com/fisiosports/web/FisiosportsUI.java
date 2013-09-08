package com.fisiosports.web;

import javax.servlet.annotation.WebServlet;

import com.fisiosports.modelo.entidades.Cliente;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IClientes;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
				Cliente cliente = new Cliente();
				cliente.setId(new Long(2));
				IClientes clientes = FabricaControladores.getIClientes();
				clientes.crearCliente(cliente);
			}
		});
		layout.addComponent(button);
	}

}