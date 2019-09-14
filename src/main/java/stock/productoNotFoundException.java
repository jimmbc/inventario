package stock;

class productoNotFoundException extends RuntimeException {

     productoNotFoundException(Long id) {
        super("producto no encontrado " + id);
    }
}