package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,Integer> {
}
