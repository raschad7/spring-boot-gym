package org.example.gymfitness.Repository;

import org.example.gymfitness.Entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker,Long> {
}
