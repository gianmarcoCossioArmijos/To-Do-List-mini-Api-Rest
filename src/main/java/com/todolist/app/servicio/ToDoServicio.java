package com.todolist.app.servicio;

import java.util.List;

import com.todolist.app.Entidad.Tarea;

public interface ToDoServicio {

	public List<Tarea> listarTareas();
	
	public Tarea buscarTarea(int id);
	
	public void registrarTarea(Tarea tarea);
	
	public void actualizarTarea(Tarea tarea);
	
	public void eliminarTarea(int id);
}
