import {Component, Input, OnInit} from '@angular/core';
import {ClienteDto} from "../model/client-dto";
import {ClientService} from "../service/client.service";
import {PopupFormComponent} from "../popup-form/popup-form.component";
import {MatDialog} from "@angular/material/dialog";
import {PopupAddFormComponent} from "../popup-add-form/popup-add-form.component";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.css'
})
export class ClientListComponent implements OnInit{
  clienteDtos!: ClienteDto[];
clients:ClienteDto;
  @Input() errorMessage: string|undefined;

  // @ts-ignore
  @Input() sharedKey: string ;
  showPopup: boolean = false;
  constructor(private clientService: ClientService,private dialog: MatDialog,
             ) {
    this.clients=new ClienteDto();
  }

  ngOnInit() {
  this.callService();
  }

  callService(){

    this.clientService.findAll().subscribe((data: ClienteDto[] ) => {
      this.clienteDtos = data;
    });
  }

  openFormPopupNew(): void {

    const dialogRef = this.dialog.open(PopupAddFormComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {

location.reload();
    });
  }


 searchClient(){

   this.clientService.getClientById(this.sharedKey)
     .subscribe({
       next: (response: ClienteDto) => { this.handleClientRetrieved(response); },
       error: (error:HttpErrorResponse) => { this.handleError(error) }
     });
    }
  handleError(error: HttpErrorResponse) {
    console.error( error.error.message)
    this.errorMessage = 'Error: ' + error.error.message;

  }


  handleClientRetrieved(data: ClienteDto){
    this.clients = data;
    this.showPopup = !this.showPopup;
    this.errorMessage="";
  }
  openFormPopup(client: ClienteDto): void {
    console.log(client);
    const dialogRef = this.dialog.open(PopupFormComponent, {
      width: '400px',
      data:  client
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('El formulario emergente se cerró');
      this.callService();
    });
  }

}
