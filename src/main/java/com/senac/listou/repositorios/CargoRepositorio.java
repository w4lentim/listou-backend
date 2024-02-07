package com.senac.listou.repositorios;

import com.senac.listou.entidades.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepositorio extends JpaRepository<Cargo, String> {
}
