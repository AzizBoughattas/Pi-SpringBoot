package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int number;
    private int duration;
    private Date dateAdded;
    private boolean finalPhase;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resources> resources;

}