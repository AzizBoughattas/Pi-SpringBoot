package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Certificate;
import tn.dalhia.entities.Quiz;
import tn.dalhia.services.ICertificateService;

import java.util.List;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Slf4j
public class CertificateController {

        @Autowired
        private ICertificateService certificateService;

        @GetMapping("/{id}/certificate")
        public ResponseEntity<List<Certificate>> get(){
            return ResponseEntity.status(HttpStatus.OK).body(
                    certificateService.getAll()
            );
        }
        @PostMapping("/{idCourse}/{idQuiz}/certificate")
        public ResponseEntity<Certificate> add(@PathVariable("idCourse") Long courseId,  @RequestBody Quiz quizUser, @PathVariable("idQuiz") Long idQuiz){
            Certificate c = certificateService.add(courseId , quizUser, idQuiz);
            return ResponseEntity.status(HttpStatus.OK).body(
                    c
            );
        }

        @PutMapping("/certificate/{id}")
        public ResponseEntity<Certificate> modify(@RequestBody Certificate certificate, @PathVariable("id") Long id){
            Certificate certificate1 = certificateService.modify(certificate, id);
            if(certificate == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        null
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    certificate1
            );
        }
        @GetMapping("/certificate/{id}")
        public ResponseEntity<Certificate> get(@PathVariable("id") Long id){
            Certificate c1 = certificateService.get(id);
            if(c1 == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        null
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    c1
            );
        }
        @DeleteMapping("/certificate/{id}")
        public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
            boolean b = certificateService.delete(id);
            if(!b){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        false
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    true
            );
        }

}
