package com.fisiosports.web.ui.calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.agenda.VentanaConsulta;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.shared.ui.calendar.DateConstants;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Table.TableTransferable;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.BackwardEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.BackwardHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.ForwardEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.ForwardHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.MoveEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicDateClickHandler;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;

public class FisioSportsCalendar extends Calendar {

	private static final long serialVersionUID = 1L;
	private Calendar calendar;

	public FisioSportsCalendar(final FisiosportsUI ui) {

		this.calendar = this;
		this.setSizeFull();
		this.setFirstVisibleHourOfDay(8);
		this.setLastVisibleHourOfDay(23);
		this.setFirstVisibleDayOfWeek(1);
		this.setLastVisibleDayOfWeek(5);
		this.setTimeFormat(TimeFormat.Format24H);
		this.setEventProvider(new FisioSportsCalendarProvider(ui.getiAgenda()));

		// GregorianCalendar calStartDate = (GregorianCalendar)
		// GregorianCalendar.getInstance();
		// calStartDate.setTime(new Date());
		// calStartDate.set(java.util.Calendar.DAY_OF_WEEK,
		// calStartDate.getFirstDayOfWeek());
		// this.setStartDate(calStartDate.getTime());
		//
		// GregorianCalendar calEndDate = (GregorianCalendar)
		// GregorianCalendar.getInstance();
		// calEndDate.setTime(new Date());
		// calEndDate.set(java.util.Calendar.DAY_OF_WEEK,
		// calEndDate.getFirstDayOfWeek());
		// calEndDate.add(java.util.Calendar.DAY_OF_WEEK,
		// calEndDate.getFirstDayOfWeek()+4);
		// this.setEndDate(calEndDate.getTime());

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
				FisioSportsCalendarEvent fisioSportsEvent = (FisioSportsCalendarEvent) event
						.getCalendarEvent();
				// System.out.println("[FisioSportsCalendar.EventClickHandler] fisioSportsEvent:"+fisioSportsEvent.getCaption());
				Window window = new VentanaConsulta(ui, calendar,
						fisioSportsEvent.getConsulta());
				window.setModal(true);
				ui.addWindow(window);

				// Notification.show(consulta.getCaption());
			}
		});

		this.setHandler(new BasicDateClickHandler() {

			private static final long serialVersionUID = 1L;

			@Override
			public void dateClick(DateClickEvent event) {

				// System.out.println("[FSCalendar.BasicDateClickHandler]");

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
							calendarEvent.getFirstDayOfWeek() + 4);
					cal.setEndDate(calendarEvent.getTime());
				} else {
					// Default behaviour, change date range to one day
					super.dateClick(event);

				}
			}
		});

		this.setHandler(new BackwardHandler() {

			private static final long serialVersionUID = 1L;

		    @Override
		    public void backward(BackwardEvent event) {
		        Date start = event.getComponent().getStartDate();
		        Date end = event.getComponent().getEndDate();

		        // set new start and end times
		        java.util.Calendar javaCalendarStart = java.util.Calendar.getInstance();
		        javaCalendarStart.setTime(start);
		        java.util.Calendar javaCalendarEnd = java.util.Calendar.getInstance();
		        javaCalendarEnd.setTime(end);      
		        
		        if (start.equals(end)) { // day view
		        	javaCalendarStart = prevCalendarDate(event, javaCalendarStart); 
		        	javaCalendarEnd = javaCalendarStart;
		        }else{ // week view
			        Long dayOffsetMilisecs = javaCalendarEnd.getTimeInMillis() -javaCalendarStart.getTimeInMillis();
			        Long dayOffset = new Long(dayOffsetMilisecs / DateConstants.DAYINMILLIS);
		        	javaCalendarStart.add(java.util.Calendar.DATE, -7);
		        	javaCalendarEnd.setTimeInMillis(javaCalendarStart.getTimeInMillis());
		        	javaCalendarEnd.add(java.util.Calendar.DATE, dayOffset.intValue());		        	
		        }
		        
		        setDates(event, javaCalendarStart.getTime(), javaCalendarEnd.getTime());				
		    }

		    /**
		     * Set the start and end dates for the event
		     * 
		     * @param event
		     *            The event that the start and end dates should be set
		     * @param start
		     *            The start date
		     * @param end
		     *            The end date
		     */
		    protected void setDates(BackwardEvent event, Date start, Date end) {
		        event.getComponent().setStartDate(start);
		        event.getComponent().setEndDate(end);
		    }
		    
			protected java.util.Calendar prevCalendarDate(BackwardEvent event, java.util.Calendar calendar){

            	calendar.add(java.util.Calendar.DATE, -1);

	            int firstDay = event.getComponent().getFirstVisibleDayOfWeek();
	            int lastDay = event.getComponent().getLastVisibleDayOfWeek();
	            int javaCalendarDayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK);
	            int vaadinCalendarDayOfWeek = javaCalendarDayOfWeek - 1; 
	            if (vaadinCalendarDayOfWeek==0){
	            	vaadinCalendarDayOfWeek = 7;
	            }
	            
                // we suppose that 7 >= lastDay >= firstDay >= 1
	            while (vaadinCalendarDayOfWeek < firstDay ||  lastDay < vaadinCalendarDayOfWeek) {
	            	calendar.add(java.util.Calendar.DATE, -1);
	            	vaadinCalendarDayOfWeek--;
		            if (vaadinCalendarDayOfWeek == 0){
		            	vaadinCalendarDayOfWeek = 7;
		            }
	            }
				
				return calendar;
			}
		    
		});

		this.setHandler(new CalendarComponentEvents.RangeSelectHandler() {

			private static final long serialVersionUID = 1L;

			@Override
			public void rangeSelect(RangeSelectEvent event) {
				BasicEvent calendarEvent = new BasicEvent();
				calendarEvent.setStart(event.getStart());
				calendarEvent.setEnd(event.getEnd());
				Window window = new VentanaConsulta(ui, calendar, event
						.getStart());
				ui.addWindow(window);
			}
		});
		
		this.setHandler(new ForwardHandler() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void forward(ForwardEvent event) {
		        Date start = event.getComponent().getStartDate();
		        Date end = event.getComponent().getEndDate();

		        // set new start and end times
		        java.util.Calendar javaCalendarStart = java.util.Calendar.getInstance();
		        javaCalendarStart.setTime(start);
		        java.util.Calendar javaCalendarEnd = java.util.Calendar.getInstance();
		        javaCalendarEnd.setTime(end);      
		        
		        if (start.equals(end)) { // day view
		        	javaCalendarStart = nextCalendarDate(event, javaCalendarStart); 
		        	javaCalendarEnd = javaCalendarStart;
		        }else{ // week view
			        Long dayOffsetMilisecs = javaCalendarEnd.getTimeInMillis() -javaCalendarStart.getTimeInMillis();
			        Long dayOffset = new Long(dayOffsetMilisecs / DateConstants.DAYINMILLIS);
		        	javaCalendarStart.add(java.util.Calendar.DATE, 7);
		        	javaCalendarEnd.setTimeInMillis(javaCalendarStart.getTimeInMillis());
		        	javaCalendarEnd.add(java.util.Calendar.DATE, dayOffset.intValue());		        	
		        }
		        
		        setDates(event, javaCalendarStart.getTime(), javaCalendarEnd.getTime());				
			}
		    
			protected void setDates(ForwardEvent event, Date start, Date end) {
		        event.getComponent().setStartDate(start);
		        event.getComponent().setEndDate(end);
		    }
			
			protected java.util.Calendar nextCalendarDate(ForwardEvent event, java.util.Calendar calendar){

            	calendar.add(java.util.Calendar.DATE, 1);

            	int firstDay = event.getComponent().getFirstVisibleDayOfWeek();
	            int lastDay = event.getComponent().getLastVisibleDayOfWeek();
	            int javaCalendarDayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK);
	            int vaadinCalendarDayOfWeek = javaCalendarDayOfWeek - 1; 
	            if (vaadinCalendarDayOfWeek==0){
	            	vaadinCalendarDayOfWeek = 7;
	            }
	            
                // we suppose that 7 >= lastDay >= firstDay >= 1
	            while (vaadinCalendarDayOfWeek < firstDay ||  lastDay < vaadinCalendarDayOfWeek) {
	            	calendar.add(java.util.Calendar.DATE, 1);
	            	vaadinCalendarDayOfWeek++;
		            if (vaadinCalendarDayOfWeek==8){
		            	vaadinCalendarDayOfWeek = 1;
		            }
	            }
				
				return calendar;
			}
			
		});

	}

}
