package com.fisiosports.web.ui.calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.agenda.VentanaConsulta;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Table.TableTransferable;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.MoveEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.RangeSelectNotifier;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicDateClickHandler;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;

public class FisioSportsCalendar extends Calendar{

	private static final long serialVersionUID = 1L;
	private Calendar calendar;

	public FisioSportsCalendar(final FisiosportsUI ui) {

		this.calendar = this;
//		this.setWidth("600px");
//		this.setHeight("400px");
		this.setSizeFull();
		this.setWidth(100, Unit.PERCENTAGE);
		this.setFirstVisibleHourOfDay(8);
		this.setFirstVisibleDayOfWeek(1);
		this.setLastVisibleDayOfWeek(6);
		this.setWidth(100.0f, Unit.PERCENTAGE);
		this.setHeight(100.0f, Unit.PERCENTAGE);
		this.setTimeFormat(TimeFormat.Format24H);
		this.setEventProvider(new FisioSportsCalendarProvider(ui.getiAgenda()));

		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		cal.setTime(new Date());
		cal.set(java.util.Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		this.setStartDate(cal.getTime());
		cal.add(java.util.Calendar.DAY_OF_WEEK, 6);
		this.setEndDate(cal.getTime());

		this.setHandler(new BasicEventMoveHandler() {
			private static final long serialVersionUID = 1L;
			@Override
			public void eventMove(MoveEvent event) {
				super.eventMove(event);
			}
			@Override
			protected void setDates(EditableCalendarEvent event, Date start,
					Date end) {
				super.setDates(event, start, end);
			}
		});

		this.setDropHandler(new DropHandler() {

			private static final long serialVersionUID = 1L;

			@Override
			public void drop(DragAndDropEvent event) {
				CalendarTargetDetails details = (CalendarTargetDetails) event
						.getTargetDetails();
				TableTransferable transferable = (TableTransferable) event
						.getTransferable();
			}

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
		});

		this.setHandler(new EventClickHandler() {
	
			private static final long serialVersionUID = 1L;

			@Override
			public void eventClick(EventClick event) {
				FisioSportsCalendarEvent fisioSportsEvent = (FisioSportsCalendarEvent) event.getCalendarEvent();
//				System.out.println("[FisioSportsCalendar.EventClickHandler] fisioSportsEvent:"+fisioSportsEvent.getCaption());
				Window window = new VentanaConsulta(ui, calendar, fisioSportsEvent.getConsulta());
				window.setModal(true);
				ui.addWindow(window);
				
				//Notification.show(consulta.getCaption());
			}
		});

		this.setHandler(new BasicDateClickHandler() {
			@Override
			public void dateClick(DateClickEvent event) {
				
				System.out.println("[FSCalendar.BasicDateClickHandler]");
				
				Calendar cal = event.getComponent();
				long currentCalDateRange = cal.getEndDate().getTime()
						- cal.getStartDate().getTime();
				
				
				if (currentCalDateRange < (24 * 60 * 60 * 1000)) {
					// Change the date range to the current week
					java.util.Calendar calendarEvent = GregorianCalendar
							.getInstance();
					calendarEvent.setTime(event.getDate());
					calendarEvent.set(java.util.Calendar.DAY_OF_WEEK,
							calendarEvent.getFirstDayOfWeek());
					cal.setStartDate(calendarEvent.getTime());
					calendarEvent.set(java.util.Calendar.DAY_OF_WEEK,
							calendarEvent.getFirstDayOfWeek() + 6);
					cal.setEndDate(calendarEvent.getTime());
				} else {
					// Default behaviour, change date range to one day
					super.dateClick(event);
				}
			}
		});

		this.setHandler(new CalendarComponentEvents.RangeSelectHandler() {
			
			@Override
			public void rangeSelect(RangeSelectEvent event) {
			    BasicEvent calendarEvent = new BasicEvent();
			    calendarEvent.setStart(event.getStart());
			    calendarEvent.setEnd(event.getEnd());
			    System.out.println("[inicio:"+event.getStart()+"]");
			    System.out.println("[fin:"+event.getEnd()+"]");
				Window window = new VentanaConsulta(ui, calendar, event.getStart());
				ui.addWindow(window);
			}
		});
		
	}

	private void setHandler(RangeSelectNotifier rangeSelectNotifier) {
		// TODO Auto-generated method stub
		
	}
}
