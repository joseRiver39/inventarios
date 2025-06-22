/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventarios.Service;

import com.inventarios.modelo.Producto;
import java.util.List;

/**
 *
 * @author ANTONIO
 */
public interface IProductoService {
    
    public List<Producto> listarProductos();
    
    public  Producto BuscarProductoById(Integer idProducto);
    
    public Producto guardarProducto(Producto producto);
    
    public void deleteProducto(Integer idProduto);
    
}
