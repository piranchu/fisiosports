package com.fisiosports.web;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.negocio.FabricaControladores;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.client.ui.VCalendar;
import com.vaadin.client.ui.calendar.schedule.CalendarEvent;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table.TableTransferable;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.CalendarEventNotifier;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.MoveEvent;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicDateClickHandler;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;

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
		layout.setWidth("100%");
		layout.setHeight("100%");
		setContent(layout);
		agregar(layout);

		Button button = new Button("Click Me now");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);
	}

	public void agregar(AbstractOrderedLayout layout) {


		Consulta consulta = new Consulta();
		consulta.setId(new Long(1));
		consulta.setStart(start);
		consulta.setEnd(end);

		cal.setTime(new Date());
		cal.set(GregorianCalendar.HOUR, 16);
		cal.set(GregorianCalendar.MINUTE, 01);
		start = cal.getTime();
		cal.set(GregorianCalendar.HOUR, 17);
		cal.set(GregorianCalendar.MINUTE, 30);
		end = cal.getTime();
		
		System.out.println("[start] " + start);
		System.out.println("[end] " + end);

		BasicEvent basicEvent = new BasicEvent();
		basicEvent.setStart(start);
		basicEvent.setEnd(end);
		basicEvent.setCaption("caption");
		basicEvent.setDescription("descripción");
		basicEvent.setStyleName("mycolor");
		calendar.addEvent(basicEvent);

		BasicEvent basicEvent2 = new BasicEvent();
		basicEvent2.setStart(start);
		basicEvent2.setEnd(end);
		calendar.addEvent(basicEvent2);

		System.out.println("[start] " + start);
		System.out.println("[end] " + end);
		// FabricaControladores.getIAgenda().agregarConsulta(consulta);

		// calendar.addEvent(consulta);
		layout.addComponent(calendar);
	}
}