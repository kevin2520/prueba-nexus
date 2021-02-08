import {HttpEvent, HttpRequest} from '@angular/common/http';
import {finalize, tap, timeout} from 'rxjs/operators';

export class InterceptorProvider {
  token: string;
  constructor() {

    if (localStorage.getItem('usuario')) {
      this.token = JSON.parse(localStorage.getItem('usuario')).accessToken;
    } else {
      this.token =  null;
    }

  }

  intercept(request: HttpRequest<any>, next) {
    const token: string | null = this.token;
    const authRequest: HttpRequest<any> = request.headers.getAll('Authorization') ? request : request.clone({headers: request.headers.set('Authorization', `Bearer ${token}`)});
      if (request.body instanceof FormData) {
        return next.handle(token !== null ? authRequest : request);
      } else {
        return next.handle(token !== null ? authRequest : request)
          .pipe(
            timeout(10000),
            tap((event: HttpEvent<any>) => {
                return event;
              }, (err) => {
                console.log(err);
              }
            ));
      }


  }
}
