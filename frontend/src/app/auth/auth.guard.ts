// ng generate guard can-deactivate (para generar el guard)


import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    // si retorna a true va saguir a la ruta
    if (this.authService.getUserLogged()) {
      return true;
    } else {
      // no va saguir y redirige a la ruta que se ponga por default
      this.router.navigate(['/login']);
      return false;
    }
  }
}
