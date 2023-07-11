package com.todolist.app.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.app.Entidad.Tarea;
import com.todolist.app.repositorio.TodoListRepositorio;

@Service
public class toDoServicioImplementacion implements ToDoServicio {

	@Autowired
	TodoListRepositorio repositorio;

	@Override
	public List<Tarea> listarTareas() {
		
		List<Tarea> tareas = repositorio.findAll();
		
		return tareas;
	}

	@Override
	public Tarea buscarTarea(int id) {
		
		Tarea tarea = repositorio.findById(id).get();
		
		return tarea;
	}

	@Override
	public void registrarTarea(Tarea tarea) {
		
		repositorio.save(tarea);
	}

	@Override
	public void actualizarTarea(Tarea tarea) {
		
		Tarea task = new Tarea();
		task.setId(tarea.getId());
		task.setTitulo(tarea.getTitulo());
		task.setDescripcion(tarea.getDescripcion());
		task.setEstado(tarea.getEstado());
		repositorio.save(task);
	}

	@Override
	public void eliminarTarea(int id) {
		
		Tarea tarea = repositorio.findById(id).get();
		repositorio.delete(tarea);
	}
}
