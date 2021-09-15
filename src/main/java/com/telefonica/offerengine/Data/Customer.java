package com.telefonica.offerengine.Data;

import java.util.*;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcustomer;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "typedocument", length = 10)
    private String typedocument;

    @Column(name = "numberdocument", length = 15)
    private String numberdocument;

    @Column(name = "dateborn")
    private Date dateborn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "customer_line",
        joinColumns = @JoinColumn(name = "idcustomer"),
        inverseJoinColumns = @JoinColumn(name = "idlinemobile")
    )
    private Set<LineMobile> LineMobile = new HashSet<>();

    public Customer(
        String name,
        String lastname,
        String typedocument,
        String numberdocument,
        Date dateborn
    ) {
        this.fullname = name.concat(" ").concat(lastname);
        this.typedocument = typedocument;
        this.numberdocument = numberdocument;
        this.dateborn = dateborn;
    }
}
