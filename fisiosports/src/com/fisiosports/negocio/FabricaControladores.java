package com.fisiosports.negocio;

public class FabricaControladores {
	
	static public IClientes getIClientes(){
		return Clientes.getInstance();
	}

}
