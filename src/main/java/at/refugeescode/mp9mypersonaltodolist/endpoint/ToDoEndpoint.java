package at.refugeescode.mp9mypersonaltodolist.endpoint;

import at.refugeescode.mp9mypersonaltodolist.persistence.model.ToDo;
import at.refugeescode.mp9mypersonaltodolist.persistence.repository.ToDoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoEndpoint {
    private ToDoRepository toDoRepository;

    public ToDoEndpoint(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping
    List<ToDo> displayAll(){
        return toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    ToDo displayOneById(@PathVariable String id){
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if(toDo.isPresent()){
            return toDo.get();
        }
        return null;
    }

    @PutMapping("/{id}/done")
    ToDo check(@PathVariable String id){
        Optional<ToDo> taskToCheck = toDoRepository.findById(id);
        if (taskToCheck.isPresent()){
            ToDo toDo = taskToCheck.get();
            toDo.setDone(true);
            return toDoRepository.save(toDo);
        }
        return null;
    }

    @PostMapping
    ToDo saveNew(@RequestBody ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @DeleteMapping("/{id}")
    String deleteById(@PathVariable String id){
        toDoRepository.deleteById(id);
        return "we terminated the task";
    }

    @GetMapping("/load")
    ToDo addNewTaskForTest(){
        ToDo newtoDo = new ToDo("sleep", false);
        return toDoRepository.save(newtoDo);
    }
}
