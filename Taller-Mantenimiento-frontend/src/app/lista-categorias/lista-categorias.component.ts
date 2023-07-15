import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../categoria.service';
import { Categoria } from '../categoria';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-categorias',
  templateUrl: './lista-categorias.component.html',
  styleUrls: ['./lista-categorias.component.css']
})
export class ListaCategoriasComponent implements OnInit {
  [x: string]: any;
//se comunica con la en index
  categorias:Categoria[];


  constructor(private categoriaService:CategoriaService ,private router:Router) { }

  ngOnInit(): void {

    this.obtenerCategorias();

  }

  private obtenerCategorias(){
    this.categoriaService.obtenerListaDeCategorias().subscribe(dato => {
         this.categorias=dato;

    })

  };

//El boton del archivo html llama a esta categoria
  actualizarCategoria(id:number){
    //lo que se esta haciendo aca es llamar a una ruta por consiguiente se mostrara otro html
    this.router.navigate(['actualizar-categoria',id]);
  }

  //a este funcion lo llama el index
  eliminarCategoria(id:number){

    this.categoriaService.eliminarCategoria(id).subscribe(dato =>{
      console.log(dato);
      this.obtenerCategorias();
    })
  }


}
