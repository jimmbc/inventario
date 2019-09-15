package stock;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class productorwsource implements ResourceAssembler<producto, Resource<producto>> {

    @Override
    public Resource<producto> toResource(producto producto) {

        return new Resource<>(producto,
                linkTo(methodOn(productoController.class).one(producto.getId())).withSelfRel(),
                linkTo(methodOn(productoController.class).all()).withRel("productos"));
    }
}