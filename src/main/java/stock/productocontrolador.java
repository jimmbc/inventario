
package stock;

import java.util.List;

import javassist.NotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

    @GetMapping
    public List<producto> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") Long id) {
        return RestPreconditions.checkFound(service.findById(id));
    }
    @GetMapping("/producto")
    List<producto> all() {
        return repository.findAll();
    }

    @GetMapping("/producto/{id}")
    ResponseEntity<EntityModel<producto>> findOne(@PathVariable long id) {

        return repository.findById(id)
                .map(employee -> new EntityModel<>(employee,
                        linkTo(methodOn(productocontrolador.class).findOne(employee.getId())).withSelfRel(),
                        linkTo(methodOn(productocontrolador.class).findAll()).withRel("employees")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    public ResponseEntity<Resources<productoresource>> all() {
        final List<PersonResource> collection =
                personRepository.findAll().stream().map(PersonResource::new).collect(Collectors.toList());
        final Resources<PersonResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }
}