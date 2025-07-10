/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventarios.ServiceImpl;

import Exceptions.ProductoNotFoundException;
import com.inventarios.Repository.ProductoRepository;
import com.inventarios.Service.IProductoService;
import com.inventarios.modelo.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ANTONIO
 */
@Service
public class ProductoServiceImpl implements IProductoService {

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

        Producto newProducto = this.productoRepositorio.save(producto);

        return newProducto;
    }

    @Override
    public void deleteProducto(Integer idProducto) {
        Optional<Producto> producto = this.productoRepositorio.findById(idProducto);

        if (producto.isEmpty()) {
            throw new ProductoNotFoundException("El producto con ID " + idProducto + " no fue encontrado.");
        }

        this.productoRepositorio.deleteById(idProducto);
    }

}
