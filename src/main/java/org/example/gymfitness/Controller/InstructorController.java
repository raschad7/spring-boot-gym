package org.example.gymfitness.Controller;

import org.example.gymfitness.Entity.Instructor;
import org.example.gymfitness.Entity.Locker;
import org.example.gymfitness.Repository.LockerRepository;

import org.example.gymfitness.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Instructor")

public class InstructorController {

    @Autowired
    InstructorRepository instructorRep;

    @GetMapping("/Instructors")
    public List<Instructor> getInstructors() {
        return instructorRep.findAll();
    }

    @GetMapping("{InstructorId}")
    public Optional<Instructor> getInstructor(@PathVariable Long InstructorId) {
        return instructorRep.findById(InstructorId);

    }


    @PostMapping("/newInstructor")
    public String saveInstructor(@RequestBody Instructor inst) {
        instructorRep.save(inst);
        return "Class Saved";
    }

    @DeleteMapping("/remove/{InstructorId}")
    public String deleteInstructor(@PathVariable Long InstructorId) {
        instructorRep.deleteById(InstructorId);
        return "Instructor deleted";
    }

    @PutMapping("/update/{instructorId}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long instructorId, @RequestBody Instructor upInstructor) {
        Optional<Instructor> exInstructorOptional = instructorRep.findById(instructorId);

        if (exInstructorOptional.isPresent()) {
            Instructor exInstructor = exInstructorOptional.get();
            exInstructor.setInstructorName(upInstructor.getInstructorName());
            exInstructor.setSpecialization(upInstructor.getSpecialization());

            Instructor updatedEntity = instructorRep.save(exInstructor);
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @GetMapping("/byLockerNumber/{lockerNumber}")
    public ResponseEntity<Instructor> getInstructorByLockerNumber(@PathVariable String lockerNumber) {
        Optional<Instructor> instructorOptional = instructorRep.findByLocker_LockerNumber(lockerNumber);

        return instructorOptional
                .map(instructor -> new ResponseEntity<>(instructor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
