package org.example.gymfitness.Repository;

import org.example.gymfitness.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    Optional<Instructor> findByLocker_LockerNumber(String lockerNumber);


}
