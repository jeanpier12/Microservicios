import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Servicios } from '../servicios';
import { ServiciosService } from '../servicios.service';

@Component({
  selector: 'app-lista-servicos',
  templateUrl: './lista-servicos.component.html',
  styleUrls: ['./lista-servicos.component.css']
})
export class ListaServicosComponent implements OnInit {
  [x: string]: any;

  ser:Servicios[];


  constructor(private serviciosService:ServiciosService ,private router:Router) { }

  ngOnInit(): void {

    this.obtenerServicios();

  }

  private obtenerServicios(){
    this.serviciosService.obtenerListaDeServicios().subscribe(dato => {
         this.ser=dato;

    })

  };

//El boton del archivo html llama a esta categoria
  actualizarServicio(id:number){
    //lo que se esta haciendo aca es llamar a una ruta por consiguiente se mostrara otro html
    this.router.navigate(['actualizar-servicios',id]);
  }

  //a este funcion lo llama el index
  eliminarServicio(id:number){

    this.serviciosService.eliminarServicio(id).subscribe(dato =>{
      console.log(dato);
      this.obtenerServicios();
    })
  }

}
