package com.example.articulos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.articulos.model.Articulo;
import com.example.articulos.repository.ArticuloRepository;

@Service // Marca la clase como servicio de Spring
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;

    @Autowired

    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo guardarArticulo(Articulo articulo) {
        if (articulo.getPrecio() != null && articulo.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (articulo.getNombre() == null || articulo.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        return articuloRepository.save(articulo);
    }

    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        articulo.setId(id);
        return articuloRepository.save(articulo);
    }

    public void eliminarArticulo(Long id) {
        if (articuloRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el articulo con este id: " + id);
        }
        articuloRepository.deleteById(id);
    }

}
