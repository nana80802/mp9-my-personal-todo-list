package at.refugeescode.mp9mypersonaltodolist.persistence.repository;

import at.refugeescode.mp9mypersonaltodolist.persistence.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String>{
    ToDo save(ToDo toDo);
}
