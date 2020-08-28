package io.github.rhacs.libreria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.libreria.modelos.Categoria;

@Repository
public interface CategoriasRepositorio extends JpaRepository<Categoria, Long> {

    /**
     * Busca un registro en el repositorio
     * 
     * @param nombre nombre a buscar
     * @return un objeto {@link Optional} que puede contener el resultado
     */
    public Optional<Categoria> findByNombre(String nombre);

}
