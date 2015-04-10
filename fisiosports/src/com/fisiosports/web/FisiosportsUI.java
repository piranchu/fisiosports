package com.fisiosports.web;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.fisiosports.modelo.entidades.seguridad.Usuario;
import com.fisiosports.negocio.IAgenda;
import com.fisiosports.negocio.ICaja;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.negocio.ISeguridad;
import com.fisiosports.web.ui.componentes.FisioSportsMenu;
import com.fisiosports.web.ui.componentes.seguridad.LoginComponent;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Title("FisioSports")
@SuppressWarnings("serial")
@Theme("fisiosports")
public class FisiosportsUI extends UI {

	private IPacientes iPacientes;
	private IAgenda iAgenda;
	private ICaja iCaja;
	private ISeguridad iSeguridad;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FisiosportsUI.class)
	public static class Servlet extends VaadinServlet {
		@EJB IPacientes iPacientes;
		@EJB IAgenda iAgenda;
		@EJB ICaja iMovimientos;
		@EJB ISeguridad iSeguridad;
		
		@Override
		protected VaadinServletRequest createVaadinRequest(
				HttpServletRequest request) {
			
			request.setAttribute("pacientes", iPacientes);
			request.setAttribute("agenda", iAgenda);
			request.setAttribute("movimientos", iMovimientos);
			request.setAttribute("seguridad", iSeguridad);
			
			return super.createVaadinRequest(request);
		}
	}
	
	private VerticalLayout content;
//	private VerticalLayout layout = new VerticalLayout();
//	private Component componentePrincipal = new Panel();
	

	@Override
	protected void init(VaadinRequest request) {
		
		this.iPacientes = (IPacientes) VaadinService.getCurrentRequest().getAttribute("pacientes");
		this.iAgenda = (IAgenda) VaadinService.getCurrentRequest().getAttribute("agenda");
		this.iSeguridad = (ISeguridad) VaadinService.getCurrentRequest().getAttribute("seguridad");
		this.iCaja = (ICaja) VaadinService.getCurrentRequest().getAttribute("movimientos");
		
		if (VaadinSession.getCurrent().getAttribute(Usuario.class) == null){
			setLoginPage();
		}else{
			setMainView();
		}
	}
		
	public void setLoginPage(){
		
		content = new VerticalLayout();
		content.setSizeFull();
		content.setWidth(100.0f, Unit.PERCENTAGE);

		LoginComponent login = new LoginComponent();
		content.addComponent(login);
		content.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
		setContent(content);
	}
	
	public void logout(){
		VaadinSession.getCurrent().setAttribute(Usuario.class, null);		
		setLoginPage();
	}

	
	
	public void setMainView(){
		
		content = new VerticalLayout();
		
		content.setWidth(100.0f, Unit.PERCENTAGE);
		
		HorizontalLayout barraSuperior = new HorizontalLayout();
		barraSuperior.setWidth(100, Unit.PERCENTAGE);
		
		Image logo = new Image();
		logo.setSource(new ThemeResource("img/logo.png"));
		logo.setWidth(15, Unit.EM);
//		logo.setWidth("250px");
//		logo.setHeight("50px");
		barraSuperior.addComponent(logo);
//		barraSuperior.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
		
		Button botonSalir = new Button("Salir");
		botonSalir.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		botonSalir.addStyleName(ValoTheme.BUTTON_HUGE);
//		botonSalir.addStyleName(ValoTheme.LABEL_H1);
		
		botonSalir.setIcon(FontAwesome.SIGN_OUT);
		botonSalir.addClickListener(new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				logout();
			}
			
		});
		barraSuperior.addComponent(botonSalir);
		barraSuperior.setComponentAlignment(botonSalir, Alignment.BOTTOM_RIGHT);

		content.addComponent(barraSuperior);
		content.addComponent(new FisioSportsMenu(this));
		setContent(content);
		
		Responsive.makeResponsive(this);
		
	}
	
	public ISeguridad getiSeguridad() {
		return iSeguridad;
	}

	public void setiSeguridad(ISeguridad iSeguridad) {
		this.iSeguridad = iSeguridad;
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

	public ICaja getiCaja() {
		return iCaja;
	}

	public void setiCaja(ICaja iCaja) {
		this.iCaja = iCaja;
	}
	
	
	
}