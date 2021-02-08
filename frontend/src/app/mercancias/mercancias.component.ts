import {Component, OnInit} from '@angular/core';
import {MercanciasService} from './mercancias.service';
import {HttpClient} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from './../services/auth.service';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-mercancias',
  templateUrl: './mercancias.component.html',
  styleUrls: ['./mercancias.component.css']
})
export class MercanciasComponent implements OnInit {
  public mercancias: any = [];
  public maxDate = new Date().toISOString().split('T')[0];
  public mercanciasForm: FormGroup;
  public filtro: FormControl = new FormControl();


  constructor(
    private mercanciasService: MercanciasService,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private toastr: ToastrService
  ) {

    this.mercanciasForm = this.formBuilder.group({
      id: [null],
      nombreProducto: [null, Validators.required],
      cantidad: [null, Validators.required],
      fechaIngreso: [new Date(), Validators.required]
    });


  }

  ngOnInit() {

    this.filtro.valueChanges.subscribe(filtro => {

      this.init();
    });

    this.init();
  }

  init() {

    let filtro: any = {
      nombreProducto: null
    };
    if (this.filtro.value !== null) {
      filtro.nombreProducto = this.filtro.value;
    } else {
      filtro = null;
    }
    this.mercanciasService.mercancias(filtro).subscribe(mercancias => {
      this.mercancias = mercancias;
    });

  }

  limpiar() {
    this.mercanciasForm.reset();
  }


  eliminar(id: number) {
    this.mercanciasService.remove(id).subscribe((data: any) => {
      this.toastr.success('mercancia eliminada');
      this.init();
    }, (error: any) => {
      console.log(error);
      this.toastr.error('error eliminando la mercancia');
    });
  }

  editar(mercancia) {
    this.mercanciasForm.patchValue(mercancia);
    this.mercanciasForm.get('fechaIngreso').setValue(mercancia.fechaIngreso);
  }


  guardar() {
    if (this.mercanciasForm.value.id) {
      this.mercanciasService.update(this.mercanciasForm.value.id, this.mercanciasForm.value).subscribe((data) => {
        this.init();
        this.toastr.success('se ha editado la mercancia correctamente');
        this.limpiar();
      }, (error) => {
      });
    } else {
      this.mercanciasService.persist(this.mercanciasForm.value).subscribe((data) => {
        this.init();
        this.toastr.success('se ha registrado la mercancia correctamente');
        this.limpiar();
      }, (error) => {
        this.toastr.error('error creando la mercancia');
      });
    }
  }

  isInCargo(cargo) {
    return this.authService.hasCargo(cargo);
  }

  compareFn(c1: any, c2: any): boolean {
    // console.log(c1, c2);
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

}
