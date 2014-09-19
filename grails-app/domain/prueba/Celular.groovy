package prueba

class Celular {

	Integer numero
	String nombre
	ModeloCelular modelo
	
    static constraints = {
		numero(nullable:true)
		nombre(nullable:true)
		modelo(nullable:true)
    }
}
