package io.github.rhacs.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.libreria.modelos.Autor;

@Repository
public interface AutoresRepositorio extends JpaRepository<Autor, Long> {

    /**
     * Busca los registros en el repositorio de {@link Autor}es que coincidan con el
     * nombre proporcionado
     * 
     * @param nombre nombre a buscar
     * @return un objeto {@link List} con el resultado
     */
    public List<Autor> findByNombreLike(String nombre);

    /**
     * Busca los registros en el repositorio de {@link Autor}es que coincidan con el
     * apellido proporcionado
     * 
     * @param apellido apellido a buscar
     * @return un objeto {@link List} con el resultado
     */
    public List<Autor> findByApellidoLike(String apellido);

    /**
     * Busca los registros en el repositorio de {@link Autor}es que coincidan con el
     * nombre y apellido proporcionados
     * 
     * @param nombre   nombre a buscar
     * @param apellido apellido a buscar
     * @return un objeto {@link List} con el resultado
     */
    public List<Autor> findByNombreAndApellidoLike(String nombre, String apellido);

    /**
     * Busca los registros en el repositorio de {@link Autor}es que coincidan con la
     * nacionalidad proporcionada
     * 
     * @param nacionalidad nacionalidad a buscar
     * @return un objeto {@link List} con el resultado
     */
    public List<Autor> findByNacionalidad(String nacionalidad);

}
