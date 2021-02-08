import { Router } from '@angular/router';
import { UsuariosService } from './../usuarios/usuarios.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public email: string;
  public password: string;

  constructor(private usuariosService: UsuariosService, public router: Router, private toastr: ToastrService) { }

  ngOnInit() {

  }

  login(form: NgForm) {
    console.log(form.value);
    this.usuariosService.login(form.value).subscribe((data: any) => {
      console.log(data);
      if (data) {
        localStorage.setItem('usuario', JSON.stringify(data));
        this.router.navigate(['home']);
        this.toastr.success('usuario conectado');
      } else {
        this.toastr.success('usuario o contraseña incorrecta');
        alert('usuario o contraseña incorrecta');

      }
    }, (error: any) => {
      this.toastr.info('No se puede conectar');
        alert('no se puede conectar');

    });
  }
  aler() {
    this.toastr.info('No se puede conectar');

  }


}


