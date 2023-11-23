
function mostrarError(mensaje) {
	// Aquí puedes implementar la lógica para mostrar el mensaje de error en la interfaz de usuario
	alert("Error: " + mensaje);
}

function validarFiltros(search, filterBy) {
	if (!search || search.trim() === "") {
		mostrarError("El parámetro de búsqueda no puede estar vacío");
		return false;
	}

	switch (filterBy) {
		case "sexo":
			if (search.toLowerCase() !== "m" && search.toLowerCase() !== "f") {
				mostrarError("Sexo debe ser 'M' o 'F'");
				return false;
			}
			break;
		case "categoria":
			const categoria = parseInt(search);
			if (isNaN(categoria) || categoria < 1 || categoria > 10) {
				mostrarError("La categoría debe ser un número entre 1 y 10");
				return false;
			}
			break;
		case "anyosTrabajados":
			const anyosTrabajados = parseInt(search);
			if (isNaN(anyosTrabajados) || anyosTrabajados < 0) {
				mostrarError("Años trabajados debe ser un número no negativo");
				return false;
			}
			break;
		// Agrega más validaciones según sea necesario
	}

	return true;
}


function validarYEnviar() {
	var search = document.getElementById("search").value;
	var filterBy = document.getElementById("filterBy").value;

	if (validarFiltros(search, filterBy)) {
		// Realizar la lógica para enviar la solicitud al servidor aquí
		return true; // Permitir el envío del formulario
	} else {
		return false; // Evitar el envío del formulario si la validación falla
	}
}