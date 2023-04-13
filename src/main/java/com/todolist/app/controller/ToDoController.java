package com.todolist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.app.Entidad.Tarea;
import com.todolist.app.repositorio.TodoListRepositorio;

@RestController
@RequestMapping("/api/todolist")
public class ToDoController {

	@Autowired
	TodoListRepositorio repositorio;
	
	@GetMapping(value = "/")
	public String getList(Model modelo) {
		
		List<Tarea> tareas = repositorio.findAll();
		modelo.addAttribute(tareas);
		
		return "reportando lista de tareas";
	}
	
	@GetMapping(value = "/task/{id}")
	public String getTask(@PathVariable int id, Model modelo) {
		
		Tarea tarea = repositorio.findById(id).get();
		modelo.addAttribute(tarea);
		
		return "reportando tarea";
	}
	
	@PostMapping(value = "/save")
	public String saveTask(@RequestBody Tarea tarea) {
		
		repositorio.save(tarea);
		
		return "tarea guardada";
	}
	
	@PutMapping(value = "/edit/{id}")
	public String editTask(@PathVariable int id, @RequestBody Tarea tarea) {
		
		Tarea task = repositorio.findById(id).get();
		task.setDescripcion(tarea.getDescripcion());
		task.setTitulo(tarea.getTitulo());
		repositorio.save(task);
		
		return "tarea editada";
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String deleteTask(@PathVariable int id) {
		
		Tarea tarea = repositorio.findById(id).get();
		repositorio.delete(tarea);
		
		return "tarea eliminada";
	}
}
