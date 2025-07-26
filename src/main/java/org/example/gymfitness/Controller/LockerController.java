package org.example.gymfitness.Controller;

import org.example.gymfitness.Entity.Locker;
import org.example.gymfitness.Entity.Member;
import org.example.gymfitness.Repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Locker")

public class LockerController {
    @Autowired
    LockerRepository lockerRep;
@GetMapping("Lockers")
    public List<Locker> getLockers(){
        return lockerRep.findAll();


    }
    @PostMapping("newLocker")
    public String saveMember(@RequestBody Locker locker) {
        lockerRep.save(locker);
        return "member saved";
    }
    @GetMapping("{lockerId}")
    public Optional<Locker> getLocker(@PathVariable Long lockerId){
        return  lockerRep.findById(lockerId);
    }
    @PutMapping("/update/{lockerId}")
    public ResponseEntity<Locker> updateLocker(@PathVariable Long lockerId, @RequestBody Locker upLocker) {
        Optional<Locker> exLockerOptional = lockerRep.findById(lockerId);

        if (exLockerOptional.isPresent()) {
            Locker exLocker = exLockerOptional.get();
            exLocker.setLockerNumber(upLocker.getLockerNumber());

            Locker updatedEntity = lockerRep.save(exLocker);
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("remove/{LockerId}")
    public String deleteLocker(@PathVariable Long LockerId) {
        lockerRep.deleteById(LockerId);
        return "Locker deleted";
    }
}
