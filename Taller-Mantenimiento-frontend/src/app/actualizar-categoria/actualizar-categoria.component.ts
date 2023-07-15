import  swal  from 'sweetalert2';
import { Component, OnInit } from '@angular/core';
import { Categoria } from '../categoria';
import { CategoriaService } from '../categoria.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-actualizar-categoria',
  templateUrl: './actualizar-categoria.component.html',
  styleUrls: ['./actualizar-categoria.component.css']
})
export class ActualizarCategoriaComponent implements OnInit {
//ES HTNL VA A PODER UTILIZAR ESTA PROPIEDADES
  id:number;
  categoria:Categoria = new Categoria();
  constructor(private categoriaService:CategoriaService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.categoriaService.obtenerCategoriaId(this.id).subscribe(dato =>{
      this.categoria = dato;
    },error => console.log(error));
  }

  irAlaListaDeCategorias(){
    this.router.navigate(['/actualizar-categoria']);
    swal('Categoria actualizada',`La categoria${this.categoria.nombre} ha sido actualizado con exito`,`success`);
  }

  onSubmit(){
    this.categoriaService.actualizarCategoria(this.id,this.categoria).subscribe(dato => {
      this.irAlaListaDeCategorias();
    },error => console.log(error));
  }


}
