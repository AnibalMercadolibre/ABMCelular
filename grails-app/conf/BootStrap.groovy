import prueba.Celular
import prueba.ModeloCelular

class BootStrap {

    def init = { servletContext ->
		ModeloCelular mod1=new ModeloCelular(nombre: "Nokia 1100").save()
		new ModeloCelular(nombre: "Motorola I80").save()
		new ModeloCelular(nombre: "Samsung T Gravity").save()
		
		new Celular(modelo: mod1, nombre: "N6-666",numero: 123324).save()
    }
    def destroy = {
		
    }
}
