package com.openwebinars.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openwebinars.rest.modelo.Producto;
import com.openwebinars.rest.modelo.ProductoRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductoController {
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	public ProductoController(ProductoRepositorio productoRepositorio) {
	    this.productoRepositorio = productoRepositorio;
	}
	


	/**
	 * Obtenemos todos los productos
	 * 
	 * @return
	 */
	@GetMapping("/producto")
	public List<Producto> obtenerTodos() {
		return this.productoRepositorio.findAll();
	}

	/**
	 * Obtenemos un producto en base a su ID
	 * 
	 * @param id
	 * @return Null si no encuentra el producto
	 */
	@GetMapping("/producto/{id}")
	public Producto obtenerUno(@PathVariable Long id) {
		return this.productoRepositorio.findById(id).orElse(null);
	}

	/**
	 * Insertamos un nuevo producto
	 * 
	 * @param nuevo
	 * @return producto insertado
	 */
	@PostMapping("/producto")
	public Producto nuevoProducto(@RequestBody Producto nuevo) {
		return this.productoRepositorio.save(nuevo);
	}

	/**
	 * 
	 * @param editar
	 * @param id
	 * @return
	 */
	@PutMapping("/producto/{id}")
	public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
		if(this.productoRepositorio.existsById(id)) {
			editar.setId(id);
			return this.productoRepositorio.save(editar);
		}else {
			
		}
		return null;
	}

	/**
	 * Borra un producto del catálogo en base a su id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/producto/{id}")
	public Producto borrarProducto(@PathVariable Long id) {
		if(this.productoRepositorio.existsById(id)) {
			Producto producto = this.productoRepositorio.findById(id).get();
			this.productoRepositorio.delete(producto);
			return producto;
		}else {
			
		}
		return null;
	}

}
