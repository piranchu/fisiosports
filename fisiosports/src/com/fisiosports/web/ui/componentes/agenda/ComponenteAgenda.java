package com.fisiosports.web.ui.componentes.agenda;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.calendar.FisioSportsCalendar;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteAgenda extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	//private Button agendarConsulta;
	private FisiosportsUI ui;
	//private HorizontalLayout opcionVistasCalendario;
	private FisioSportsCalendar calendar;

	public ComponenteAgenda(final FisiosportsUI ui){
		this.ui = ui;
		this.setSpacing(true);
		this.setMargin(true);
		this.setSizeFull();
		this.setWidth(100, Unit.PERCENTAGE);

		this.addComponent(this.createMenuBar());
		calendar = new FisioSportsCalendar(ui);
		//this.addComponent(getOpcionesVistaCalendario(calendar));
		
		//calendar = new FisioSportsCalendar(ui);
		this.addComponent(calendar);
		
	}
	
	private MenuBar createMenuBar(){
		MenuBar menu = new MenuBar();
		
		MenuItem itemAdd = menu.addItem("", 
				FontAwesome.PLUS_CIRCLE, 
				this.addCommand);
		itemAdd.setDescription("reservar hora");
		
		MenuItem itemMonth = menu.addItem("", 
				FontAwesome.CALENDAR, 
				this.monthCommand);
		itemMonth.setDescription("ver mes");

		MenuItem itemPrev = menu.addItem("", 
				FontAwesome.ARROW_CIRCLE_LEFT, 
				this.prevCommand);
		itemPrev.setDescription("mes anterior");
		
		MenuItem itemNext = menu.addItem("", 
				FontAwesome.ARROW_CIRCLE_RIGHT, 
				this.nextCommand);
		itemNext.setDescription("mes siguiente");
		
		
		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		
		return menu;
	}
	
	private Command addCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaConsulta(ui, calendar);
			window.setModal(true);
			ui.addWindow(window);
		}
	};

	private Command prevCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			mesAnterior();
		}
	};

	private Command nextCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			mesSiguiente();
		}
	};

	private Command monthCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			vistaMes();
		}
	};

	
	private void mesAnterior() {
		vistaMes();
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(calendar.getStartDate());
		startDate.add(Calendar.MONTH, -1);
		calendar.setStartDate(startDate.getTime());

		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(calendar.getEndDate());
		endDate.add(Calendar.MONTH, -1);
		calendar.setEndDate(endDate.getTime());
	}

	private void mesSiguiente() {
		vistaMes();
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(calendar.getEndDate());
		endDate.add(Calendar.MONTH, 1);
		calendar.setEndDate(endDate.getTime());

		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(calendar.getStartDate());
		startDate.add(Calendar.MONTH, 1);
		calendar.setStartDate(startDate.getTime());
		
	}

	protected void vistaMes() {
		// Set start date to first date in this month
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(calendar.getStartDate());
		//System.out.println("[ComponenteAgenda.vistaMes] startDate:"+startDate);
        startDate.set(java.util.Calendar.MONTH, startDate.get(Calendar.MONTH));
        startDate.set(java.util.Calendar.DATE, 1);
        calendar.setStartDate(startDate.getTime());
        //System.out.println("[ComponenteAgenda.vistaMes] calendar.startDate:"+startDate);

        // Set end date to last day of this month
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.set(java.util.Calendar.MONTH, startDate.get(Calendar.MONTH));
		//System.out.println("[ComponenteAgenda.vistaMes] endDate:"+endDate);
        endDate.set(java.util.Calendar.DATE, 1);
        endDate.roll(java.util.Calendar.DATE, -1);
        calendar.setEndDate(endDate.getTime());
        //System.out.println("[ComponenteAgenda.vistaMes] calendar.endDate:"+endDate);
		
	}

	
}
