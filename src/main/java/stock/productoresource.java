package stock;

import org.springframework.core.io.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
class productorwsource implements ResourceAssembler<producto, Resource<producto>> {

    @Override
    public Resource<producto> toResource(producto producto) {

        return new Resource<>(producto,
                linkTo(methodOn(productoController.class).one(producto.getId())).withSelfRel(),
                linkTo(methodOn(productoController.class).all()).withRel("productos"));
    }
}