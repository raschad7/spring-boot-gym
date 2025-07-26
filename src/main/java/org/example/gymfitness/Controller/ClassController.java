package org.example.gymfitness.Controller;

import org.example.gymfitness.Entity.Class;
import org.example.gymfitness.Entity.Member;
import org.example.gymfitness.Repository.ClassRepository;
import org.example.gymfitness.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymClass")
public class ClassController {

    @Autowired
    ClassRepository classRep;
    @Autowired
    MemberRepository memberRep;

@GetMapping("/Classes")
    public List<Class> getClasses(){
    return  classRep.findAll();
}
    @PostMapping("/newClass")
    public String saveClass(@RequestBody Class cls){
        classRep.save(cls);
        return "Class Saved";
    }

    @GetMapping ("{classId}")
    public Optional<Class> getClass(@PathVariable Long classId){
        return classRep.findById(classId);

    }
    @DeleteMapping("remove/{classId}")
    public String deleteClass(@PathVariable Long classId){
        classRep.deleteById(classId);
        return "class deleted";
    }
    @PutMapping("update/{classId}")
    public ResponseEntity<Class> updateClass(@PathVariable Long classId, @RequestBody Class updatedClass) {
        Optional<Class> existingClassOptional = classRep.findById(classId);

        if (existingClassOptional.isPresent()) {
            Class existingClass = existingClassOptional.get();
            existingClass.setClassName(updatedClass.getClassName());
            existingClass.setMaxCapacity(updatedClass.getMaxCapacity());
            existingClass.setInstructor(updatedClass.getInstructor());


            Class updatedEntity = classRep.save(existingClass);
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("members/{instructorId}")
    public List<Member> getMembersByInstructor(@PathVariable Long instructorId) {
        List<Class> classes = classRep.findByInstructor_InstructorId(instructorId);

        List<Member> members = new ArrayList<>();
        for (Class gymClass : classes) {
            List<Member> classMembers = gymClass.getMembers();
            if (classMembers != null) {
                members.addAll(classMembers);
            }
        }

        return members;
    }
}
