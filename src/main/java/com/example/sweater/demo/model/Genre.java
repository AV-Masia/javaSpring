package com.example.sweater.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
//@Table(name = "genres"
////        uniqueConstraints = {
////                @UniqueConstraint(columnNames = "name")
////        }
//)
@Table(name = "genres", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
//@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = "name")
//        (onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
//    @EqualsAndHashCode.Include()
    private String name;

//    @Builder.Default
//    @ManyToMany(mappedBy = "genres")
//    private Set<Movie> movies = new HashSet<>();

}