import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {ClientListComponent} from './client-list/client-list.component';
import {ClientFormComponent} from './client-form/client-form.component';
import {PopupFormComponent} from './popup-form/popup-form.component';
import {MatCell, MatColumnDef, MatHeaderCell} from "@angular/material/table";
import {MatIconButton} from "@angular/material/button";
import {PopupAddFormComponent} from './popup-add-form/popup-add-form.component';
import {NgxMaskDirective, provideNgxMask} from 'ngx-mask';
import {PopupQueryFormComponent} from './popup-query-form/popup-query-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    ClientFormComponent,
    PopupFormComponent,
    PopupAddFormComponent,
    PopupQueryFormComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatHeaderCell,
    MatColumnDef,
    MatCell,
    MatIconButton,

    NgxMaskDirective,
  ],
  providers: [provideNgxMask()],
  bootstrap: [AppComponent]
})
export class AppModule { }
