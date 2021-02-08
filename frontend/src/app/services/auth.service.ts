import {Injectable} from '@angular/core';
import jwt_decode from 'jwt-decode';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  usuario: any;

  constructor(private router: Router) {
  }

  getUserLogged() {
    if (localStorage.getItem('usuario')) {
      // console.log('localStore' + localStorage.getItem('usuario'));
      this.usuario = this.getDecodedAccessToken();
      return this.usuario;
    } else {
      return undefined;
    }
  }


  hasCargo(cargo: string) {
    if (this.getUserLogged()) {
      const cargos: [] = this.getDecodedAccessToken().cargos;
      return cargos.find((data: any) => {
        return data.authority === cargo;
      }) !== undefined;
    } else {
      return false;
    }
  }


  getDecodedAccessToken(): any {
    try {

      if (localStorage.getItem('usuario')) {
        return jwt_decode(JSON.parse(localStorage.getItem('usuario')).accessToken);
      } else {
        return null;
      }
    } catch (Error) {
      return null;
    }
  }

  logOut() {
    localStorage.clear();
    window.location.reload();
    this.router.navigate(['home']);
  }
}
