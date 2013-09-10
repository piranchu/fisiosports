package com.fisiosports.negocio;

public class FabricaControladores {
	
	static public IPacientes getIClientes(){
		return ControladorPacientes.getInstance();
	}
	
	static public IAgenda getIAgenda(){
		return ControladorAgenda.getInstance();
	}

}
