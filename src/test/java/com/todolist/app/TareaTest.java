package com.todolist.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.todolist.app.Entidad.Tarea;
import com.todolist.app.repositorio.TodoListRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class TareaTest {

	@Autowired
	private TodoListRepositorio repositorio;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testGuardarTarea() {
		
		Tarea tarea = new Tarea("Testear", "Relizar prueba unitaria con Junit", "Pendiente");
		Tarea temporal = repositorio.save(tarea);
		
		assertNotNull(temporal);
	}
	
	@Test
	@Order(4)
	public void testListarTareas() {
		
		List<Tarea> tareas = repositorio.findAll();
		
		assertThat(tareas).size().isGreaterThan(0);
	}
	
	@Test
	@Order(6)
	public void testListaVaciaTareas() {
		
		List<Tarea> tareas = repositorio.findAll();
		
		assertThat(tareas).size().isEqualByComparingTo(0);
	}
	
	@Test
	@Rollback(false)
	@Order(2)
	public void testActualizarTarea() {
		
		Optional<Tarea> optional = repositorio.findById(1);
		Tarea tarea = optional.get();
		tarea.setEstado("REALIZADA");
		Tarea temporal = repositorio.save(tarea);
		
		assertThat(temporal.getEstado().equals("REALIZADA"));
	}
	
	@Test
	@Order(3)
	public void testBuscarTareaID() {
		
		Optional<Tarea> optional = repositorio.findById(1);
		Tarea tarea = optional.get();
		
		assertNotNull(tarea);
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void eliminarTarea() {
		
		boolean existencia = repositorio.findById(1).isPresent();
		repositorio.deleteById(1);
		boolean noExistencia = repositorio.findById(1).isPresent();
		
		assertTrue(existencia);
		assertFalse(noExistencia);
	}
}
