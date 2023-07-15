import { Component, OnInit } from '@angular/core';
import { Servicios } from '../servicios';
import { Router } from '@angular/router';
import { ServiciosService } from '../servicios.service';

@Component({
  selector: 'app-registrar-servicios',
  templateUrl: './registrar-servicios.component.html',
  styleUrls: ['./registrar-servicios.component.css']
})
export class RegistrarServiciosComponent implements OnInit {
  servicio:Servicios =new Servicios();
  constructor(private servicioServicio:ServiciosService, private router:Router) { }

  ngOnInit(): void {


  }
//declarando la funcion guardarcategoria
  guardarServicio(){
    this.servicioServicio.registrarServicio(this.servicio).subscribe(dato =>{
      console.log(dato);
      this.irAlaListaDeServicios();


    },error => console.log(error));
  }

 irAlaListaDeServicios(){
  console.log("se ejecuto el metodo de redireccion")
  this.router.navigate(["/Servicios-lista"])
 }


 //el boton del formulario html llama a este metodo
//este metodo imprime los datos del empleado al cual yo quiero registrar
  onSubmit(){
    //llamando la funcion
    this.guardarServicio();
  }

}
