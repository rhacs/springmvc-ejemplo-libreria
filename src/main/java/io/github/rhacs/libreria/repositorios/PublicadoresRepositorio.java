package io.github.rhacs.libreria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.libreria.modelos.Publicador;

@Repository
public interface PublicadoresRepositorio extends JpaRepository<Publicador, Long> {

    /**
     * Busca un registro en el repositorio
     * 
     * @param nombre nombre a buscar
     * @return un objeto {@link Optional} que puede contener un resultado
     */
    public Optional<Publicador> findByNombre(String nombre);

}
