package dev.samuel.auth_service_grocery_story.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scopes")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scopes_seq")
    @SequenceGenerator(name = "scopes_seq", sequenceName = "scopes_seq", allocationSize = 1)
    private Long id;

    private String name;


}
