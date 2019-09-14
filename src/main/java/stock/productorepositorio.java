package stock;


import org.springframework.data.jpa.repository.JpaRepository;

interface productorepositorio  extends JpaRepository<producto, Long> {

}