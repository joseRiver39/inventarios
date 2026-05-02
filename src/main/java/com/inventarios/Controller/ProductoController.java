package com.inventarios.Controller;

import com.inventarios.Service.IProductoService;
import com.inventarios.modelo.Producto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author ANTONIO
 */
@RestController
@RequestMapping("/app-inventario")
@Tag(name = "Productos", description = "API para la gestión de productos del inventario")
public class ProductoController {

    @Autowired
    private IProductoService productoServiceImpl;

    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista con todos los productos registrados en el inventario.")
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos() {

        List<Producto> productos = this.productoServiceImpl.listarProductos();
        System.out.println("productos:" + productos);

        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Buscar producto por ID", description = "Obtiene los detalles de un producto específico mediante su ID.")
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> ProductoById(
            @Parameter(description = "ID del producto a buscar", required = true) @PathVariable Integer id) {

        try {
            Producto producto = this.productoServiceImpl.BuscarProductoById(id);
            System.out.println("productos:" + producto);

            return ResponseEntity.ok(producto);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("producto " + id + " no encontrado");
        }

    }

    @Operation(summary = "Crear nuevo producto", description = "Guarda un nuevo producto en la base de datos.")
    @PostMapping("/productos")
    public ResponseEntity<?> ProductoSave(
            @Parameter(description = "Objeto producto a guardar", required = true) @RequestBody Producto producto) {

        try {
            Producto productos = this.productoServiceImpl.guardarProducto(producto);
            System.out.println("productos:" + productos);

            return ResponseEntity.ok(productos);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("producto " + producto.getDescripcion() + " no Guardado");
        }

    }

    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente proporcionando su ID.")
    @PutMapping("/productos/{id}")
    public ResponseEntity<?> actualizarProducto(
            @Parameter(description = "ID del producto a actualizar", required = true) @PathVariable int id,
            @Parameter(description = "Objeto producto con los nuevos datos", required = true) @RequestBody Producto productoRecibido) {
        
            Producto producto = this.productoServiceImpl.BuscarProductoById(id);

            producto.setDescripcion(productoRecibido.getDescripcion());
            producto.setExistencia(productoRecibido.getExistencia());
            producto.setPrecio(productoRecibido.getPrecio());
            this.productoServiceImpl.guardarProducto(producto);
            return ResponseEntity.ok(producto);     

    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto de la base de datos mediante su ID.")
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProduct(
            @Parameter(description = "ID del producto a eliminar", required = true) @PathVariable Integer id) {
        this.productoServiceImpl.deleteProducto(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("producto eliminado", Boolean.TRUE);
        
        return ResponseEntity.ok(respuesta);
    }
}
