package org.example.gymfitness.Repository;

import org.example.gymfitness.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
