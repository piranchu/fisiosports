package com.fisiosports.negocio;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fisiosports.modelo.entidades.pacientes.Consulta;
import com.fisiosports.modelo.entidades.pacientes.Evaluacion;
import com.fisiosports.modelo.entidades.pacientes.Gimnasio;
import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.modelo.entidades.pacientes.TerapiaFisica;
import com.fisiosports.modelo.entidades.pacientes.Tratamiento;
import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

@Stateless
public class ControladorPacientes implements IPacientes{

	@PersistenceContext(unitName="fisiosportsPU")
	private EntityManager em;

	@Override
	public void crearPaciente(Paciente paciente) {
//		System.out.println("[ControladorPacientes.crearPaciente] "+paciente.getDocumento());
		em.merge(paciente);
	}

	@Override
	public void agregarEvaluacionPaciente(Evaluacion evaluacion, Paciente paciente){
//		System.out.println("[ControladorPacientes.agregarEvaluacion] evaluacion.getTratamiento().getId():"+evaluacion.getTratamiento().getId());
		if (evaluacion.getTratamiento().getId() == null){
			em.persist(evaluacion.getTratamiento());
		}
		if (evaluacion.getId() == null){
			em.persist(evaluacion);
		}
		evaluacion.setTratamiento(evaluacion.getTratamiento());
		evaluacion.setPaciente(paciente);
		em.merge(evaluacion);
		paciente.getEvaluaciones().add(evaluacion);
		em.merge(paciente);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Paciente obtenerPaciente(Long documento){
		Paciente paciente = null;
		paciente = em.find(Paciente.class, documento);
//		List<Paciente> lista = em
//				.createNamedQuery("Paciente.findByDocument")
//				.setParameter("documento", documento)
//				.getResultList();
//		if (!lista.isEmpty()){
//			paciente = lista.get(0);
//		}
		if (paciente != null){
			for (Evaluacion evaluacion:paciente.getEvaluaciones()){
				evaluacion.getTratamiento().getConsultas();
			}
		}
		return paciente;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> obtenerPacientes() {
		return em.createNamedQuery("Paciente.all").getResultList();
	}

	@Override
	public void agregarConsultaTratamiento(Consulta consulta,
			Evaluacion evaluacion) {
		em.merge(evaluacion);
		em.persist(consulta);
		consulta.setTratamiento(evaluacion.getTratamiento());
//		List<Consulta> consultas = this.obtenerConsultas(evaluacion.getId());
//		consultas.add
		em.merge(evaluacion.getTratamiento());
		Tratamiento tratamiento = em.find(Tratamiento.class, evaluacion.getTratamiento().getId());
		tratamiento.getConsultas().add(consulta);
		
	}

	@Override
	public List<Consulta> obtenerConsultas(Long idEvaluacion) {
//		System.out.println("[ControladorPaciuentes.obtenerConsutas] idEvaluacion");
		Evaluacion evaluacion = em.find(Evaluacion.class, idEvaluacion);
		if (evaluacion == null){
			return new LinkedList<Consulta>();
		}		
//		System.out.println("[ControladorPaciuentes.obtenerConsutas] consultas:"+evaluacion.getTratamiento().getConsultas().size());
		for (Consulta consulta:evaluacion.getTratamiento().getConsultas()){
			if (consulta instanceof Gimnasio){
				Gimnasio gimnasio = (Gimnasio) consulta;
				gimnasio.getTipos();
			}
			if (consulta instanceof TerapiaFisica){
				TerapiaFisica terapia = (TerapiaFisica) consulta;
				terapia.getTipos();
			}
		}
		return evaluacion.getTratamiento().getConsultas();
	}

	@Override
	public Consulta obtenerConsulta(Long id) {
		Consulta consulta = em.find(Consulta.class, id);
		if (consulta != null){
			if (consulta instanceof Gimnasio){
				Gimnasio gimnasio = (Gimnasio) consulta;
				gimnasio.getTipos();
			}
			if (consulta instanceof TerapiaFisica){
				TerapiaFisica terapia = (TerapiaFisica) consulta;
				terapia.getTipos();
			}
		}
		return consulta;
	}

	@Override
	public List<TipoGimnasio> obtenerTipos(Gimnasio gimnasio) {
		gimnasio = em.find(Gimnasio.class, gimnasio.getId());
		return gimnasio.getTipos();
	}

	@Override
	public List<TipoTerapiaFisica> obtenerTipos(TerapiaFisica terapia) {
		terapia = em.merge(terapia);
//		System.out.println("[ControladorPacientes] obtenerTiposTerapiaFisica: "+terapia);
		List<TipoTerapiaFisica> tipos = terapia.getTipos();
//		System.out.println("[ControladorPacientes] lista:");
		for (TipoTerapiaFisica tipo:tipos){
//			System.out.println("[ControladorPacientes] \t "+tipo);
		}
		return tipos;
	}

	@Override
	public void borrarPaciente(Paciente paciente) throws Exception{
		paciente = em.merge(paciente);
		em.remove(paciente);
		
	}

	@Override
	public void borrarEvaluacion(Evaluacion evaluacion) {
		evaluacion = em.merge(evaluacion);
		Paciente paciente = em.merge(evaluacion.getPaciente());
		paciente.getEvaluaciones().remove(evaluacion);
		em.remove(evaluacion);
	}

	@Override
	public void borrarConsultaTratamiento(Consulta consulta) {
		consulta = em.merge(consulta);
		consulta.getTratamiento().getConsultas().remove(consulta);
		em.remove(consulta);		
	}

	
}
