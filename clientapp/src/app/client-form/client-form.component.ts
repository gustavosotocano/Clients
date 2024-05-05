import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../service/client.service';
import { ClienteDto } from '../model/client-dto';


@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent {
  clientDto: ClienteDto;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService) {
    this.clientDto = new ClienteDto();
  }

  onSubmit() {
    this.clientService.save(this.clientDto)
      .subscribe(result => {  this.gotoClientList()
        }, error => {
           console.error(error);
        });
  }

  gotoClientList() {
    this.router.navigate(['/clients']);
  }

}
