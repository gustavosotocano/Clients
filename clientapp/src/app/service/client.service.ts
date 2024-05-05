
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ClienteDto} from "../model/client-dto";
// @ts-ignore
import { Observable } from "rxjs/Observable";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private clientsUrl: string;

  constructor(private http: HttpClient) {
    this.clientsUrl = 'http://localhost:8090';
  }

  public findAll(): Observable<ClienteDto[]> {
    return this.http.get<ClienteDto[]>(this.clientsUrl+"/v1/client");
  }

  public getClientById(sharedKey:string): Observable<ClienteDto> {
    return this.http.get<ClienteDto>(this.clientsUrl+"/v1/client/sharedKey/"+sharedKey);
  }
  public save(cliente: ClienteDto) {
    return this.http.post<ClienteDto>(this.clientsUrl+"/v1/client", cliente);
  }

  public update(cliente: ClienteDto) {
    return this.http.put<ClienteDto>(this.clientsUrl+"/v1/client", cliente);
  }
}
