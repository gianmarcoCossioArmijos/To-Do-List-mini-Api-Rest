package com.todolist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.app.Entidad.Tarea;
import com.todolist.app.repositorio.TodoListRepositorio;
import com.todolist.app.servicio.ToDoServicio;

@RestController
@RequestMapping("/api/todolist")
public class ToDoController {

	@Autowired
	ToDoServicio servicio;
	
	@GetMapping
	public List<Tarea> getList() {
		
		List<Tarea> tareas = servicio.listarTareas();
		
		return tareas;
	}
	
	@GetMapping(value = "/task/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Tarea getTask(@PathVariable int id) {
		
		Tarea tarea = servicio.buscarTarea(id);
		
		return tarea;
	}
	
	@PostMapping(value = "/save")
	public String saveTask(@RequestBody Tarea tarea) {
		
		servicio.registrarTarea(tarea);
		
		return "tarea guardada";
	}
	
	@PutMapping(value = "/edit/{id}")
	public String editTask(@RequestBody Tarea tarea) {
		
		servicio.actualizarTarea(tarea);
		
		return "tarea editada";
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String deleteTask(@PathVariable int id) {
		
		servicio.eliminarTarea(id);
		
		return "tarea eliminada";
	}
}
