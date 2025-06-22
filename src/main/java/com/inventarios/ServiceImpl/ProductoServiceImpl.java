/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventarios.ServiceImpl;

import com.inventarios.Repository.ProductoRepository;
import com.inventarios.Service.IProductoService;
import com.inventarios.modelo.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;
/**
 *
 * @author ANTONIO
 */
@Service
public class ProductoServiceImpl implements IProductoService{
    
    @Autowired
    private ProductoRepository productoRepositorio;
    

    @Override
    public List<Producto> listarProductos() {
       
           return this.productoRepositorio.findAll();
    }

    @Override
    public Producto BuscarProductoById(Integer idProducto) {
       
           Producto producto = this.productoRepositorio.findById(idProducto).orElseThrow(null);
           
           return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
         
          Producto newProducto= this.productoRepositorio.save(producto);
          
          return newProducto;
    }

   @Override
    public void deleteProducto(Integer idProducto) {
    Producto producto = this.productoRepositorio.findById(idProducto)
        .orElseThrow(() -> new RuntimeException("Producto con ID " + idProducto + " no existe"));
    
    this.productoRepositorio.deleteById(idProducto);
     }
    
    
}
