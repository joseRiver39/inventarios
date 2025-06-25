package com.inventarios.Controller;

import com.inventarios.Service.IProductoService;
import com.inventarios.ServiceImpl.ProductoServiceImpl;
import com.inventarios.modelo.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ANTONIO
 */
@RestController
@RequestMapping("/app-inventario")
public class ProductoController {

    @Autowired
    private IProductoService productoServiceImpl;

    @GetMapping("/productos")

    public ResponseEntity<List<Producto>> listarProductos() {

        List<Producto> productos = this.productoServiceImpl.listarProductos();
        System.out.println("productos:" + productos);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/productos/{id}")

    public ResponseEntity<?> ProductoById(@PathVariable Integer id) {

        try {
            Producto producto = this.productoServiceImpl.BuscarProductoById(id);
            System.out.println("productos:" + producto);

            return ResponseEntity.ok(producto);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("producto " + id + "no encontrado");
        }

    }

    @PostMapping("/productos")
    public ResponseEntity<?> ProductoSave(@RequestBody Producto producto) {

        try {
            Producto productos = this.productoServiceImpl.guardarProducto(producto);
            System.out.println("productos:" + productos);

            return ResponseEntity.ok(productos);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("producto " + producto.getDescripcion() + " no Guardado");
        }

    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id,
            @RequestBody Producto productoRecibido) {
        try {
            Producto producto = this.productoServiceImpl.BuscarProductoById(id);

            producto.setDescripcion(productoRecibido.getDescripcion());
            producto.setExistencia(productoRecibido.getExistencia());
            producto.setPrecio(productoRecibido.getPrecio());
            this.productoServiceImpl.guardarProducto(producto);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("producto " + productoRecibido.getDescripcion() + " no Actualizado");
        }

    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productoServiceImpl.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
