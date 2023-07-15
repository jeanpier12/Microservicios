import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Servicios } from './servicios';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  private baseURL ="http://localhost:8009/servicios/Listar";
  private baseURL2 ="http://localhost:8009/servicios/create";
  private baseURL3 ="http://localhost:8009/servicios/ver";
  private baseURL4 ="http://localhost:8009/servicios/update";
  private baseURL5 ="http://localhost:8009/servicios/delete";
  constructor(private httpClient : HttpClient) { }


   //Este metodo nos sirve para obtener loservicio
   obtenerListaDeServicios():Observable<Servicios[]>{
    return this.httpClient.get<Servicios[]>(`${this.baseURL}`);
      }

      //Añadir el metodo para guardar servicio
  registrarServicio(servicio:Servicios):Observable<Object>{
    return this.httpClient.post(`${this.baseURL2}`,servicio);

  }

  //este metodo sirve para actualizar servicio
  actualizarServicio(id:number,servicio:Servicios):Observable<Object>{
    return this.httpClient.put(`${this.baseURL4}/${id}`,servicio);
  }

   //este metodo sirve para obtener o buscar un servicio
   obtenerServicioId(id:number):Observable<Servicios>{
    return this.httpClient.get<Servicios>(`${this.baseURL3}/${id}`);
  }

  //este metodo elimina servicio
  eliminarServicio(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL5}/${id}`);

  }
}
