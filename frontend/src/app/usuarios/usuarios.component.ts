import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { UsuariosService } from './usuarios.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  public usuarios: any;
  public selectedUsuario: any = {};
  public userEmail: any;


  constructor(private usuariosService: UsuariosService, private authService: AuthService,  private toastr: ToastrService) {

  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.selectedUsuario = {};
    this.usuariosService.getUser().subscribe(usuarios => {
      this.usuarios = usuarios;
      // console.log(usuarios);
    });
  }


  eliminar(id: number) {
    this.usuariosService.remove(id).subscribe((data: any) => {
      // console.log(data);
      console.log('eliminar');
      this.toastr.success('se ha eliminado el usuario correctamente');
      this.init();
    }, (error: any) => {
      console.log(error);
      this.toastr.success('error eliminando el usuario');
    });

  }

  editar(usuario) {
    this.selectedUsuario = Object.assign({}, usuario);
  }

  isInCargo(cargo) {
   return this.authService.hasCargo(cargo);
  }

  guardar() {
    if (this.selectedUsuario.id) {
      this.usuariosService.update(this.selectedUsuario.id, this.selectedUsuario).subscribe((data: any) => {
        // console.log(data);
        console.log('actulizado');
        this.toastr.success('se ha actualizado el usuario correctamente');
        this.init();
      }, (error: any) => {
        console.log(error);
      });
    } else {
      this.usuariosService.persist(this.selectedUsuario).subscribe((data: any) => {
        // console.log(data);
        this.toastr.success('se ha creado el usuario correctamente');
        this.init();
      }, (error: any) => {
        console.log(error);
      });
    }
  }

}




