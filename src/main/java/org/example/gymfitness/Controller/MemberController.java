package org.example.gymfitness.Controller;

import org.example.gymfitness.Entity.Member;
import org.example.gymfitness.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Member")

public class MemberController {

    @Autowired
    MemberRepository memberRep;
    @GetMapping("/Members")
    public List<Member> getMembers(){
        return memberRep.findAll();


    }

    @GetMapping("{memberId}")
    public Optional<Member> getMember(@PathVariable Long memberId){
        return  memberRep.findById(memberId);
    }

    @PostMapping("newMember")
    public String saveMember(@RequestBody Member member){
        memberRep.save(member);
        return "member saved";

    }
    @PutMapping("/update/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member upMember) {
        Optional<Member> exMemberOptional = memberRep.findById(memberId);

        if (exMemberOptional.isPresent()) {
            Member exMember = exMemberOptional.get();
            exMember.setMemberName(upMember.getMemberName());

            Member updatedEntity = memberRep.save(exMember);
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("remove/{memberId}")
    public String deleteMember(Long memberId){
        memberRep.deleteById(memberId);
        return "member deleted";
    }
}
