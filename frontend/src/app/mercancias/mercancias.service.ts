import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable()
export class MercanciasService {

  environment: any = environment;

  constructor(
    private http: HttpClient
  ) {
  }

  mercancias(filtro: any) {

    return this.http.get(this.environment.url + '/api/mercancias', {
      params: filtro || undefined
    });
  }

  // crear
  persist(entity: any) {
    return this.http.post(this.environment.url + '/api/mercancias', entity);
  }

  // actulizar
  update(id: number, entity: any) {
    return this.http.put(this.environment.url + '/api/mercancias/' + id, entity);
  }

  // eliminar mercancias
  remove(id: number) {
    return this.http.delete(this.environment.url + '/api/mercancias/' + id);
  }

}
