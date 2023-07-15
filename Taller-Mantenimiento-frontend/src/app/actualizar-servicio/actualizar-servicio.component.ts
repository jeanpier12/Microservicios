import { Component, OnInit } from '@angular/core';
import { Servicios } from '../servicios';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiciosService } from '../servicios.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-servicio',
  templateUrl: './actualizar-servicio.component.html',
  styleUrls: ['./actualizar-servicio.component.css']
})
export class ActualizarServicioComponent implements OnInit {

  id:number;
  servicio:Servicios = new Servicios();
  constructor(private ServiciosService:ServiciosService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.ServiciosService.obtenerServicioId(this.id).subscribe(dato =>{
      this.servicio = dato;
    },error => console.log(error));
  }

  irAlaListaDeServicios(){
    this.router.navigate(['/actualizar-servicios']);
    swal('Servicio actualizado',`El servicio${this.servicio.nombre} ha sido actualizado con exito`,`success`);
  }

  onSubmit(){
    this.ServiciosService.actualizarServicio(this.id,this.servicio).subscribe(dato => {
      this.irAlaListaDeServicios();
    },error => console.log(error));
  }


}
