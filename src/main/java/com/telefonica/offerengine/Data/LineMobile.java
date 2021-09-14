package com.telefonica.offerengine.Data;

import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LineMobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idlinemobile;

    @Column(name = "numberphone", length = 100)
    private String numberphone;

    @Column(name = "typeplane", length = 10)
    private String typeplane;

    @Column(name = "nameplane", length = 100)
    private String nameplane;

    private LocalDateTime datecreated = LocalDateTime.now(ZoneId.of("America/Lima"));

    @Column(name = "state")
    private Boolean state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "line_offer",
        joinColumns = @JoinColumn(name = "idlinemobile"),
        inverseJoinColumns = @JoinColumn(name = "idoffer")
    )
    private Set<Offer> offer = new HashSet<>();

    public LineMobile(String numberphone, String typeplane, String nameplane, Boolean state) {
        this.numberphone = numberphone;
        this.typeplane = typeplane;
        this.nameplane = nameplane;
        this.state = state;
    }
}
