class UrlMappings {

	static mappings = {
		
		"/celular/$id?"(controller: "controlador", parseRequest: true){
			action = [POST: "escribir", GET: "traer", PUT: "modificar", DELETE: "eliminar"]
		}
		
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
