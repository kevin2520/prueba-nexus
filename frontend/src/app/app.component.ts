import { AuthService } from './services/auth.service';
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'prueba';

  constructor(private authService: AuthService, private router: Router) {

  }

  getUserLogged() {
    return this.authService.getUserLogged();
  }


  logOut() {
      this.authService.logOut();
  }

}
