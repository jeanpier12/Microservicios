import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from './categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private baseURL ="http://localhost:8005/categorias/Lista";
  private baseURL2 ="http://localhost:8005/categorias/save";
  private baseURL3 ="http://localhost:8005/categorias/ver";
  private baseURL4 ="http://localhost:8005/categorias/actualizar";
  private baseURL5 ="http://localhost:8005/categorias/delete";
  constructor(private httpClient : HttpClient) { }


   //Este metodo nos sirve para obtener los empleados
   obtenerListaDeCategorias():Observable<Categoria[]>{
    return this.httpClient.get<Categoria[]>(`${this.baseURL}`);
      }

      //Añadir el metodo para guardar un empleado
  registrarCategoria(categoria:Categoria):Observable<Object>{
    return this.httpClient.post(`${this.baseURL2}`,categoria);

  }

  //este metodo sirve para actualizar el empleado
  actualizarCategoria(id:number,categoria:Categoria):Observable<Object>{
    return this.httpClient.put(`${this.baseURL4}/${id}`,categoria);
  }

   //este metodo sirve para obtener o buscar un empleado
   obtenerCategoriaId(id:number):Observable<Categoria>{
    return this.httpClient.get<Categoria>(`${this.baseURL3}/${id}`);
  }

  //este metodo elimina un empleado
  eliminarCategoria(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL5}/${id}`);

  }
}
