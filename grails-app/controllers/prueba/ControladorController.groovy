package prueba

import prueba.Celular
import grails.converters.JSON

class ControladorController {
	
	MiServicioService miServicioService
	
    def traer() {
		
		if (params.id){
			System.out.println("traer id:"+params.id)
			Celular cel = Celular.get(params.id)
			if (cel){
				print "Existe el cel: "+cel.getProperties()
				render([response:cel, status: 200] as JSON)
				return
			}else{
				print "NO existe el cel"
				render([response:"No encontrado", status: 404] as JSON)
				return 
			}
		}else{
			System.out.println("listar todos")
			List<Celular> celulares=Celular.findAll()
			render([response:celulares, status: 200] as JSON)
			return
		}	
    }
	
	def escribir(){
		System.out.println("Escribir")
		def json = request.getJSON()
		
		def resp = miServicioService.validar(json)
		if (resp.esValido){
			Celular cel=new Celular(
				nombre : json.nombre,
				numero : json.numero,
				modelo : ModeloCelular.get(json.modelo)
			).save()
			render([response:cel, status: 201] as JSON)
		}else
			render([response:resp.mensaje, status: resp.status] as JSON)
		return
	}
	
	def modificar(){
		System.out.println("Modificar")
		def json = request.getJSON()
		
		def resp = miServicioService.validarUpdate(params.id,json)
		if (resp.esValido){
			Celular cel=Celular.get(params.id)
			cel.setNumero(json.numero)
			cel.setNombre(json.nombre)
			cel.setModelo(ModeloCelular.get(json.modelo))
			cel.save(flush:true)
			render([response:cel, status: 201] as JSON)
		}else
			render([response:resp.mensaje, status: resp.status] as JSON)
		return
	}
	
	def eliminar(){
		println "Eliminar"
		def resp = miServicioService.validarExiste(params.id)
		
		if (resp.esValido){
			Celular cel=Celular.get(params.id)
			cel.delete(flush:true)
			render([response:cel, status: 201] as JSON)
		}else
			render([response:resp.mensaje, status: resp.status] as JSON)
	
	}
	
}
