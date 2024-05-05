import {Component} from '@angular/core';
import {PopupAddFormComponent} from "./popup-add-form/popup-add-form.component";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: string;

  constructor(private dialog: MatDialog,
              private route: ActivatedRoute,
  private router: Router) {


    this.title = 'Clients';
  }
  openFormPopup(): void {

    const dialogRef = this.dialog.open(PopupAddFormComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('El formulario emergente se cerró');

    });
  }
}
