import { Component, OnInit } from '@angular/core';
import { CargosService } from './cargos.service';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from './../services/auth.service';
import {ToastrService} from 'ngx-toastr';



@Component({
  selector: 'app-cargos',
  templateUrl: './cargos.component.html',
  styleUrls: ['./cargos.component.css']
})
export class CargosComponent implements OnInit {
  public cargos: any = [];
  public cargosForm: FormGroup;


  constructor(
    private cargosService: CargosService,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private authService: AuthService ,
    private toastr: ToastrService
  ) {

    this.cargosForm = this.formBuilder.group({
      id: [null],
      nombre: [null, Validators.required],
      descripcion: [null, Validators.required]
    });


  }

  ngOnInit() {
    this.init();
  }

  init() {

    this.cargosService.cargos().subscribe(cargos => {
      this.cargos = cargos;
    });

  }

  limpiar() {
    this.cargosForm.reset();
  }


  eliminar(id: number) {
    this.cargosService.remove(id).subscribe((data: any) => {
      this.toastr.success('cargo eliminado');
      this.init();
    }, (error: any) => {
      console.log(error);
      this.toastr.error('error eliminando el cargo');
    });
  }

  editar(cargo) {
    this.cargosForm.setValue(cargo);
  }


  guardar() {
    if (this.cargosForm.value.id) {
      this.cargosService.update(this.cargosForm.value.id, this.cargosForm.value).subscribe((data) => {
        this.init();
        this.toastr.success('cargo actualizado correctamente');
        this.limpiar();
      }, (error) => {
      });
    } else {
      this.cargosService.persist(this.cargosForm.value).subscribe((data) => {
        this.init();
        this.toastr.success('cargo creado correctamente');
        this.limpiar();
      }, (error) => {
        this.toastr.error('error guardando el cargo');
      });
    }
  }

  compareFn(c1: any, c2: any): boolean {
    // console.log(c1, c2);
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

}
