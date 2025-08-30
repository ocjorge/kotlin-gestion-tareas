//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// 1. Enumeración para representar el estado de una tarea
enum class EstadoTarea {
    PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA
}

// 2. Data class para representar una tarea
data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    var estado: EstadoTarea,
    val prioridad: Int,
    val responsable: String
)

// 3. Clase que representa un equipo de desarrollo
class Equipo(val nombre: String) {
    private val tareas = mutableListOf<Tarea>()

    // 4. Función para agregar una tarea al equipo
    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    // 5. Función para buscar tareas por responsable
    fun buscarTareasPorResponsable(responsable: String): List<Tarea> {
        return tareas.filter { it.responsable == responsable }
    }

    // 6. Función para cambiar el estado de una tarea
    fun cambiarEstadoTarea(id: Int, nuevoEstado: EstadoTarea): Boolean {
        val tarea = tareas.find { it.id == id } ?: return false
        tarea.estado = nuevoEstado
        return true
    }

    // 7. Función para obtener tareas por prioridad
    fun obtenerTareasPorPrioridad(prioridad: Int): List<Tarea> {
        return tareas.filter { it.prioridad == prioridad }
    }

    // 8. Función para imprimir todas las tareas
    fun imprimirTareas() {
        println("\n--- Todas las tareas del equipo ---")
        tareas.forEach { tarea ->
            println("ID: ${tarea.id}, Título: ${tarea.titulo}, Estado: ${tarea.estado}, Prioridad: ${tarea.prioridad}, Responsable: ${tarea.responsable}")
        }
    }

    // 9. Función para calcular el promedio de prioridad de las tareas
    fun promedioPrioridad(): Double {
        return tareas.map { it.prioridad }.average()
    }
}

// 10. Función de extensión para validar si una tarea es urgente
fun Tarea.esUrgente(): Boolean = this.prioridad <= 2

// 11. Función principal
fun main() {
    // 12. Declaración de variables con diferentes tipos de datos
    val nombreEquipo: String = "Equipo de Desarrollo"
    var cantidadTareas: Int = 0
    val esEquipoActivo: Boolean = true

    // 13. Creación de un objeto Equipo
    val equipo = Equipo(nombreEquipo)

    // 14. Lista de responsables
    val responsables = listOf("Mare", "Hiram", "Jorge")

    // 15. Ciclo for para agregar tareas al equipo
    for (i in 1..5) {
        val tarea = Tarea(
            id = i,
            titulo = "Tarea $i",
            descripcion = "Descripción de la tarea $i",
            estado = EstadoTarea.PENDIENTE,
            prioridad = i,
            responsable = responsables[(i - 1) % responsables.size]
        )
        equipo.agregarTarea(tarea)
        cantidadTareas++
    }

    // 16. Imprimir los responsables del equipo
    println("Responsables del equipo:")
    responsables.forEach { responsable ->
        println("- $responsable")
    }

    // 17. Uso de when para clasificar tareas por prioridad
    val tareaEjemplo = Tarea(6, "Tarea Ejemplo", "Descripción ejemplo", EstadoTarea.PENDIENTE, 3, "Mare")
    val clasificacion = when (tareaEjemplo.prioridad) {
        1 -> "Muy alta"
        2 -> "Alta"
        3 -> "Media"
        4 -> "Baja"
        else -> "Muy baja"
    }
    println("\nLa prioridad de '${tareaEjemplo.titulo}' es $clasificacion.")

    // 18. Uso de if-else para verificar el estado del equipo
    if (esEquipoActivo) {
        println("\n$nombreEquipo está activo.")
    } else {
        println("\n$nombreEquipo no está activo.")
    }

    // 19. Uso de operadores lógicos y aritméticos
    val prioridadPromedio = equipo.promedioPrioridad()
    val mensajePrioridad = if (prioridadPromedio < 3) "Alta prioridad en promedio" else "Prioridad moderada o baja"
    println("\nPromedio de prioridad: $prioridadPromedio ($mensajePrioridad)")

    // 20. Cambiar el estado de una tarea
    equipo.cambiarEstadoTarea(1, EstadoTarea.EN_PROGRESO)
    println("\nEstado de la tarea 1 cambiado a EN_PROGRESO.")

    // 21. Tareas urgentes de Mare
    val tareasUrgentesMare = equipo.buscarTareasPorResponsable("Mare").filter { it.esUrgente() }
    println("\nTareas urgentes de Mare: ${tareasUrgentesMare.size}")

    // 22. Títulos de tareas asignadas a Hiram
    val titulosTareasHiram = equipo.buscarTareasPorResponsable("Hiram").map { it.titulo }
    println("\nTítulos de tareas de Hiram: $titulosTareasHiram")

    // 23. Uso de null safety
    val tareaNula: Tarea? = null
    val tituloTarea = tareaNula?.titulo ?: "Tarea desconocida"
    println("\nTítulo de la tarea (null safety): $tituloTarea")

    // 24. Uso de rangos para iterar
    println("\nIteraciones:")
    for (i in 1..3) {
        println("Iteración $i")
    }

    // 25. Tareas con prioridad 3
    val tareasPrioridad3 = equipo.obtenerTareasPorPrioridad(3)
    println("\nTareas con prioridad 3:")
    tareasPrioridad3.forEach { println("- ${it.titulo}") }

    // 26. Uso de destructuring declarations
    val (id, titulo, _, _, prioridad, responsable) = equipo.buscarTareasPorResponsable("Jorge").first()
    println("\nPrimera tarea de Jorge: ID=$id, Título=$titulo, Prioridad=$prioridad")

    // 27. Uso de operadores de igualdad
    val esMismaTarea = equipo.buscarTareasPorResponsable("Mare").first() == equipo.buscarTareasPorResponsable("Mare").first()
    println("\n¿Es la misma tarea? $esMismaTarea")

    // 28. Uso de ciclos while
    println("\nContador:")
    var contador = 0
    while (contador < 3) {
        println("Contador: $contador")
        contador++
    }

    // 29. Impresión de todas las tareas
    equipo.imprimirTareas()
}
