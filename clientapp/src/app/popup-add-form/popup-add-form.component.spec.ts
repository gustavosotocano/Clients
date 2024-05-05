import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupAddFormComponent } from './popup-add-form.component';

describe('PopupAddFormComponent', () => {
  let component: PopupAddFormComponent;
  let fixture: ComponentFixture<PopupAddFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PopupAddFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopupAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
