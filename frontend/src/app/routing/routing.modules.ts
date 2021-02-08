import { NotFoundComponent } from './../not-found/not-found.component';
import { HomeComponent } from './../home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuariosComponent } from '../usuarios/usuarios.component';
import { MercanciasComponent } from '../mercancias/mercancias.component';
import { LoginComponent } from '../login/login.component';
import { RegistroComponent } from '../registro/registro.component';
import { AuthGuard } from '../auth/auth.guard';
import {CargosComponent} from '../cargos/cargos.component';


const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'not-found', component: NotFoundComponent },
    { path: 'usuarios', component: UsuariosComponent, canActivate: [AuthGuard] },
    { path: 'cargos', component: CargosComponent, canActivate: [AuthGuard] },
    { path: 'mercancias', component: MercanciasComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'registro', component: RegistroComponent },
    // poner encima de estas los rutas nuevas en orden
    { path: '', redirectTo: '/home', pathMatch: 'full'},
    { path: '**', redirectTo: '/not-found', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class RoutingModule { }
