import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../environments/environment';



@Injectable()
export class UsuariosService {

    environment:any = environment;
    constructor(
        private http: HttpClient,
    ) { }

    getUser() {

        return this.http.get(this.environment.url + '/api/usuarios');
    }

    // crear
    persist(entity: any) {
        return this.http.post(this.environment.url + '/api/usuarios', entity);
    }

  // crear
   registrar(entity: any) {
    return this.http.post(this.environment.url + '/api/auth/registrar', entity);
   }
    // actulizar
    update(id: number, entity: any) {
        return this.http.put(this.environment.url + '/api/usuarios/' + id, entity);
    }

    // eliminar mercancias
    remove(id: number) {
        return this.http.delete(this.environment.url + '/api/usuarios/' + id);
    }

    login(usuario) {
        return this.http.post(this.environment.url + '/api/auth/login', usuario);
    }

}
