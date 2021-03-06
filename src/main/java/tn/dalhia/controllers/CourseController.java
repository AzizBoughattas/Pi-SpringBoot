package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.MyCourses;
import tn.dalhia.entities.Phase;
import tn.dalhia.services.ICourseService;
import tn.dalhia.services.IPhaseService;

import java.util.List;


@RestController
@RequestMapping("/course/course")
@RequiredArgsConstructor
@Slf4j

public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IPhaseService phaseService;

    @GetMapping("/coursesList")
    public ResponseEntity<List<Course>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.getAll()
        );
    }

    @GetMapping("/{idUser}/myCourses")
    public ResponseEntity<MyCourses> getMyCourses(@PathVariable("idUser") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.getMyCourses(id)
        );
    }

    @PostMapping("/{idCourseCategory}/add")
    public ResponseEntity<Course> add(@RequestBody Course course, @PathVariable("idCourseCategory") Long id){
        Course c = courseService.add(course,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                c
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> modify(@RequestBody Course course, @PathVariable("id") Long id){
        Course course1 = courseService.modify(course, id);
        if(course == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                course1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable("id") Long id){
        Course c1 = courseService.get(id);
        if(c1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                c1
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = courseService.delete(id);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }

    @GetMapping("/MRC")
    public ResponseEntity<List<Course>> mostRelevantCourse(){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.mostRelevantCourse()
        );
    }
}
