package com.marville001.todo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@GetMapping("/")
	public List<Todo> GetTodos(){
		return todoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Todo GetTodo(@PathVariable Integer id){
		return todoRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/")
	public Todo PostTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}
	
	@PutMapping("/")
	public List<Todo> PutTodo(@RequestBody Todo todo) {
		Todo oldTodo = todoRepository.findById(todo.id).orElse(null);
		
		oldTodo.setTodo(todo.todo);
		oldTodo.setStatus(todo.status);
		oldTodo.setEndTime(todo.endTime);
		
		todoRepository.save(oldTodo);

		return todoRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public Integer DeleteTodo(@PathVariable Integer id) {
		todoRepository.deleteById(id);	
		return id;
		
	}
}
