import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaCategoriasComponent } from './lista-categorias/lista-categorias.component';
import { RegistrarCategoriaComponent } from './registrar-categoria/registrar-categoria.component';
import { ActualizarCategoriaComponent } from './actualizar-categoria/actualizar-categoria.component';


//en Router estamos vinculando cada componente de angular  con las ruta  generica
const routes: Routes = [
  //si le soy a la url por defecto y empleados me llevara
  //al compomente asignado =component:ListaEmpleadosComponent
{path:"Categorias-lista",component:ListaCategoriasComponent},
//si yo le doy una url por defecto me va a dirigir a empleados
{path:"",redirectTo:"Categorias-lista",pathMatch:"full"},
//si estro a esta url se me mostrara el componente =RegistroEmpleadoComponent
{path:"registrar-categoria",component:RegistrarCategoriaComponent},
{path : 'actualizar-categoria/:id',component:ActualizarCategoriaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
