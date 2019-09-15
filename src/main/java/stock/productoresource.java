package stock;


import org.springframework.stereotype.Component;

public class productoResource extends ResourceSupport {

    private final producto producto;

    public PersonResource(final Person person) {
        this.person = person;
        final long id = person.getId();
        add(linkTo(PersonController.class).withRel("people"));
        add(linkTo(methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
        add(linkTo(methodOn(PersonController.class).get(id)).withSelfRel());
    }
}