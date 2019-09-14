package stock;

import java.util.List;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class productocontrolador {

    private final productorepositorio repository;

    productocontrolador (productorepositorio repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/producto")
    List<producto> all() {
        return repository.findAll();
    }

    @PostMapping("/producto")
    producto nuevoproducto(@RequestBody producto nuevoproducto) {
        return repository.save(nuevoproducto);
    }

    // Single item

    @GetMapping("/producto/{id}")
    producto one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new productoNotFoundException(id));
    }


    @PutMapping("/producto/{id}")
    producto replaceproducto(@RequestBody producto nuevoproducto, @PathVariable Long id) {

        return repository.findById(id)
                .map(producto-> {
                    producto.setNombre(nuevoproducto.getNombre());
                    producto.setDescripcion(nuevoproducto.getDescripcion());
                    return repository.save(producto);
                })
                .orElseGet(() -> {
                    nuevoproducto.setId(id);
                    return repository.save(nuevoproducto);
                });
    }

    @DeleteMapping("/producto/{id}")
    void eliminarproducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
}