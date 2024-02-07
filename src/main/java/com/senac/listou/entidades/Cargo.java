package com.senac.listou.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Entity(name = "cargos")
public class Cargo implements GrantedAuthority {

    @Id
    @Column(name = "id_cargo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_seq")
    @SequenceGenerator(name = "cargo_seq", sequenceName = "seq_cargo", allocationSize = 1)
    private String idCargo;

    @Column(name = "nome")
    private String nome;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo", cascade = CascadeType.MERGE)
    private Set<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return nome;
    }
}
