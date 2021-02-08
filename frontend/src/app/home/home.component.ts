import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [AuthService]
})
export class HomeComponent implements OnInit, OnDestroy {
  usuario: any;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {

  }

  usuarioAuth() {
    return this.authService.getUserLogged();
  }

  ngOnDestroy() {
  }

}


