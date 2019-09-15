
package stock;

import com.sun.javaws.security.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class productocontrolador {

    private final productorepositorio repository;

    productocontrolador (productorepositorio repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/producto/{id}")
    Resource<producto> one(@PathVariable Long id) {

        producto producto = repository.findById(id)
                .orElseThrow(() -> new productoNotFoundException(id));

        return new Resource<>(producto,
                linkTo(methodOn(productoController.class).one(id)).withSelfRel(),
                linkTo(methodOn(productoController.class).all()).withRel("producto"));
    }
    @GetMapping("/producto")
    Resources<Resource<producto>> all() {

        List<Resource<producto>> producto = repository.findAll().stream()
                .map(producto -> new Resource<>(producto,
                        linkTo(methodOn(productoController.class).one(producto.getId())).withSelfRel(),
                        linkTo(methodOn(productoController.class).all()).withRel("producto")))
                .collect(Collectors.toList());

        return new Resources<>(producto,
                linkTo(methodOn(productoController.class).all()).withSelfRel());
    }
    @GetMapping("/producto/{id}")
    Resource<producto> one(@PathVariable Long id) {

        producto producto = repository.findById(id)
                .orElseThrow(() -> new productoNotFoundException(id));

        return assembler.toResource(producto);
    }

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
    void deleteproducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @RestController
    class productoController {

        private final productorepositorio repository;

        private final productorwsource assembler;

        productoController(productorepositorio repository,
                           productorwsource assembler) {

            this.repository = repository;
            this.assembler = assembler;
        }

  ...

    }
}