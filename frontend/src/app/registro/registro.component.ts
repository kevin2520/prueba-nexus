import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UsuariosService} from '../usuarios/usuarios.service';
import {CargosService} from '../cargos/cargos.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {


  public registerForm: FormGroup;
  public cargos: Array<any> = [];

  constructor(private usuariosService: UsuariosService, private formBuilder: FormBuilder, private router: Router, private cargosService: CargosService, private toastr: ToastrService) {
    this.registerForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      cedula: ['', Validators.required],
      edad: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.required]],
      cargo: ['', Validators.required],
    });
  }

  ngOnInit() {

    this.cargosService.cargos().subscribe((res: any) => {
      this.cargos = res;

    }, (error) => {
      console.log(error);
    });
  }

  registro() {
    console.log(this.registerForm.value);
    this.usuariosService.registrar(this.registerForm.value).subscribe((data: any) => {
      console.log(data);
      this.router.navigate(['login']);
      this.toastr.success('usuario conectado');
    }, (error: any) => {
      console.log(error);
    });
  }

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}
