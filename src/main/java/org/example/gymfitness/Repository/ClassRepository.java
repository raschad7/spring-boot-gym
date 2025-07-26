package org.example.gymfitness.Repository;

import org.example.gymfitness.Entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class,Long> {
    List<Class> findByInstructor_InstructorId(Long instructorId);

}
