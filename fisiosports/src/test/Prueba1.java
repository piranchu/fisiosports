package test;

import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

public class Prueba1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		TipoTerapiaFisica terapia = TipoTerapiaFisica.ELECTROANALGESICA;
		
		System.out.println(terapia);
		System.out.println(terapia.name());
		System.out.println(terapia.toString());
		
		
	}

}
