import { Component, OnInit } from '@angular/core';
import { Categoria } from '../categoria';
import { Router } from '@angular/router';
import { CategoriaService } from '../categoria.service';

@Component({
  selector: 'app-registrar-categoria',
  templateUrl: './registrar-categoria.component.html',
  styleUrls: ['./registrar-categoria.component.css']
})
export class RegistrarCategoriaComponent implements OnInit {

  categoria:Categoria =new Categoria();
  constructor(private categoriaServicio:CategoriaService, private router:Router) { }

  ngOnInit(): void {


  }
//declarando la funcion guardarcategoria
  guardarCategoria(){
    this.categoriaServicio.registrarCategoria(this.categoria).subscribe(dato =>{
      console.log(dato);
      this.irAlaListaDeCategorias();


    },error => console.log(error));
  }

 irAlaListaDeCategorias(){
  console.log("se ejecuro el metodo de redireccion")
  this.router.navigate(["/Categorias-lista"])
 }


 //el boton del formulario html llama a este metodo
//este metodo imprime los datos del empleado al cual yo quiero registrar
  onSubmit(){
    //llamando la funcion
    this.guardarCategoria();
  }

}
