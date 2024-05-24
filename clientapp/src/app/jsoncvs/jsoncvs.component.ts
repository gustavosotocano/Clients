import { Component } from '@angular/core';
import { ClientService } from '../service/client.service';
import {ClienteDto} from "../model/client-dto";
@Component({
  selector: 'app-jsoncvs',
  templateUrl: './jsoncvs.component.html',
  styleUrl: './jsoncvs.component.css'
})
export class JsoncvsComponent {

  jsonInput: string = '';
  csvOutput: string = '';
  clienteDtos!: ClienteDto[];

  constructor(private jsonToCsvService: ClientService) { }

  convertJsonToCsv(): void {
    try {

      this.jsonToCsvService.findAll().subscribe((data: ClienteDto[] ) => {
        this.clienteDtos = data;
        this.csvOutput=this.jsonToCsvService.convertJsonToCsv(this.clienteDtos)
         this.downloadCsv();
      });

      //const jsonData = JSON.parse(this.jsonInput);
      this.csvOutput = this.jsonInput;//this.jsonToCsvService.convertJsonToCsv(jsonData);
    } catch (error) {
      console.error('Invalid JSON input', error);
    }
  }

 public downloadCsv(): void {
    const blob = new Blob([this.csvOutput], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'data.csv';
    link.click();
  }

}
