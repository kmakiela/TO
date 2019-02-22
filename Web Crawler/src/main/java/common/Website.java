package common;

public enum Website {
	All("All"),
	PAP("Polska Agencja Prasowa"),
	//Focus("Focus.pl"), // brak mo�lwo�ci po��czenia przez program [ Exception in thread "main" java.lang.IllegalArgumentException: protocol = https host = null ]
	MilujcieSie("Mi�ujcie Si�!"),
	homebook("Homebook"),
	diagnozujmy("Diagnozujmy"),
	wprost("Wprost");
	
	String fullName;
	
	Website(String name) {
		fullName = name;
	}
	
}
