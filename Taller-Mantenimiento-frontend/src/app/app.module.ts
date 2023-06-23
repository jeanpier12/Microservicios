import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaCategoriasComponent } from './lista-categorias/lista-categorias.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RegistrarCategoriaComponent } from './registrar-categoria/registrar-categoria.component';
import { ActualizarCategoriaComponent } from './actualizar-categoria/actualizar-categoria.component';

@NgModule({
  declarations: [
    AppComponent,
    ListaCategoriasComponent,
    RegistrarCategoriaComponent,
    ActualizarCategoriaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
     //se agrego
    HttpClientModule,
      //para agregar formularios
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
