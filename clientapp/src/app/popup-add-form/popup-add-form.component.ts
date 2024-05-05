import {Component, Input} from '@angular/core';
import {ClienteDto} from "../model/client-dto";
import {ClientService} from "../service/client.service";
import {HttpErrorResponse} from "@angular/common/http";

import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-popup-add-form',
  templateUrl: './popup-add-form.component.html',
  styleUrl: './popup-add-form.component.css'
})
export class PopupAddFormComponent {
  client: ClienteDto;
  @Input() errorMessage: string|undefined;

  constructor(


    private clientService: ClientService,
    private dialogRef: MatDialogRef<PopupAddFormComponent>) {
    this.client = new ClienteDto();

  }

  onSubmit() {
    // @ts-ignore
    if (this.client.started > this.client.ended){

      this.errorMessage="Start date is Greater than End date";
      throw new Error('Start date cannot be greater than end date');

    }

    this.errorMessage="";
    this.clientService.save(this.client)
      .subscribe({
        next:response=> {  this.dialogRef.close(); },
        error:(error:HttpErrorResponse) => {this.handleError(error)}

      });
  }
  handleError(error: HttpErrorResponse) {

      console.error( error.error.message)
      this.errorMessage = 'Error: ' + error.error.message;

  }

}
