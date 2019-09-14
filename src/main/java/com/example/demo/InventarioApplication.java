import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

}
interface inventario_rep extends JpaRepository<inventario, Long>{
	Collection<inventario> findByInventarioNombre(String nombre);
}

class inventarioCommandLineRunner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		for (inventario b : this.inventario_rep.findAll())
		
	}
	
}



class inventario {
	
	private long id;
	private String nombre; 
	public inventario(String nombre) {
		super();
		this.nombre = nombre;
	}
	public inventario() {
		
	}
	public Long getId(){
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String toString () {
		return "Inventario [id = "+id+", nombre="+nombre+"]";
	}
}