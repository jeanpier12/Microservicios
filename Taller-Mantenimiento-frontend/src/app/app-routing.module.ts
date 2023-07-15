import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaCategoriasComponent } from './lista-categorias/lista-categorias.component';
import { RegistrarCategoriaComponent } from './registrar-categoria/registrar-categoria.component';
import { ActualizarCategoriaComponent } from './actualizar-categoria/actualizar-categoria.component';
import { RegistrarServiciosComponent } from './registrar-servicios/registrar-servicios.component';
import { ActualizarServicioComponent } from './actualizar-servicio/actualizar-servicio.component';
import { ListaServicosComponent } from './lista-servicos/lista-servicos.component';



//en Router estamos vinculando cada componente de angular  con las ruta  generica
const routes: Routes = [
 //si yo le doy una url por defecto me va a dirigir a lista de categorias
{path:" ",redirectTo:"Categorias-lista",pathMatch:"full"},
//al compomente asignado =component:ListaCategoriasComponent
{path:"Categorias-lista",component:ListaCategoriasComponent},
//si estro a esta url se me mostrara el componente =RegistroEmpleadoComponent
{path:"registrar-categoria",component:RegistrarCategoriaComponent},
{path : 'actualizar-categoria/:id',component:ActualizarCategoriaComponent},
//al compomente asignado =component:ListaCategoriasComponent
{path:"Servicios-lista",component:ListaServicosComponent},
//si estro a esta url se me mostrara el componente =RegistroEmpleadoComponent
{path:"registrar-Servicios",component:RegistrarServiciosComponent},
{path : 'actualizar-servicios/:id',component:ActualizarServicioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
