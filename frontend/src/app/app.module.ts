import { MercanciasService } from './mercancias/mercancias.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { MercanciasComponent } from './mercancias/mercancias.component';

import { UsuariosService } from './usuarios/usuarios.service';
import { CargosService } from './cargos/cargos.service';

import { RoutingModule } from './routing/routing.modules';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { AuthService } from './services/auth.service';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { CargosComponent } from './cargos/cargos.component';
import {InterceptorProvider} from './interceptors/interceptor';


@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    MercanciasComponent,
    LoginComponent,
    RegistroComponent,
    NotFoundComponent,
    HomeComponent,
    CargosComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RoutingModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
  ],
  providers: [
    UsuariosService,
    CargosService,
    MercanciasService,
    AuthService,
    InterceptorProvider,
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorProvider, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
