package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface UserRepository extends CrudRepository<User,Integer> {
}
