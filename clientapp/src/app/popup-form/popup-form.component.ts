import {Component, Inject, Input} from '@angular/core';
import {ClienteDto} from "../model/client-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientService} from "../service/client.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {HttpErrorResponse} from "@angular/common/http";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-popup-form',
  templateUrl: './popup-form.component.html',
  styleUrl: './popup-form.component.css'
})
export class PopupFormComponent {
  client: ClienteDto;
  @Input() errorMessage: string|undefined;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ClienteDto,
    private route: ActivatedRoute,
    private router: Router,
    private dialogRef: MatDialogRef<PopupFormComponent>,
    private clientService: ClientService) {
    this.client = data;
  }

  onSubmit() {

    // @ts-ignore
    if (this.client.started > this.client.ended){

      this.errorMessage="Start date is Greater than End date";
      throw new Error('Start date cannot be greater than end date');

    }
    this.errorMessage="";
    this.clientService.update(this.client)
      .subscribe({
        next:response=> {  this.dialogRef.close(); },
        error:(error:HttpErrorResponse) => {this.handleError(error)}

      });
  }

  gotoClientList() {
    this.router.navigate(['/clients']);
  }
  handleError(error: HttpErrorResponse) {
       console.error( error.error.message)
      this.errorMessage = 'Error: ' + error.error.message;

  }
}
