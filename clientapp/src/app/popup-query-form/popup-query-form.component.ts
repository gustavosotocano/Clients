import {Component,  Input} from '@angular/core';
import {ClienteDto} from "../model/client-dto";
import {ClientService} from "../service/client.service";

@Component({
  selector: 'app-popup-query-form',
  templateUrl: './popup-query-form.component.html',
  styleUrl: './popup-query-form.component.css'
})
export class PopupQueryFormComponent {
  @Input() client: ClienteDto;
  // @ts-ignore
  @Input() sharedKey: string;
    constructor(private clientService: ClientService) {
      this.client = new ClienteDto();
  }



  public  callServiceQuery( ){

    this.clientService.getClientById(this.sharedKey)
      .subscribe((data: ClienteDto ) => {
      this.client = data;
    });
    console.log(this.client);
  }
}
