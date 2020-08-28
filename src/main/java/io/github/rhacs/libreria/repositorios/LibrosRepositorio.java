package io.github.rhacs.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.libreria.modelos.Libro;

@Repository
public interface LibrosRepositorio extends JpaRepository<Libro, Long> {

}
