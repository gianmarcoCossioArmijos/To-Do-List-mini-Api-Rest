package com.todolist.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.app.Entidad.Tarea;

public interface TodoListRepositorio extends JpaRepository<Tarea, Integer> {

}
