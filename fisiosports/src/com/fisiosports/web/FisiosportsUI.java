package com.fisiosports.web;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.fisiosports.negocio.IAgenda;
import com.fisiosports.negocio.IMovimientos;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.web.ui.componentes.FisioSportsMenu;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("FisioSports")
@SuppressWarnings("serial")
@Theme("fisiosports")
public class FisiosportsUI extends UI {

	private IPacientes iPacientes;
	private IAgenda iAgenda;
	private IMovimientos iMovimientos;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FisiosportsUI.class)
	public static class Servlet extends VaadinServlet {
		@EJB IPacientes iPacientes;
		@EJB IAgenda iAgenda;
		@EJB IMovimientos iMovimientos;
		
		@Override
		protected VaadinServletRequest createVaadinRequest(
				HttpServletRequest request) {
			
			request.setAttribute("pacientes", iPacientes);
			request.setAttribute("agenda", iAgenda);
			request.setAttribute("movimientos", iMovimientos);
			
			return super.createVaadinRequest(request);
		}
	}
	
	private VerticalLayout content;
//	private VerticalLayout layout = new VerticalLayout();
//	private Component componentePrincipal = new Panel();
	

	@Override
	protected void init(VaadinRequest request) {
		
		content = new VerticalLayout();
		content.setWidth(100.0f, Unit.PERCENTAGE);
		
		this.iPacientes = (IPacientes) VaadinService.getCurrentRequest().getAttribute("pacientes");
		this.iAgenda = (IAgenda) VaadinService.getCurrentRequest().getAttribute("agenda");
		this.setiMovimientos((IMovimientos) VaadinService.getCurrentRequest().getAttribute("movimientos"));
		
		HorizontalLayout barraSuperior = new HorizontalLayout();
		
		Image logo = new Image();
		logo.setSource(new ThemeResource("img/logo.png"));
		logo.setWidth("250px");
		logo.setHeight("50px");
		barraSuperior.addComponent(logo);

//		Image fodoLogo = new Image();
//		fodoLogo.setSource(new ThemeResource("img/fondo-logo.png"));
//		fodoLogo.setWidth("-1px");
//		fodoLogo.setHeight("50px");
//		barraSuperior.addComponent(fodoLogo);
		
		content.addComponent(barraSuperior);
		content.addComponent(new FisioSportsMenu(this));
		setContent(content);
		
	}

	public IPacientes getIPacientes(){
		return this.iPacientes;
	}

	public IAgenda getIAgenda(){
		return this.iAgenda;
	}

	public IPacientes getiPacientes() {
		return iPacientes;
	}

	public void setiPacientes(IPacientes iPacientes) {
		this.iPacientes = iPacientes;
	}

	public IAgenda getiAgenda() {
		return iAgenda;
	}

	public void setiAgenda(IAgenda iAgenda) {
		this.iAgenda = iAgenda;
	}

	public IMovimientos getiMovimientos() {
		return iMovimientos;
	}

	public void setiMovimientos(IMovimientos iMovimientos) {
		this.iMovimientos = iMovimientos;
	}
	
	
	
}