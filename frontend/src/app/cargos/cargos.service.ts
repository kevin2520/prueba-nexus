import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable()
export class CargosService {

  environment: any = environment;
    constructor(
        private http: HttpClient
    ) { }

    cargos() {

        return this.http.get(this.environment.url + '/api/cargos');
    }

    // crear
    persist(entity: any) {
      return this.http.post(this.environment.url + '/api/cargos', entity);
    }
    // actulizar
    update(id: number, entity: any) {
        return this.http.put(this.environment.url + '/api/cargos/' + id, entity);
    }

    // eliminar cargos
    remove(id: number) {
        return this.http.delete(this.environment.url + '/api/cargos/' + id);
    }

}
