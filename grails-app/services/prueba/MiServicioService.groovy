package prueba

import grails.transaction.Transactional

@Transactional
class MiServicioService {

    def validar(json) {
		def respuesta = [:]
		respuesta.esValido = true
		respuesta.status = 201
		
		/* NOMBRE */
		if (json.nombre.size()<3){
			respuesta.esValido =false
			respuesta.mensaje = "Error: Mensaje debe tener mas de 3 letras"
			respuesta.status = 400
			return respuesta
		}
		
		/* NUMERO */
		if (!json.numero.toString().isNumber()){
			respuesta.esValido =false
			respuesta.mensaje = "Error: El numero debe ser NUMERICO"
			respuesta.status = 400
			return respuesta
		}
		
		if (json.numero<100000){
			respuesta.esValido =false
			respuesta.mensaje = "Error: El numero debe ser un valor de mas de 5 digitos"
			respuesta.status = 400
			return respuesta
		}
		
		/* MODELO */
		if (!json.modelo.toString().isNumber()){
			respuesta.esValido =false
			respuesta.mensaje = "Error: El modelo debe ser NUMERICO"
			respuesta.status = 400
			return respuesta
		}
		
		if (ModeloCelular.get(json.modelo)==null){
			respuesta.esValido =false
			respuesta.mensaje = "Error: El modelo es inexistente"
			respuesta.status = 400
			return respuesta
		}
		
		return respuesta
    }
	
	def validarUpdate(idCel, json){
		
		def respuesta =  [:]
		
		respuesta = validarExiste(idCel)
		if (respuesta.esValido)
			return validar(json)
			
		return respuesta
	}
	
	def validarExiste(idCel){
		def respuesta = [:]
		respuesta.esValido = true
		respuesta.status = 201
		
		//ID existente
		if (Celular.get(idCel)==null){
			respuesta.esValido =false
			respuesta.mensaje = "Error: El id de Celular es inexiste"
			respuesta.status = 400
			return respuesta
		}
		
		return respuesta
	}
}
